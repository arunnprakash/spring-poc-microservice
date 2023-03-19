package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

/**
 * @author __ArunPrakash__
 *
 */
@RestController
@RequestMapping(path = "/v1")
public class EmployeeController extends BaseController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.OK)
	public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
}
