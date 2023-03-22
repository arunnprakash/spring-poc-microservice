package com.example.demo.dao;

import static java.util.Arrays.asList;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

/**
 * @author __ArunPrakash__
 *
 */
@Configuration
@EnableJdbcAuditing
@EnableJdbcRepositories
class JdbcConfiguration extends AbstractJdbcConfiguration {

	@Override
	protected List<Converter<?,?>> userConverters() {
		return asList(new StatusWriter(), new StatusReader(), 
				new ZonedDateTimeWriter(), new ZonedDateTimeReader());
	}

	/*@Bean
	AuditorAware<AuditableUser> auditorProvider() {
		return new AuditorAwareImpl();
	}*/

	@Bean
	NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean
	TransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}