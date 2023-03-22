package com.example.demo.json.convertors;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Component
@Slf4j
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	@Value("${application.datetime.format:yyyy-MM-dd HH:mm:ss}")
	private String dateFormatPattern;

	private DateTimeFormatter dateTimeFormatter;

	@PostConstruct
	private void init() {
		dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatPattern).withZone(ZoneId.systemDefault());
	}

	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		if (jp.getValueAsString() != null && !jp.getValueAsString().isEmpty()) {
			log.debug("De-Serialize Date from String {}", jp.getValueAsString());
			return LocalDateTime.parse(jp.getValueAsString(), dateTimeFormatter);
		} else {
			return null;
		}
	}
}