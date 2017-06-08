package model;

/**
 * Global variables used in calculations, some of which may be changed by an
 * administrator
 */
public class Settings {

	/**
	 * The base price for a delivery
	 */
	public double baseCost;
	
	/**
	 * The price per block traveled that is added to the base price for a
	 * delivery
	 */
	public double pricePerBlock;
	
	/**
	 * The amount of non-travel time for pickup and delivery that is planned
	 * into a delivery
	 */
	public double plannedNonTravelTime;
	
	/**
	 * The intersection of the company that couriers depart from and return to.
	 */
	public Intersection courierStartAddress;
	
	/**
	 * The average speed at which a courier travels. Used in delivery time
	 * calculations
	 */
	public double averageCourierSpeed;
	
	/**
	 * The time in minutes before and after the requested pickup and estimated
	 * delivery times during which a delivery is considered on time. Default is
	 * 5minutes.
	 */
	public int bonusLeeway;
	
	public double bonusAmount;
	
	/**
	 * the id that will be assigned to the next employee, client, or package
	 */
	public int nextID;
	
	// Settings to point to the locations for the persistent data.
	public static String EmployeeFile = "..\\data\\Employees.xml";
	public static String CourierFile = "..\\data\\Couriers.xml";
	public static String ClientFile = "..\\data\\Clients.xml";
	public static String DeliveriesFile = "..\\data\\Deliveries.xml";
}