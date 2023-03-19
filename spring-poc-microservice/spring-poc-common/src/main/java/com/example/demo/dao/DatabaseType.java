package com.example.demo.dao;
/**
 * @author __ArunPrakash.B__
 *
 */
public enum DatabaseType {
	DERBY, DB2, DB2VSE, DB2ZOS, DB2AS400,
	HSQL, SQLSERVER, MYSQL, ORACLE,
	POSTGRES, SYBASE, H2, SQLITE, HANA, MARIADB;
	
	public boolean isOracle() {
		return this.equals(ORACLE);
	}
	
	public boolean isPostgres() {
		return this.equals(POSTGRES);
	}
	
	public boolean isMssql() {
		return this.equals(SQLSERVER);
	}
}