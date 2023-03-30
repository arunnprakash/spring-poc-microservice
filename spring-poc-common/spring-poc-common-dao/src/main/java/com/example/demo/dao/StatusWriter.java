package com.example.demo.dao;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import com.example.demo.constant.Status;

/**
 * @author __ArunPrakash__
 *
 */
@WritingConverter
public class StatusWriter implements Converter<Status, String> {

	@Override
	public String convert(Status status) {
		return status != null? status.getStatusCode() : null;
	}
}