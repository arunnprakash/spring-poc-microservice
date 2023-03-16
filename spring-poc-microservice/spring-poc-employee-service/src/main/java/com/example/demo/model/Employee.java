package com.example.demo.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * @author __ArunPrakash__
 *
 */
@Data
public class Employee {
	@Id
	private Long id;
	
	private String name;
	
	private String status;
}
