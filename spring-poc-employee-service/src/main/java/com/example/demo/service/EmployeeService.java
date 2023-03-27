/**
 * 
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

/**
 * @author __ArunPrakash__
 *
 */
public interface EmployeeService {

	List<Employee> getAllEmployees();

	Employee updateEmployee(Employee employee);

	Employee addEmployee(Employee employee);

}
