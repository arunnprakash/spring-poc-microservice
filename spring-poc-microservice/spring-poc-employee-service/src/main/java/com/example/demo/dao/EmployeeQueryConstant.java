package com.example.demo.dao;

/**
 * @author __ArunPrakash.B__
 *
 */
public final class EmployeeQueryConstant {
	private EmployeeQueryConstant() {}

	public static final Query UPDATE_EMPLOYEE_QUERY = Query.of("UPDATE EMPLOYEE SET NAME=:name WHERE ID=:id");
}