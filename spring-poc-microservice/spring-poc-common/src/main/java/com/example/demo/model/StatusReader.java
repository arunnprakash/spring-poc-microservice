package com.example.demo.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * @author __ArunPrakash__
 *
 */
@ReadingConverter
public class StatusReader implements Converter<String, Status> {

	@Override
	public Status convert(String statusCode) {
		return Status.getStatus(statusCode);
	}
}