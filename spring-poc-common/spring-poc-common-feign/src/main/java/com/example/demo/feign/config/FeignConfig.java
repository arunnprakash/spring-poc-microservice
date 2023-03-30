package com.example.demo.feign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.feign.interceptor.FeignRequestInterceptor;

/**
 * @author __ArunPrakash__
 *
 */
@Configuration
@EnableFeignClients
public class FeignConfig {

	@Bean
	FeignRequestInterceptor feignRequestInterceptor() {
		return new FeignRequestInterceptor();
	}
}
