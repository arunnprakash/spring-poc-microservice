package com.example.demo;

import static java.lang.System.out;
import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import com.example.demo.dao.DatabaseType;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
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

	@Value("${spring.datasource.platform}")
	private DatabaseType databaseType;
	
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
		out.printf("\tDB Platform\t\t:\t%s%n", databaseType);
		out.printf("\tDB Url\t\t\t:\t%s%n", dataSourceProperties.getUrl());
		out.printf("\tService Port\t\t:\t%s%n", webServerAppCtxt.getWebServer().getPort());
		out.println("===================================================================================");
	}

}
