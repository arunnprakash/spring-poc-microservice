package com.example.demo;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		try {
			log.info("Requested Url :: {}", request.getRequestURI());
			MDC.put("RequestId", request.getHeader("RequestId"));
			chain.doFilter(request, response);
		} catch (Exception exp) {
			// In case of failure. Make sure it's clear; so guarantee user won't be authenticated
			SecurityContextHolder.clearContext();
			throw exp;
		} finally {
			MDC.remove("RequestId");
			MDC.remove("UserId");
		}
	}

}