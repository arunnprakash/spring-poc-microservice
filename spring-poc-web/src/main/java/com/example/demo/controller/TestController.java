package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class TestController {

	@GetMapping("/test")
	@ResponseStatus(HttpStatus.OK)
	public String saveCustomers() {
		return "Hello";
	}

}
