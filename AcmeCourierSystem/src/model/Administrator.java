package model;

/*
 * The owner of the company who determines the settings and manages employees
 */
public class Administrator extends OrderTaker {

	/**
	 * The username the owner uses to log in to the system.
	 */
	private String username;
	/**
	 * The password the owner uses to log in to the system.
	 */
	private String password;

	/**
	 * Add the information of a new employee to the system.
	 */
	public void addEmployee() {
		// TODO - implement Administrator.addEmployee
		throw new UnsupportedOperationException();
	}

	/**
	 * Change the information of an existing employee in the system.
	 */
	public void editEmployee() {
		// TODO - implement Administrator.editEmployee
		throw new UnsupportedOperationException();
	}

	/**
	 * remove an employee from the system.
	 */
	public void deleteEmployee() {
		// TODO - implement Administrator.deleteEmployee
		throw new UnsupportedOperationException();
	}

	/**
	 * Remove a client from the system.
	 */
	public void deleteClient() {
		// TODO - implement Administrator.deleteClient
		throw new UnsupportedOperationException();
	}

	/**
	 * Change the settings uses in calculations performed by the system, such as
	 * the pricePerBlock traveled, and the amount of non-travel time allowed to
	 * pick up or deliver a package
	 */
	public void editSettings() {
		// TODO - implement Administrator.editSettings
		throw new UnsupportedOperationException();
	}

	/**
	 * returns the username of the owner
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * sets the username of the owner
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * returns the password of the owner
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * sets the password of the owner
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}