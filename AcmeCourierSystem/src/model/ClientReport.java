package model;

import java.util.Date;

public class ClientReport {

	/**
	 * The client the report is for
	 */
	private Client client;
	/**
	 * The first date the report covers
	 */
	private Date reportStart;
	/**
	 * The last date the report covers
	 */
	private Date reportEnd;
	/**
	 * the percentage of deliveries the client has received that were on time
	 */
	private double onTimePercentage;

	/**
	 * generates a report for the selected client
	 * 
	 * @param client
	 * @param reportStart
	 * @param reportEnd
	 */
	public ClientReport GenerateClientReport(Client client, Date reportStart, Date reportEnd) {
		// TODO - implement ClientReport.GenerateClientReport
		throw new UnsupportedOperationException();
	}

	/**
	 * returns the client the report is for
	 */
	public Client getClient() {
		return this.client;
	}

	/**
	 * sets the client the report is for
	 * 
	 * @param Client
	 */
	public void setClient(Client Client) {
		this.client = Client;
	}

	/**
	 * returns the first date covered by the report
	 */
	public Date getReportStart() {
		return this.reportStart;
	}

	/**
	 * sets the first date covered by the report
	 * 
	 * @param reportStart
	 */
	public void setReportStart(Date reportStart) {
		this.reportStart = reportStart;
	}

	/**
	 * returns the last day covered by the report
	 */
	public Date getReportEnd() {
		return this.reportEnd;
	}

	/**
	 * sets the last day covered by the report
	 * 
	 * @param reportEnd
	 */
	public void setReportEnd(Date reportEnd) {
		this.reportEnd = reportEnd;
	}

	/**
	 * returns the percentage of deliveries for the client that were on time
	 */
	public double getOnTimePercentage() {
		return this.onTimePercentage;
	}

	/**
	 * sets the percentage of deliveries for the client that were on time
	 * 
	 * @param onTimePercentage
	 */
	public void setOnTimePercentage(double onTimePercentage) {
		this.onTimePercentage = onTimePercentage;
	}

}