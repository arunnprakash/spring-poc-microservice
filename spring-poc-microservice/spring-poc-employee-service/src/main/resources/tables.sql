DROP TABLE IF EXISTS EMPLOYEE;

CREATE TABLE EMPLOYEE (
ID INT PRIMARY KEY,
NAME VARCHAR(200) NOT NULL,
STATUS VARCHAR(1) NOT NULL,
CREATED_DATE TIME WITH TIME ZONE,
MODIFIED_DATE TIME WITH TIME ZONE
);
