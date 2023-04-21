package com.example.demo;

import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Configuration
@EnableAsync
public class AsyncExecutionConfiguration {
	
	@Bean
	DelegatingSecurityContextAsyncTaskExecutor taskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) { 
		return new DelegatingSecurityContextAsyncTaskExecutor(threadPoolTaskExecutor); 
	}

	@Bean
	ThreadPoolTaskExecutor threadPoolTaskExecutor(TaskExecutionProperties taskExecutionProperties) {
		log.info("TaskExecutionProperties Thread Prefix {}", taskExecutionProperties.getThreadNamePrefix());
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(taskExecutionProperties.getPool().getCoreSize());
		executor.setMaxPoolSize(taskExecutionProperties.getPool().getMaxSize());
		executor.setQueueCapacity(taskExecutionProperties.getPool().getQueueCapacity());
		executor.setThreadNamePrefix(taskExecutionProperties.getThreadNamePrefix());
		executor.setWaitForTasksToCompleteOnShutdown(taskExecutionProperties.getShutdown().isAwaitTermination());
		executor.initialize();
		return executor;
	}
}