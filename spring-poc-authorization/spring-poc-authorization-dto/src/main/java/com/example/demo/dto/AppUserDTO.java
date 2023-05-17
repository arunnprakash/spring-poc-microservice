package com.example.demo.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AppUserDTO extends BaseDTO {

	@Schema(description = "UserName", minLength = 4, maxLength = 20)
	private String userName;
	
	@Schema(description = "Password", minLength = 4, maxLength = 20)
	private String password;
}
