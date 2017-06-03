package model;

/**
 * Global variables used in calculations, some of which may be changed by an
 * administrator
 */
public class Settings {

	/**
	 * The base price for a delivery
	 */
	private double baseCost;
	/**
	 * The price per block traveled that is added to the base price for a
	 * delivery
	 */
	private double pricePerBlock;
	/**
	 * The amount of non-travel time for pickup and delivery that is planned
	 * into a delivery
	 */
	private double plannedNonTravelTime;
	/**
	 * The intersection of the company that couriers depart from and return to.
	 */
	private Intersection courierStartAddress;
	/**
	 * The average speed at which a courier travels. Used in delivery time
	 * calculations
	 */
	private double averageCourierSpeed;
	/**
	 * The time in minutes before and after the requested pickup and estimated
	 * delivery times during which a delivery is considered on time. Default is
	 * 5minutes.
	 */
	private int bonusLeeway;
	private double bonusAmount;
	/**
	 * the id that will be assigned to the next employee, client, or package
	 */
	private int nextID;

}