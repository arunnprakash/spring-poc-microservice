package com.example.demo;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

import com.example.demo.model.StatusReader;
import com.example.demo.model.StatusWriter;

/**
 * @author __ArunPrakash__
 *
 */
@Configuration
class JdbcConfiguration extends AbstractJdbcConfiguration {

	@Override
	protected List<Converter<?,?>> userConverters() {
		return asList(new StatusWriter(), new StatusReader());
	}

}