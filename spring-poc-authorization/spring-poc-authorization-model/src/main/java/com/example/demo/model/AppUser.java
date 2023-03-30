package com.example.demo.model;

import com.example.demo.constant.Status;

import lombok.Data;

/**
 * @author __ArunPrakash__
 *
 */
@Data
public class AppUser extends BaseModel {
	
	private String userName;
	
	private String password;
	
}
