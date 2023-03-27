package com.example.demo.jwt;
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
@ConfigurationProperties(prefix = "application.jwt", ignoreUnknownFields = false, ignoreInvalidFields = false)
public class JwtConfig {

	private String headerName;
	private String tokenPrefix;
	private Long expiration;
	private String secretKey;
}