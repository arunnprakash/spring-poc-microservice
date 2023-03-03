package com.example.demo;

import static java.lang.System.out;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationSummary {

	@Autowired
	private ServletWebServerApplicationContext webServerAppCtxt;

	@Autowired
	private Environment environment;

	@EventListener(ApplicationReadyEvent.class)
	public void appSummary() {
		out.printf("==============================Application Summary==============================\n");
		out.printf("\tProfile \t\t:\t%s\n", Arrays.asList(environment.getActiveProfiles()));
		out.printf("\tService Port\t\t:\t%s\n", webServerAppCtxt.getWebServer().getPort());
		out.printf("===============================================================================\n");
	}

}
