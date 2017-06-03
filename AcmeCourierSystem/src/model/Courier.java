package model;

import java.util.Date;

/**
 * An Employee of the company who delivers packages.
 */
public class Courier extends Employee {

	/**
	 * the name of the courier
	 */
	private String name;
	/**
	 * a unique id assigned to the courier by the system
	 */
	private int employeeID;
	/**
	 * All deliveries that the courier has made.
	 */
	private Delivery[] deliveryHistory;

	/**
	 * returns the percent the courier made on time deliveries during a
	 * specified time period
	 * 
	 * @param start
	 * @param end
	 */
	public double getDeliveryOnTimePercentage(Date start, Date end) {
		// TODO - implement Courier.getDeliveryOnTimePercentage
		throw new UnsupportedOperationException();
	}

	/**
	 * The number of bonuses a courier should receive for a specified time
	 * period
	 * 
	 * @param start
	 * @param end
	 */
	public int getBonuses(Date start, Date end) {
		// TODO - implement Courier.getBonuses
		throw new UnsupportedOperationException();
	}

}