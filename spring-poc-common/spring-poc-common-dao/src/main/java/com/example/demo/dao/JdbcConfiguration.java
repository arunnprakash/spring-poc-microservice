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
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Configuration
@EnableJdbcAuditing
@EnableJdbcRepositories
class JdbcConfiguration extends AbstractJdbcConfiguration {

	@SuppressWarnings("rawtypes")
	@Override
	protected List<Converter<?,?>> userConverters() {
		List<Converter<?,?>> converters = new ArrayList<>();
		try {
			Reflections reflections = new Reflections(this.getClass().getPackageName());
			Set<Class<?>> readingConverters = reflections.getTypesAnnotatedWith(ReadingConverter.class);
			for(Class<?> readingConvertor : readingConverters) {
				Converter<?,?> converter = (Converter)readingConvertor.getDeclaredConstructor().newInstance();
				converters.add(converter);
			}
			Set<Class<?>> writingConverters = reflections.getTypesAnnotatedWith(WritingConverter.class);
			for(Class<?> writingConvertor : writingConverters) {
				Converter<?,?> converter = (Converter)writingConvertor.getDeclaredConstructor().newInstance();
				converters.add(converter);
			}
		} catch (Exception exp) {
			log.error("Error while instantate Converters", exp);
		}
		return converters;
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