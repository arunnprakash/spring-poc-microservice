package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


/**
 * @author __ArunPrakash__
 *
 */
@EnableAutoConfiguration
@EnableEurekaServer
@SpringBootApplication
public class DemoServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoServiceDiscoveryApplication.class, args);
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		return http
				.cors()
				.and()
				.csrf()
					.disable()
				.build();
		// @formatter:on
	}
}
