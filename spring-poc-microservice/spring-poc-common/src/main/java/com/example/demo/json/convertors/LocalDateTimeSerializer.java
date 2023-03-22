package com.example.demo.json.convertors;

import java.io.IOException;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Component
@Slf4j
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	@Value("${application.datetime.format:yyyy-MM-dd HH:mm:ss}")
	private String dateFormatPattern;

	private DateTimeFormatter dateTimeFormatter;

	@PostConstruct
	private void init() {
		dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormatPattern).withZone(ZoneId.systemDefault());
	}

	@Override
	public void serialize(final LocalDateTime value, final JsonGenerator jgen, final SerializerProvider provider)
			throws IOException {
		if (value != null) {
			String formattedDate = value.format(dateTimeFormatter);
			log.debug("Serialize Date {} to {}", value, formattedDate);
			jgen.writeString(formattedDate);
		} else {
			jgen.writeString("");
		}
	}
}