package com.example.demo.controller;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.service.LoginService;

import reactor.core.publisher.Mono;

/**
 * @author __ArunPrakash__
 *
 */
@RestController
@RequestMapping(path = "/v1/login")
public class LoginController extends BaseController {

	@Autowired
	private LoginService loginService;

	@PostMapping(produces = TEXT_EVENT_STREAM_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		return loginService.doLogin(loginRequest);
	}

}
