package com.example.demo;

import static java.lang.System.out;
import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

/**
 * @author __ArunPrakash__
 *
 */
@Configuration
public class ApplicationSummary {

	@Autowired
	private ServletWebServerApplicationContext webServerAppCtxt;

	@Autowired
	private Environment environment;

	@Autowired
	private BuildProperties buildProperties;

	@Autowired
	private GitProperties gitProperties;

	@Autowired
	private DataSourceProperties dataSourceProperties;
	
	@EventListener(ApplicationReadyEvent.class)
	public void appSummary() {
		out.println("================================Application Summary================================");
		out.printf("\tProfile \t\t:\t%s%n", asList(environment.getActiveProfiles()));
		out.printf("\tBuild Version\t\t:\t%s%n", buildProperties.getVersion());
		out.printf("\tBuild At\t\t:\t%s%n", buildProperties.getTime());
		out.printf("\tGit Branch\t\t:\t%s%n", gitProperties.getBranch());
		out.printf("\tGit CommitId\t\t:\t%s%n", gitProperties.getCommitId());
		out.printf("\tDB Url\t\t\t:\t%s%n", dataSourceProperties.getUrl());
		out.printf("\tService Port\t\t:\t%s%n", webServerAppCtxt.getWebServer().getPort());
		out.println("===================================================================================");
	}

}
