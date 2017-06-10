package model;

/**
 * an entity that is served by the courier company
 */
public class Client {

	public Client() {
		address = new Intersection("", "");
		name = "";
		phoneNumber = "";
		emailAddress = "";
		dropoffInstructions = "";
	}

	/**
	 * The name of the client
	 */
	public String name;
	/**
	 * The phone number of the client
	 */
	public String phoneNumber;
	/**
	 * The unique ID number assigned to the client
	 */
	public int clientID;
	/**
	 * The email address of the client
	 */
	public String emailAddress;
	/**
	 * Special instructions couriers are to follow when picking up or dropping
	 * off a package.
	 */
	public String dropoffInstructions;
	/**
	 * deliveries associated with the client
	 */
	public Delivery[] deliveryHistory;
	/**
	 * The intersection the client is located at where packages are picked up
	 * from and delivered to
	 */
	public Intersection address;

}