package model;

import java.util.Date;

/**
 * A report on the performance of all Couriers during a specified period of
 * time.
 */
public class CourierReport {

	/**
	 * The couriers who made at least one delivery during the time period the
	 * report covers.
	 */
	private Courier[] couriers;
	/**
	 * The percentage of deliveries that were on time for each courier during
	 * the specified time period.
	 */
	private double[] onTimePercentages;
	/**
	 * The number of bonuses that each courier should receive
	 */
	private int[] bonuses;
	/**
	 * The first date that the report covers
	 */
	private Date reportStart;
	/**
	 * The last date that the report covers.
	 */
	private Date reportEnd;

	/**
	 * Create a report for all couriers displaying their on time percentage and
	 * the bonuses they should receive for a specified period
	 * 
	 * @param reportStart
	 * @param reportEnd
	 */
	public CourierReport generateCourierReport(Date reportStart, Date reportEnd) {
		// TODO - implement CourierReport.generateCourierReport
		throw new UnsupportedOperationException();
	}

	/**
	 * returns the array of couriers the report is for
	 */
	public Courier[] getCouriers() {
		return this.couriers;
	}

	/**
	 * adds a courier the the array of couriers that the report covers
	 * 
	 * @param courier
	 */
	public void addCourier(Courier courier) {
		// TODO - implement CourierReport.addCourier
		throw new UnsupportedOperationException();
	}

	/**
	 * returns the array of on time percentages of the couriers
	 */
	public double[] getOnTimePercentages() {
		return this.onTimePercentages;
	}

	/**
	 * adds a value to the array of on time percentages of the couriers
	 * 
	 * @param onTimePercentage
	 */
	public void addOnTimePercentage(double onTimePercentage) {
		// TODO - implement CourierReport.addOnTimePercentage
		throw new UnsupportedOperationException();
	}

	/**
	 * returns the array of the number of bonuses that each courier should
	 * receive
	 */
	public int[] getBonuses() {
		return this.bonuses;
	}

	/**
	 * add a bonus to the array of bonuses the couriers should receive
	 * 
	 * @param bonus
	 */
	public void addBonus(int bonus) {
		// TODO - implement CourierReport.addBonus
		throw new UnsupportedOperationException();
	}

	/**
	 * returns the first day the report covers
	 */
	public Date getReportStart() {
		return this.reportStart;
	}

	/**
	 * sets the first day the report covers
	 * 
	 * @param reportStart
	 */
	public void setReportStart(Date reportStart) {
		this.reportStart = reportStart;
	}

	/**
	 * returns the last day the report covers
	 */
	public Date getReportEnd() {
		return this.reportEnd;
	}

	/**
	 * sets the last day the report covers
	 * 
	 * @param reportEnd
	 */
	public void setReportEnd(Date reportEnd) {
		this.reportEnd = reportEnd;
	}

}