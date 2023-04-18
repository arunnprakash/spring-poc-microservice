package com.example.demo.model;

import static com.example.demo.constant.Status.ACTIVE;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import com.example.demo.constant.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {
	@Id
	private Long id;
	
	private Status status = ACTIVE;
	
	@CreatedDate
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}
