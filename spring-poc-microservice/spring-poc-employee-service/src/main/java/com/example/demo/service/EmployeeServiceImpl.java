package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.model.Employee;
import com.google.common.collect.Lists;

/**
 * @author __ArunPrakash__
 *
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
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
