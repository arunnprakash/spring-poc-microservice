package com.example.demo.model;

import lombok.Data;

/**
 * @author __ArunPrakash__
 *
 */
@Data
public class Employee extends BaseModel {
	
	private String name;
	
	private Status status;
}
