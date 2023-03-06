/*******************************************************************************
 *
 * Copyright (c) 2020 Mindgate Private Limited
 *
 * All information contained herein is, and remains the property of Mindgate Private
 * Limited. The intellectual and technical concepts contained herein are
 * proprietary to Mindgate Private and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material is
 * strictly forbidden unless prior written permission is obtained from Mindgate Private
 * Limited
 *
 *******************************************************************************/
package com.example.demo;

import java.util.Collections;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Configuration
@Slf4j
public class OpenApiConfig {

	@Value("${spring.application.name}")
	private String appName;

	@Bean
	OpenAPI apiInfo() {
		return new OpenAPI().info(new Info()
					.title(appName.toUpperCase())
					.version("1.0.0")
					.description("\"REST API for Vtransact "+appName+"\"")
					.license(new License().name("Copyright (c) 2023 Demo Limited").url("https://www.demo.com"))
					.contact(new Contact().name("Mindgate").url("https://www.mindgate.in/").email("arunnprakash@gmail.com"))
				)
				.components(new Components()
						.addSecuritySchemes("BearerToken", apiKeySecuritySchema())
						.addSecuritySchemes("AcceptLanguage", acceptLanguageSecuritySchema())
				)
				.security(Collections.singletonList(new SecurityRequirement().addList("BearerToken").addList("AcceptLanguage")));
	}

	private SecurityScheme apiKeySecuritySchema() {
				return new SecurityScheme().name("Authorization").description("Bearer Access Token")
						.in(SecurityScheme.In.HEADER).type(SecurityScheme.Type.APIKEY);
	}

	private SecurityScheme acceptLanguageSecuritySchema() {
				return new SecurityScheme().name("Accept-Language")
						.description("Parameter used to get language based response")
						.in(SecurityScheme.In.HEADER).type(SecurityScheme.Type.APIKEY);
	}

	@Bean
	OpenApiCustomizer openApiCustomiser() {
		return this::customizeOpenApi;
	}

	private void customizeOpenApi(OpenAPI openApi) {
		log.info("Url :: {}", openApi.getPaths());
	}

}
