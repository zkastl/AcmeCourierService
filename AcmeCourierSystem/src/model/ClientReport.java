package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import main.CourierSystem;

public class ClientReport {

	/**
	 * Create a report for all clients displaying their on time percentage
	 * 
	 * @param reportStart
	 * @param reportEnd
	 */
	public static String generateClientReport(LocalDate start, LocalDate end) {
		HashMap<String, Integer> totalDeliveries = new HashMap<String, Integer>();
		HashMap<String, Integer> bonuses = new HashMap<String, Integer>();
		LocalDateTime reportStart = start.atTime(0, 0);
		LocalDateTime reportEnd = end.atTime(23, 59);
		
		// this report is for all clients so add them all in with no stats
		for(Client c : CourierSystem.Clients.values()) {
			totalDeliveries.put(c.name, 0);
			bonuses.put(c.name, 0);
		}
		
		// for each delivery
		for(Delivery delivery : CourierSystem.Deliveries.values()) {
			// if the delivery was completed and is in the period the report is for update client stats
			if(delivery.status.equals(DeliveryStatus.Completed) && delivery.requestedPickupTime.isAfter(reportStart) && delivery.requestedPickupTime.isBefore(reportEnd)) {
				// increment total deliveries
				totalDeliveries.put(delivery.pickupClient.name, totalDeliveries.get(delivery.pickupClient.name) + 1);
				totalDeliveries.put(delivery.deliveryClient.name, totalDeliveries.get(delivery.deliveryClient.name) + 1);
				// if the delivery was on time
				if(delivery.bonusEarned) {
					// increment bonuses and on time deliveries
					bonuses.put(delivery.pickupClient.name, bonuses.get(delivery.pickupClient.name) + 1);
					bonuses.put(delivery.deliveryClient.name, bonuses.get(delivery.deliveryClient.name) + 1);
				}
			}
		}
		
		StringBuilder report = new StringBuilder();
		report.append(getHeader(reportStart.toLocalDate(), reportEnd.toLocalDate()));
		
		for( String client : totalDeliveries.keySet()) {
			Double onTimePercentage;
			onTimePercentage = (totalDeliveries.get(client) == 0 ? 0 : Double.valueOf(bonuses.get(client).toString()) / Double.valueOf(totalDeliveries.get(client).toString()));
			report.append(client+","+totalDeliveries.get(client).toString()+","+onTimePercentage.toString()+"\n");
		}
		
		return report.toString();
	}

	/**
	 * Create a report for a specified courier displaying their on time percentage and
	 * the bonuses they should receive for a specified period
	 * 
	 * @param reportStart
	 * @param reportEnd
	 */
	public static String generateClientReport(Client client, LocalDate start, LocalDate end) {
		Integer totalDeliveries = 0;
		Integer bonuses = 0;
		LocalDateTime reportStart = start.atTime(0, 0);
		LocalDateTime reportEnd = end.atTime(23, 59);
		
		// for each delivery
		for(Delivery delivery : CourierSystem.Deliveries.values()) {
			// if the delivery was for the specified client and is in the period the report is for
			if(delivery.status.equals(DeliveryStatus.Completed) && (delivery.pickupClient.equals(client) || delivery.deliveryClient.equals(client)) && delivery.requestedPickupTime.isAfter(reportStart) && delivery.requestedPickupTime.isBefore(reportEnd)) {
				// increment total deliveries
				totalDeliveries = totalDeliveries + 1;
				// if the delivery was on time
				if(delivery.bonusEarned) {
					// increment bonuses and on time deliveries
					bonuses = bonuses + 1;
				}
			}
		}
		
		Double onTimePercentage;
		if(totalDeliveries > 0) {
			onTimePercentage = Double.valueOf(bonuses.toString()) / Double.valueOf(totalDeliveries.toString());
		} else {
			onTimePercentage = 0.0;
		}
		
		StringBuilder report = new StringBuilder();
		report.append(getHeader(reportStart.toLocalDate(), reportEnd.toLocalDate()));
		report.append(client.name+","+totalDeliveries.toString()+","+onTimePercentage.toString()+"\n");
		
		return report.toString();
	}
	
	private static String getHeader(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
		StringBuilder header = new StringBuilder();
		header.append("Start Date:," + startDate.format(dateFormat) + "\n");
		header.append("End Date:," + endDate.format(dateFormat) + "\n\n");
		header.append("Name,TotalDelivieries,OnTimePercentage\n");

		return header.toString();
	}

}