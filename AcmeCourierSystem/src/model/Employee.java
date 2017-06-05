package model;

import java.io.*;
import java.beans.*;

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The name of the employee
	 */
	private String name;
	
	/**
	 * The ID number of the employee.
	 */
	private int employeeID;
	
	/**
	 * whether the employee is a Courier or an OrderTaker
	 */
	private String role;
	
	public Employee(String name, int id, String role) {
		this.setName(name);
		setEmployeeID(id);
		this.setRole(role);
	}
	
	public Employee() {
		this.setName("null");
		setEmployeeID(0);
		this.setRole("null");
	}
	
	public void SaveEmployee() throws FileNotFoundException, IOException {
		XMLEncoder e = new XMLEncoder(
				new BufferedOutputStream(
						new FileOutputStream("Test2.xml")));
		e.writeObject(new Employee("Katie", 0002, "Sister"));
		e.close();
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Test.xml"));
		oos.writeObject(this);
		oos.flush();
		oos.close();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}