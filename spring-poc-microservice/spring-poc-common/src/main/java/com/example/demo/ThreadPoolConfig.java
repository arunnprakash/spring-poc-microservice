/**
 * 
 */
package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author __ArunPrakash__
 *
 */
@Component
@RefreshScope
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "async.thread", ignoreUnknownFields = false, ignoreInvalidFields = false)
public class ThreadPoolConfig {
	private Integer corePoolSize;
	private Integer maxPoolSize;
	private Integer queueCapacity;
	private String threadNamePrefix;
}
