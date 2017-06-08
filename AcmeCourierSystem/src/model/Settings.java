package model;

/**
 * Global variables used in calculations, some of which may be changed by an
 * administrator
 */
public class Settings {

	/**
	 * The base price for a delivery
	 */
	public static double baseCost;
	
	/**
	 * The price per block traveled that is added to the base price for a
	 * delivery
	 */
	public static double pricePerBlock;
	
	/**
	 * The amount of non-travel time for pickup and delivery that is planned
	 * into a delivery
	 */
	public static double plannedNonTravelTime;
	
	/**
	 * The intersection of the company that couriers depart from and return to.
	 */
	public static Intersection courierStartAddress;
	
	/**
	 * The average speed at which a courier travels. Used in delivery time
	 * calculations
	 */
	public static double averageCourierSpeed;
	
	/**
	 * The time in minutes before and after the requested pickup and estimated
	 * delivery times during which a delivery is considered on time. Default is
	 * 5minutes.
	 */
	public static int bonusLeeway;
	
	public static double bonusAmount;
	
	/**
	 * the id that will be assigned to the next employee, client, or package
	 */
	public static int nextID;
	
	// Settings to point to the locations for the persistent data.
	public static String EmployeeFile = "Employees.xml";
	public static String CourierFile = "Couriers.xml";
	public static String ClientFile = "Clients.xml";
	public static String DeliveriesFile = "Deliveries.xml";
}