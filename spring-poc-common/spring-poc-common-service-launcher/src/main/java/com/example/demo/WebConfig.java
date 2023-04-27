package com.example.demo;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.json.convertors.LocalDateTimeDeserializer;
import com.example.demo.json.convertors.LocalDateTimeSerializer;
import com.example.demo.json.convertors.ZonedDateTimeDeserializer;
import com.example.demo.json.convertors.ZonedDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ZonedDateTimeDeserializer zonedDateTimeDeserializer;

	@Autowired
	private ZonedDateTimeSerializer zonedDateTimeSerializer;

	@Autowired
	private LocalDateTimeDeserializer localDateTimeDeserializer;

	@Autowired
	private LocalDateTimeSerializer localDateTimeSerializer;

	@Autowired
	private JwtAuthorizationFilter jwtAuthorizationFilter;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// @formatter:off
		return http
				.cors(c -> c.disable())
				.csrf(c -> c.disable())
				.exceptionHandling(exp -> exp.authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.NEVER))
				.authorizeHttpRequests(a -> a.requestMatchers(
						"/v3/api-docs/**",
						"/swagger-ui/**",
						"/swagger-ui.html",
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
					.anyRequest()
						.authenticated())
				.addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class)
				.build();
		// @formatter:on
	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		log.debug("Registering Extend Message Converters");
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		SimpleModule dateTimeModule = new SimpleModule();
		dateTimeModule.addDeserializer(ZonedDateTime.class, zonedDateTimeDeserializer);
		dateTimeModule.addSerializer(ZonedDateTime.class, zonedDateTimeSerializer);
		dateTimeModule.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
		dateTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer);
		objectMapper.registerModule(dateTimeModule);
		converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
	}
}
