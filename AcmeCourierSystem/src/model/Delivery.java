package model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.internal.NotNull;

import main.CourierSystem;

/**
 * All the information associated with delivering a package from one client to
 * another.
 */
@Entity(name = "Deliveries")
public class Delivery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Route pickupRoute;
	private Route deliveryRoute;
	private Route returnRoute;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int packageID;

	/**
	 * The time the delivery was created.
	 */
	public LocalDateTime creationTime;

	/**
	 * The OrderTaker who created the delivery.
	 */
	@NotNull
	public Employee orderTaker;

	/**
	 * The client who the package will be picked up from
	 */
	@NotNull
	public Client pickupClient;

	/**
	 * The client the package will be delivered to
	 */
	@NotNull
	public Client deliveryClient;

	/**
	 * The time the package is supposed to be picked up.
	 */
	@NotNull
	public LocalDateTime requestedPickupTime;

	/**
	 * The estimated time at which the package will be delivered to the client
	 */
	// @NotNull
	public LocalDateTime estimatedDeliveryTime;

	/**
	 * The time at which the courier should leave in order to pick up the
	 * package at the requested time.
	 */
	// @NotNull
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
	@NotNull
	public boolean billToSender;

	/**
	 * Estimation of the total distance traveled during the delivery. Used to
	 * calculate price.
	 */
	// @NotNull
	public double estimatedDistanceTraveled;

	/**
	 * The price that will be billed to a client.
	 */
	// @NotNull
	public double totalDeliveryCost;

	/**
	 * The courier who will make the delivery
	 */
	@NotNull
	public Employee assignedCourier;

	/**
	 * True if the courier earned a bonus. False if the courier did not earn a
	 * bonus.
	 */
	@NotNull
	public boolean bonusEarned;

	@NotNull
	public DeliveryStatus status;

	public Delivery() {
		creationTime = LocalDateTime.now();
		orderTaker = CourierSystem.currentUser;
	}

	/**
	 * Calculate if the delivery was on time
	 */
	public void calculateDeliveryStatistics() {
		calculateRoutes();
		calculatedDepartureTime = requestedPickupTime.minus(pickupRoute.getTime());
		estimatedDeliveryTime = requestedPickupTime.plus(deliveryRoute.getTime());
		estimatedDistanceTraveled = pickupRoute.getDistance() + deliveryRoute.getDistance();
	}

	/**
	 * calculates the shortest routes from the courierStart to the pickupClient,
	 * from the pickupClient to the deliveryClient, and from the deliveryClient
	 * back to the courierStart
	 */
	public void calculateRoutes() {
		pickupRoute = CourierSystem.CityMap.getRoute(Settings.courierStartAddress, pickupClient.trueAddress);
		pickupRoute.calculateDistance();
		pickupRoute.calculateTime();
		deliveryRoute = CourierSystem.CityMap.getRoute(pickupClient.trueAddress, deliveryClient.trueAddress);
		deliveryRoute.calculateDistance();
		deliveryRoute.calculateTime();
	}
	
	public void calculateBonus() {
		long actualTimeInTransit = actualDeliveryTime.toEpochSecond(null) - actualPickupTime.toEpochSecond(null);
		long estimatedTimeInTransit = estimatedDeliveryTime.toEpochSecond(null) - requestedPickupTime.toEpochSecond(null);
		int differenceInMinutesAsInt = (int) (Math.abs(actualTimeInTransit - estimatedTimeInTransit) / 60);
		if (differenceInMinutesAsInt < Settings.bonusLeeway) {
			bonusEarned = true;
		}
		else {
			bonusEarned = false;
		}
	}

	public Route getPickupRoute() {
		return pickupRoute;
	}

	public Route getDeliveryRoute() {
		return deliveryRoute;
	}

	public Route getReturnRoute() {
		return returnRoute;
	}
}