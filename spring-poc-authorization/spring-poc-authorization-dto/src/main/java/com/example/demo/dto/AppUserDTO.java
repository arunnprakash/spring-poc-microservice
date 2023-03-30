package com.example.demo.dto;


import lombok.Data;

@Data
public class AppUserDTO extends BaseDTO {
	private String userName;
	private String password;
}
