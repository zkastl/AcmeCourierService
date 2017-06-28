package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Set;

import main.CourierSystem;

/**
 * A report on the performance of all Couriers during a specified period of
 * time.
 */
public class CourierReport {
/**
	 * Create a report for all couriers displaying their on time percentage and
	 * the bonuses they should receive for a specified period
	 * 
	 * @param reportStart
	 * @param reportEnd
	 */
	public static String generateCourierReport(LocalDate start, LocalDate end) {
		HashMap<String, Integer> totalDeliveries = new HashMap<String, Integer>();
		HashMap<String, Integer> bonuses = new HashMap<String, Integer>();
		LocalDateTime reportStart = start.atTime(0, 0);
		LocalDateTime reportEnd = end.atTime(23, 59);
		
		// this report is for all couriers so add them all in with no stats
		for(Employee e : CourierSystem.Employees.values()) {
			if(e.role.compareTo(EmployeeRole.Courier) == 0) {
				totalDeliveries.put(e.name, 0);
				bonuses.put(e.name, 0);
			}
		}
		
		// for each delivery
		for(Delivery delivery : CourierSystem.Deliveries.values()) {
			// if the delivery was completed and is in the period the report is for update courier stats
			if(delivery.status.equals(DeliveryStatus.Completed) && delivery.requestedPickupTime.isAfter(reportStart) && delivery.requestedPickupTime.isBefore(reportEnd)) {
				// increment total deliveries
				totalDeliveries.put(delivery.assignedCourier.name, totalDeliveries.get(delivery.assignedCourier.name) + 1);
				// if the delivery was on time
				if(delivery.bonusEarned) {
					// increment bonuses and on time deliveries
					bonuses.put(delivery.assignedCourier.name, bonuses.get(delivery.assignedCourier.name) + 1);
				}
			}
		}
		
		StringBuilder report = new StringBuilder();
		report.append(getHeader(reportStart.toLocalDate(), reportEnd.toLocalDate()));
		
		for( String courier : totalDeliveries.keySet()) {
			Double onTimePercentage;
			onTimePercentage = (totalDeliveries.get(courier) == 0 ? 0 : Double.valueOf(bonuses.get(courier).toString()) / Double.valueOf(totalDeliveries.get(courier).toString()));
			report.append(courier+","+totalDeliveries.get(courier).toString()+","+onTimePercentage.toString()+","+bonuses.get(courier).toString()+"\n");
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
	public static String generateCourierReport(Employee courier, LocalDate start, LocalDate end) {
		Integer totalDeliveries = 0;
		Integer bonuses = 0;
		LocalDateTime reportStart = start.atTime(0, 0);
		LocalDateTime reportEnd = end.atTime(23, 59);
		
		// for each delivery
		for(Delivery delivery : CourierSystem.Deliveries.values()) {
			// if the delivery was completed by the specified courier and is in the period the report is for
			if(delivery.status.equals(DeliveryStatus.Completed) && delivery.assignedCourier.equals(courier) && delivery.requestedPickupTime.isAfter(reportStart) && delivery.requestedPickupTime.isBefore(reportEnd)) {
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
		report.append(courier.name+","+totalDeliveries.toString()+","+onTimePercentage.toString()+","+bonuses.toString()+"\n");
		
		return report.toString();
	}
	
	private static String getHeader(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
		StringBuilder header = new StringBuilder();
		header.append("Start Date:," + startDate.format(dateFormat) + "\n");
		header.append("End Date:," + endDate.format(dateFormat) + "\n\n");
		header.append("Name,TotalDelivieries,OnTimePercentage,Bonuses\n");

		return header.toString();
	}
	
}