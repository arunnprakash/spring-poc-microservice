package com.example.demo.controller;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.UserService;

import reactor.core.publisher.Mono;


/**
 * @author __ArunPrakash__
 *
 */
@RestController
@RequestMapping(path = "/v1/users")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@GetMapping(produces = TEXT_EVENT_STREAM_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<UserResponse> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping(value = {"/add"}, produces = TEXT_EVENT_STREAM_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<UserResponse> addUser(@RequestBody UserRequest userRequest) {
		return userService.addUser(userRequest);
	}
	
	@PostMapping(value = {"/update"}, produces = TEXT_EVENT_STREAM_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<UserResponse> updateUser(@RequestBody UserRequest userRequest) {
		return userService.updateUser(userRequest);
	}
}
