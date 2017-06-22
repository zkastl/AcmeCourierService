package model;

import java.io.Serializable;
import java.util.Date;

/**
 * An Employee of the company who delivers packages.
 */
public class Courier extends Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5875114390978027755L;
	/**
	 * the name of the courier
	 */

	public Delivery[] deliveryHistory;

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

	public Courier() {
		super.name = "";
	}

	public Courier(String s) {
		super.name = s;
	}

	@Override
	public String toString() {
		return super.name;
	}

}