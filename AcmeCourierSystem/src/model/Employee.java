package model;

import java.io.Serializable;

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The name of the employee
	 */
	public String name;

	/**
	 * The ID number of the employee.
	 */
	public int employeeID;

	/**
	 * whether the employee is a Courier or an OrderTaker
	 */
	public EmployeeRole role;
	public String userName;
	public String password;

	public Employee(String name, int id, EmployeeRole role) {
		this.name = name;
		employeeID = id;
		this.role = role;
	}

	public Employee() {
		this.name = "Administrator";
		employeeID = 1;
		this.role = EmployeeRole.Administrator;
		this.userName = "Admin";
		this.password = "password";
	}
}