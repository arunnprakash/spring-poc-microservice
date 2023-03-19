package com.example.demo.dao;
/**
 * @author __ArunPrakash.B__
 *
 */
public final class EmployeeQueryConstant {
	private EmployeeQueryConstant() {}

	public static final Query TRANSACTION_SUMMARY_QUERY = Query
			.builder()
				.oracle("SELECT * FROM EMPLOYEE")
				.mssql("SELECT * FROM EMPLOYEE")
				.postgres("SELECT * FROM EMPLOYEE")
			.build();
}