package com.example.demo.dao;

import java.time.ZonedDateTime;
import java.time.OffsetDateTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * @author __ArunPrakash__
 *
 */
@ReadingConverter
public class ZonedDateTimeReader implements Converter<OffsetDateTime, ZonedDateTime> {

	@Override
	public ZonedDateTime convert(OffsetDateTime source) {
		return source == null ? null : ZonedDateTime.from(source);
	}
}