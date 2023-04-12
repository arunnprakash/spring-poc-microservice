package com.example.demo.gateway;

import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import reactor.core.publisher.Mono;

/**
 * @author __ArunPrakash__
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DemoGatewayApplication {

	@Autowired
	private RouteDefinitionLocator locator;

	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(DemoGatewayApplication.class, args);
	}

	@Bean
	OpenAPI apiInfo() {
		return new OpenAPI().info(new Info()
				.title(appName.toUpperCase())
				.version("1.0.0")
				.description("\""+appName+" for Demo REST API\"")
				.license(new License().name("Copyright (c) 2020 Demo Private Limited").url("https://www.demo.in/"))
				.contact(new Contact().name("Demo").url("https://www.demo.in/").email("arunnprakash@gmail.com"))
			);
	}

	@Bean
	List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters) {
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		return definitions.stream()
				.filter(routeDefinition -> routeDefinition.getId().matches(".*-service"))
				.map(routeDefinition -> {
					swaggerUiConfigParameters.addGroup(routeDefinition.getId());
					return GroupedOpenApi.builder().pathsToMatch("/" + routeDefinition.getId() + "/**").group(routeDefinition.getId()).build();
				})
				.toList();
	}
	
	@Autowired
	private ApplicationContext applicationContext;

	@Value("${spring.webflux.base-path}")
	private String basePath;

	@Bean
	@Primary
	HttpHandler httpHandler() {
		HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(this.applicationContext).build();
		return new ContextPathHttpHandler(httpHandler, basePath);
	}

	class ContextPathHttpHandler implements HttpHandler {
		private final HttpHandler delegate;
		private final String contextPath;

		public ContextPathHttpHandler(HttpHandler delegate, String contextPath) {
			this.delegate = delegate;
			this.contextPath = contextPath;
		}

		@Override
		public Mono<Void> handle(ServerHttpRequest request, ServerHttpResponse response) {
			return delegate.handle(withoutContextPath(request), withoutCache(response));
		}

		private ServerHttpRequest withoutContextPath(ServerHttpRequest request) {
			String path = request.getPath().value();
			if (path.startsWith(contextPath)) {
				String pathWithApplication = path.substring(contextPath.length());
				if (!StringUtils.hasText(pathWithApplication)) {
					pathWithApplication = "/";
				}
				return request.mutate().path(pathWithApplication).build();
			}
			return request;
		}

		private ServerHttpResponse withoutCache(ServerHttpResponse response) {
			response.getHeaders().set("cache-control", "no-store");
			return response;
		}
	}
}
