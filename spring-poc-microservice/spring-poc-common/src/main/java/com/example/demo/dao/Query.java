package com.example.demo.dao;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
//@Component
@Builder
@Slf4j
public final class Query {

	private String queryString;
	private String oracle;
	private String mssql;
	private String postgres;

	//@Value("${spring.datasource.platform}")
	private DatabaseType databaseType;

	//@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public String getQueryString() {
		if (queryString != null) {
			return queryString;
		} else {
			if (databaseType.isOracle()) {
				return oracle;
			} else if (databaseType.isMssql()) {
				return mssql;
			} else {
				return postgres;
			}
		}
	}
	public static Query of(String queryString) {
		return Query.builder()
				.queryString(queryString)
			.build();
	}
	public <T> Stream<T> queryForStream(MapSqlParameterSource paramters) {
		return jdbcTemplate.queryForStream(getQueryString(), paramters, new BeanPropertyRowMapper<T>());
	}
}