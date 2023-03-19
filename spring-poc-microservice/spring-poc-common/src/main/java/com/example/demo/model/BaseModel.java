package com.example.demo.model;

import java.time.ZonedDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
public class BaseModel {
	@Id
	private Long id;
	
	@CreatedDate
	private ZonedDateTime createdDate;
	
	@LastModifiedDate
	private ZonedDateTime modifiedDate;
}
