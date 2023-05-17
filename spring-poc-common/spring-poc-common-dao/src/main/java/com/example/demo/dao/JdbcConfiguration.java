package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.R2dbcDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.dialect.H2Dialect;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Configuration
@EnableR2dbcAuditing
@EnableR2dbcRepositories
class JdbcConfiguration /*extends AbstractR2dbcConfiguration*/ {

	@Bean
	R2dbcCustomConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		try {
			Reflections reflections = new Reflections(this.getClass().getPackageName());
			Set<Class<?>> readingConverters = reflections.getTypesAnnotatedWith(ReadingConverter.class);
			for(Class<?> readingConvertor : readingConverters) {
				Converter<?,?> converter = (Converter<?,?>)readingConvertor.getDeclaredConstructor().newInstance();
				converters.add(converter);
			}
			Set<Class<?>> writingConverters = reflections.getTypesAnnotatedWith(WritingConverter.class);
			for(Class<?> writingConvertor : writingConverters) {
				Converter<?,?> converter = (Converter<?,?>)writingConvertor.getDeclaredConstructor().newInstance();
				converters.add(converter);
			}
		} catch (Exception exp) {
			log.error("Error while instantate Converters", exp);
		}
		return R2dbcCustomConversions.of(H2Dialect.INSTANCE, converters);
	}

	/*@Bean
	AuditorAware<AuditableUser> auditorProvider() {
		return new AuditorAwareImpl();
	}*/

	/*
	 * @Bean NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource
	 * dataSource) { return new NamedParameterJdbcTemplate(dataSource); }
	 */

	/*
	 * @Bean TransactionManager transactionManager(DataSource dataSource) { return
	 * new DataSourceTransactionManager(dataSource); }
	 */

	/*@Override
	@Bean
	public H2ConnectionFactory connectionFactory() {
		return new H2ConnectionFactory(H2ConnectionConfiguration.builder().url("mem:testdb;DB_CLOSE_DELAY=-1;").username("sa").build());
	}*/
}