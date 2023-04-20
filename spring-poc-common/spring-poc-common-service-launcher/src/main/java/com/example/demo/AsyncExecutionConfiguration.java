package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

/**
 * @author __ArunPrakash__
 *
 */
@Configuration
@EnableAsync
public class AsyncExecutionConfiguration {
	
	@Bean
	DelegatingSecurityContextAsyncTaskExecutor taskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) { 
		return new DelegatingSecurityContextAsyncTaskExecutor(threadPoolTaskExecutor); 
	}
}