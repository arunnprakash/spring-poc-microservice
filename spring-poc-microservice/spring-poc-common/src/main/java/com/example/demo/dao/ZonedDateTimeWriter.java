package com.example.demo.dao;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * @author __ArunPrakash__
 *
 */
@WritingConverter
public class ZonedDateTimeWriter implements Converter<ZonedDateTime, OffsetDateTime> {

	@Override
	public OffsetDateTime convert(ZonedDateTime source) {
		return source.toOffsetDateTime();
	}
}