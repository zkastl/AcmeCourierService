package model;

/**
 * An Employee of the company who takes orders from customers.
 */
public class OrderTaker extends Employee {

	/**
	 * the name of the OrderTaker
	 */
	private String name;
	/**
	 * a unique id assigned to the OrderTaker by the system
	 */
	private int employeeID;
	/**
	 * The username the OrderTaker uses to log in to the system.
	 */
	private String username;
	/**
	 * The password the OrderTaker uses to log in to the system.
	 */
	private String password;
	/**
	 * The role the OrderTaker has in the company, currently whether or not they
	 * have admin priviledges
	 */
	private String role;

}