package com.example.demo.jwt;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author __ArunPrakash__
 *
 */
@Component
public class TokenManager {

	public static final long TOKEN_VALIDITY = 10 * 60 * 60;

	@Autowired
	private JwtConfig jwtConfig;
	private SecretKey secretKey;
	public String generateJwtToken(String username) throws NoSuchAlgorithmException {
		Map<String, Object> claims = new HashMap<>();
		SecretKey secretKey = getKey();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
				.signWith(secretKey, HS512).compact();
	}

	private SecretKey getKey() throws NoSuchAlgorithmException {
		if (secretKey == null) {
			secretKey = KeyGenerator.getInstance("HmacSHA512").generateKey();
			//String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
			//secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length(), );
		}
		return secretKey;
	}

	public Boolean validateJwtToken(String token, UserDetails userDetails) {
		String username = getUsernameFromToken(token);
		Claims claims = getClaims(token);
		Boolean isTokenExpired = claims.getExpiration().before(new Date());
		return (username.equals(userDetails.getUsername()) && !isTokenExpired);
	}

	public String getUsernameFromToken(String token) {
		final Claims claims = getClaims(token);
		return claims.getSubject();
	}
	
	public Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
	}
}