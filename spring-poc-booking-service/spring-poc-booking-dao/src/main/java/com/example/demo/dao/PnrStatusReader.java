package com.example.demo.dao;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import com.example.demo.constant.PnrStatus;

/**
 * @author __ArunPrakash__
 *
 */
@ReadingConverter
public class PnrStatusReader implements Converter<String, PnrStatus> {

	@Override
	public PnrStatus convert(String statusCode) {
		return PnrStatus.getStatus(statusCode);
	}
}