package com.example.demo;

import static org.springframework.security.config.web.server.SecurityWebFiltersOrder.AUTHENTICATION;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CorsSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

	@Bean
	SecurityWebFilterChain filterChain(ServerHttpSecurity http, JwtAuthorizationFilter jwtAuthorizationFilter) throws Exception {
		// @formatter:off
		return http
				.cors(CorsSpec::disable)
				.csrf(CsrfSpec::disable)
				//.exceptionHandling(exp -> exp.authenticationEntryPoint((req, res) -> res.se))
				//.exceptionHandling(exp -> exp.authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
				//.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.NEVER))
				.authorizeExchange(exchange -> exchange
						.pathMatchers(
								"/v3/api-docs/**",
								"/swagger-ui.html",
								"/webjars/swagger-ui/**",
								"/swagger-resources/**",
								"/error",
								"/favicon.ico",
								"**.png",
								"**.gif",
								"**.svg",
								"**.jpg",
								"**.html",
								"**.css",
								"**.js",
								"/actuator/**")
						.permitAll()
						.anyExchange()
						.authenticated())
				.addFilterBefore(jwtAuthorizationFilter, AUTHENTICATION)
				.build();
		// @formatter:on
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers(request -> {
			log.info("Checking Conditions");
			return true;
		});
	}

	@Bean
	AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		//authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}
}