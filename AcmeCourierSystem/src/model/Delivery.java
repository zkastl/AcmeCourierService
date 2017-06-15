package model;

import java.io.Serializable;
import java.time.*;

import javax.persistence.*;

/**
 * All the information associated with delivering a package from one client to
 * another.
 */
@Entity(name="Deliveries")
public class Delivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int packageID;
	
	/**
	 * The time the delivery was created.
	 */
	public LocalDateTime creationTime;
	
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
	public LocalDateTime requestedPickupTime;
	
	/**
	 * The estimated time at which the package will be delivered to the client
	 */
	public LocalDateTime estimatedDeliveryTime;
	
	/**
	 * The time at which the courier should leave in order to pick up the
	 * package at the requested time.
	 */
	public LocalDateTime calculatedDepartureTime;
	
	/**
	 * The time the courier actually left to go pick up the package
	 */
	public LocalDateTime actualDepartureTime;
	
	/**
	 * The time at which the courier actually picked up the package
	 */
	public LocalDateTime actualPickupTime;
	
	/**
	 * The time at which the courier actually delivered the package
	 */
	public LocalDateTime actualDeliveryTime;
	
	/**
	 * The time at which the courier returned to the company
	 */
	public LocalDateTime actualReturnTime;
	
	/**
	 * True if the client sending the package should be billed. False if the
	 * client receiving the package should be billed.
	 */
	public boolean billToSender;
	
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
	public Employee assignedCourier;
	
	/**
	 * True if the courier earned a bonus. False if the courier did not earn a
	 * bonus.
	 */
	public boolean bonusEarned;
	

	public DeliveryStatus status;
	
	public Delivery() {
		creationTime = LocalDateTime.now();
	}

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