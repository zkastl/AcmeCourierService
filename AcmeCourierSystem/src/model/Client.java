package model;

/**
 * an entity that is served by the courier company
 */
public class Client {

	/**
	 * The name of the client
	 */
	private String name;
	/**
	 * The phone number of the client
	 */
	private String phoneNumber;
	/**
	 * The unique ID number assigned to the client
	 */
	private int clientID;
	/**
	 * The email address of the client
	 */
	private String emailAddress;
	/**
	 * Special instructions couriers are to follow when picking up or dropping
	 * off a package.
	 */
	private String dropoffInstructions;
	/**
	 * deliveries associated with the client
	 */
	private Delivery[] deliveryHistory;
	/**
	 * The intersection the client is located at where packages are picked up
	 * from and delivered to
	 */
	private Intersection address;

}