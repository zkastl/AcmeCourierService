package model;

import java.io.Serializable;

import main.CourierSystem;

/**
 * Global variables used in calculations, some of which may be changed by an
 * administrator
 */
public class Settings implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1630360224524076087L;

	/**
	 * The base price for a delivery
	 */
	public Double baseCost;
	
	/**
	 * The price per block traveled that is added to the base price for a
	 * delivery
	 */
	public Double pricePerBlock;
	
	/**
	 * The amount of non-travel time for pickup and delivery that is planned
	 * into a delivery
	 */
	public Double plannedNonTravelTime;
	
	/**
	 * The intersection of the company that couriers depart from and return to.
	 */
	public Intersection courierStartAddress;
	
	/**
	 * The average speed at which a courier travels in miles per hour. Used in delivery time
	 * calculations
	 */
	public Double averageCourierSpeed;
	
	public Integer blocksPerMile;
	
	/**
	 * The time in minutes before and after the requested pickup and estimated
	 * delivery times during which a delivery is considered on time. Default is
	 * 5minutes.
	 */
	public Integer bonusLeeway;
	
	public Double bonusAmount;
	
	public Settings() {
	}
	
	public void setDefaultValues() {
		averageCourierSpeed = 5.0;
		baseCost = 10.00;
		blocksPerMile = 10;
		bonusAmount = 2.00;
		bonusLeeway = 5;
		courierStartAddress = CourierSystem.CityMap.getIntersection("D4");
		plannedNonTravelTime = 5.0;
		pricePerBlock = 2.00;
	}
}