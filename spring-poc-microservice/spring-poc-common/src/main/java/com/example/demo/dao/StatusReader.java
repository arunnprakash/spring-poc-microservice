package com.example.demo.dao;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.example.demo.model.Status;

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