package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Employee;

/**
 * @author __ArunPrakash__
 *
 */
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

	List<Employee> findByName(String name);
	
	@Modifying
	@Query("UPDATE employee SET status ='inactive' WHERE ID = :id")
	void updateStatusInactive(Long id);
}