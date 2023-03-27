package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.model.Employee;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * @author __ArunPrakash__
 *
 */
@Slf4j
@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		log.debug("Getting all employees");
		return Lists.newArrayList(employeeRepository.findAll());
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.updateName(employee);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

}
