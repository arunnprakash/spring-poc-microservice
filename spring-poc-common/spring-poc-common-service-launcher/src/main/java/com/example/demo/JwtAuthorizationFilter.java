package com.example.demo;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.util.List;
import java.util.Map;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JwtAuthorizationFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
		try {
			log.info("Requested Url :: {}", serverWebExchange.getRequest().getURI());
			MDC.put("RequestId", "");
			createAuthentication(serverWebExchange);
			return webFilterChain.filter(serverWebExchange);
		} catch (Exception exp) {
			SecurityContextHolder.clearContext();
			throw exp;
		} finally {
			MDC.remove("RequestId");
			MDC.remove("UserId");
		}
	}

	private void createAuthentication(ServerWebExchange serverWebExchange) {
		List<String> authorizationHeaders = serverWebExchange.getRequest().getHeaders().get(AUTHORIZATION);
		if (authorizationHeaders != null && !authorizationHeaders.isEmpty()) {
			String token = authorizationHeaders.get(0);
			token = token.replace("Bearer ", "");
			Claims claims = Jwts.parserBuilder().build().parseClaimsJws(token).getBody();
			@SuppressWarnings("unchecked")
			Map<String, Object> data = (Map<String, Object>) claims.get("sub");
			String roles = "admin";
			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
			AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("admin", null, grantedAuthorities);;
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

}