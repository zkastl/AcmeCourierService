package model;

import java.util.Date;

/**
 * All the information associated with delivering a package from one client to
 * another.
 */
public class Delivery {

	/**
	 * The time the delivery was created.
	 */
	private Date creationTime;
	/**
	 * The OrderTaker who created the delivery.
	 */
	private OrderTaker orderTaker;
	/**
	 * The client who the package will be picked up from
	 */
	private Client pickupClient;
	/**
	 * The client the package will be delivered to
	 */
	private Client deliveryClient;
	/**
	 * The time the package is supposed to be picked up.
	 */
	private Date requestedPickupTime;
	/**
	 * The estimated time at which the package will be delivered to the client
	 */
	private Date estimatedDeliveryTime;
	/**
	 * The time at which the courier should leave in order to pick up the
	 * package at the requested time.
	 */
	private Date calculatedDepartureTime;
	/**
	 * The time the courier actually left to go pick up the package
	 */
	private Date actualDepartureTime;
	/**
	 * The time at which the courier actually picked up the package
	 */
	private Date actualPickupTime;
	/**
	 * The time at which the courier actually delivered the package
	 */
	private Date actualDeliveryTime;
	/**
	 * The time at which the courier returned to the company
	 */
	private Date actualReturnTime;
	/**
	 * True if the client sending the package should be billed. False if the
	 * client receiving the package should be billed.
	 */
	private boolean billToSender;
	/**
	 * Unique ID written on the package to ensure the courier picks up the
	 * correct one.
	 */
	private int packageID;
	/**
	 * Estimation of the total distance traveled during the delivery. Used to
	 * calculate price.
	 */
	private double estimatedDistanceTraveled;
	/**
	 * The price that will be billed to a client.
	 */
	private double totalDeliveryCost;
	/**
	 * The courier who will make the delivery
	 */
	private Courier assignedCourier;
	/**
	 * True if the courier earned a bonus. False if the courier did not earn a
	 * bonus.
	 */
	private boolean bonusEarned;

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