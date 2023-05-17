package com.example.demo;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.List;
import java.util.Map;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.example.demo.jwt.TokenManager;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtAuthorizationFilter implements WebFilter {

	@Autowired
	private TokenManager tokenManager;
	
	@Override
	public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
		try {
			log.info("Requested Url :: {}", serverWebExchange.getRequest().getURI());
			MDC.put("RequestId", "");
			var authentication = createAuthentication(serverWebExchange);
			return webFilterChain.filter(serverWebExchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
		} catch (Exception exp) {
			ReactiveSecurityContextHolder.clearContext();
			throw exp;
		} finally {
			MDC.remove("RequestId");
			MDC.remove("UserId");
		}
	}

	private AbstractAuthenticationToken createAuthentication(ServerWebExchange serverWebExchange) {
		AbstractAuthenticationToken authentication = null;
		List<String> authorizationHeaders = serverWebExchange.getRequest().getHeaders().get(AUTHORIZATION);
		if (authorizationHeaders != null && !authorizationHeaders.isEmpty()) {
			String token = authorizationHeaders.get(0);
			token = token.replace("Bearer ", "");
			Claims claims = tokenManager.getClaims(token);
			@SuppressWarnings("unchecked")
			//Map<String, Object> data = (Map<String, Object>) claims.get("sub");
			String roles = "admin";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
			authentication = new UsernamePasswordAuthenticationToken("admin", null, grantedAuthorities);;
		} else {
			log.info("No Authorization header found");
		}
		return authentication;
	}



}