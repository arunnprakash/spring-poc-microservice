package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.dto.LoginResponse.LoginResponseHolder;
import com.example.demo.service.LoginService;

/**
 * @author __ArunPrakash__
 *
 */
@RestController
@RequestMapping(path = "/v1/login")
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public LoginResponse login(@RequestBody LoginRequest loginRequest) {
		LoginResponseHolder loginResponseHolder = loginService.doLogin(loginRequest.getRequest().getBody());
		return LoginResponse.builder().response(loginResponseHolder).build();
	}

}
