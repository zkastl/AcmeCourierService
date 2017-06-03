package model;

import java.util.Date;

/**
 * the accumulated charges a client has accrued over a period of time, usually a
 * week.
 */
public class Bill {

	/**
	 * The client the bill will be sent to
	 */
	private Client client;
	/**
	 * The first date the bill covers
	 */
	private Date billStart;
	/**
	 * the last date the bill covers
	 */
	private Date billEnd;
	/**
	 * The deliveries the bill is for
	 */
	private Delivery[] deliveries;
	/**
	 * The total amount the client owes for all the deliveries during the
	 * specified time period
	 */
	private double amountOwed;
	/**
	 * unique id the system assigned to the bill
	 */
	private int invoiceNumber;

	/**
	 * add the specified amount to the amount owed by the client
	 * 
	 * @param amountOwed
	 */
	public void incrementAmountOwed(double amountOwed) {
		// TODO - implement Bill.incrementAmountOwed
		throw new UnsupportedOperationException();
	}

	/**
	 * outputs the bill to a file that can be printed and sent to the client
	 */
	public void print() {
		// TODO - implement Bill.print
		throw new UnsupportedOperationException();
	}

}