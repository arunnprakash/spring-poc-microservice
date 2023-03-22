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

	@Bean
	ThreadPoolTaskExecutor threadPoolTaskExecutor(ThreadPoolConfig threadPoolConfig) {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(threadPoolConfig.getCorePoolSize());
		executor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
		executor.setQueueCapacity(threadPoolConfig.getQueueCapacity());
		executor.setThreadNamePrefix(threadPoolConfig.getThreadNamePrefix() + "-");
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.initialize();
		return executor;
	}
}