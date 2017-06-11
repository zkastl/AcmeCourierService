package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * All the information associated with delivering a package from one client to
 * another.
 */
public class Delivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The time the delivery was created.
	 */
	public Date creationTime;
	/**
	 * The OrderTaker who created the delivery.
	 */
	public Employee orderTaker;
	/**
	 * The client who the package will be picked up from
	 */
	public Client pickupClient;
	/**
	 * The client the package will be delivered to
	 */
	public Client deliveryClient;
	/**
	 * The time the package is supposed to be picked up.
	 */
	public LocalDate requestedPickupTime;
	/**
	 * The estimated time at which the package will be delivered to the client
	 */
	public LocalDate estimatedDeliveryTime;
	/**
	 * The time at which the courier should leave in order to pick up the
	 * package at the requested time.
	 */
	public LocalDate calculatedDepartureTime;
	/**
	 * The time the courier actually left to go pick up the package
	 */
	public LocalDate actualDepartureTime;
	/**
	 * The time at which the courier actually picked up the package
	 */
	public LocalDate actualPickupTime;
	/**
	 * The time at which the courier actually delivered the package
	 */
	public LocalDate actualDeliveryTime;
	/**
	 * The time at which the courier returned to the company
	 */
	public LocalDate actualReturnTime;
	/**
	 * True if the client sending the package should be billed. False if the
	 * client receiving the package should be billed.
	 */
	public boolean billToSender;
	/**
	 * Unique ID written on the package to ensure the courier picks up the
	 * correct one.
	 */
	public int packageID;
	/**
	 * Estimation of the total distance traveled during the delivery. Used to
	 * calculate price.
	 */
	public double estimatedDistanceTraveled;
	/**
	 * The price that will be billed to a client.
	 */
	public double totalDeliveryCost;
	/**
	 * The courier who will make the delivery
	 */
	public Courier assignedCourier;
	/**
	 * True if the courier earned a bonus. False if the courier did not earn a
	 * bonus.
	 */
	public boolean bonusEarned;

	public DeliveryStatus status;

	/**
	 * Calculate if the delivery was on time
	 */
	public void calculateDeliveryStatistics() {
		// TODO - implement Delivery.calculateDeliveryStatistics
		throw new UnsupportedOperationException();
	}

	/**
	 * calculates the shortest routes from the courierStart to the pickupClient,
	 * from the pickupClient to the deliveryClient, and from the deliveryClient
	 * back to the courierStart
	 */
	public void calculateRoutes() {
		// TODO - implement Delivery.calculateRoutes
		throw new UnsupportedOperationException();
	}

}