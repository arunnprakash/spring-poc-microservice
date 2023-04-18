package com.example.demo.dao;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import com.example.demo.constant.PnrStatus;

/**
 * @author __ArunPrakash__
 *
 */
@WritingConverter
public class PnrStatusWriter implements Converter<PnrStatus, String> {

	@Override
	public String convert(PnrStatus status) {
		return status != null? status.getStatusCode() : null;
	}
}