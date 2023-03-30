package com.example.demo.controller;

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
import com.example.demo.dto.UserResponse.UserResponseHolder;
import com.example.demo.service.UserService;


/**
 * @author __ArunPrakash__
 *
 */
@RestController
@RequestMapping(path = "/v1/users")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public UserResponse getAllUsers() {
		UserResponseHolder userResponseHolder = userService.getAllUsers();
		return UserResponse.builder().response(userResponseHolder).build();
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse addUser(@RequestBody UserRequest userRequest) {
		UserResponseHolder userResponseHolder = userService.addUser(userRequest.getRequest().getBody());
		return UserResponse.builder().response(userResponseHolder).build();
	}
	
	@PostMapping("/update")
	@ResponseStatus(HttpStatus.OK)
	public UserResponse updateUser(@RequestBody UserRequest userRequest) {
		UserResponseHolder userResponseHolder = userService.updateUser(userRequest.getRequest().getBody());
		return UserResponse.builder().response(userResponseHolder).build();
	}
}
