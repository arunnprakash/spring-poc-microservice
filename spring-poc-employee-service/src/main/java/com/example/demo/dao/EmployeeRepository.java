package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;

import com.example.demo.model.Employee;

/**
 * @author __ArunPrakash__
 *
 */
public interface EmployeeRepository extends BaseRepository<Employee>, CustomEmployeeRepository {

	List<Employee> findByName(String name);
	
	@Modifying
	@Query("UPDATE employee SET status ='I' WHERE ID = :id")
	void updateStatusInactive(Long id);
}