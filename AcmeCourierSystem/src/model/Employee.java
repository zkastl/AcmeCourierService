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
	public String role;

	public String userName;

	public String password;

	public Employee(String name, int id, String role) {
		this.name = name;
		employeeID = id;
		this.role = role;
	}

	public Employee() {
		this.name = "Null";
		employeeID = -1;
		this.role = "Nobody";
	}
}