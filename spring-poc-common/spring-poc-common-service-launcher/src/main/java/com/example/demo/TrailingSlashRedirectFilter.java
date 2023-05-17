package com.example.demo;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

/**
 * @author __ArunPrakash__
 *
 */
@Component
@jakarta.servlet.annotation.WebFilter(urlPatterns = {"/*"})
public class TrailingSlashRedirectFilter implements WebFilter {

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String path = request.getPath().value();

		if (path.endsWith("/")) {
			String newPath = path.substring(0, path.length() - 1);
			ServerHttpRequest newRequest = request.mutate().path(newPath).build();
			return chain.filter(exchange.mutate().request(newRequest).build());
		}

		return chain.filter(exchange);
	}
}