package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import main.CourierSystem;

/**
 * the accumulated charges a client has accrued over a period of time, usually a
 * week.
 */
public class Bill {

	public static String generateBill(LocalDate startDate, LocalDate endDate) {

		StringBuilder report = new StringBuilder();
		report.append(getHeader(startDate, endDate));

		for (Client c : CourierSystem.Clients.values()) {
			report.append(c.clientID + "," + c.name + ",," + getClientTotal(c, startDate, endDate) + "\n");
		}

		return report.toString();
	}

	/**
	 * outputs the bill to a file that can be printed and sent to the client
	 */
	public static String generateBill(Client client, LocalDate startDate, LocalDate endDate) {
		StringBuilder report = new StringBuilder();
		report.append(getHeader(startDate, endDate));
		report.append(client.clientID + "," + client.name + ",," + getClientTotal(client, startDate, endDate));

		return report.toString();
	}

	private static String getHeader(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
		StringBuilder header = new StringBuilder();
		header.append("Start Date:," + startDate.format(dateFormat) + "\n");
		header.append("End Date:," + endDate.format(dateFormat) + "\n\n");
		header.append("Client Id, Name,, Total\n");

		return header.toString();
	}

	private static String getClientTotal(Client client, LocalDate startDate, LocalDate endDate) {
		double total = 0;

		for (Delivery delivery : CourierSystem.Deliveries.values()) {
			if (delivery.status == DeliveryStatus.Completed && delivery.getBilledClient().clientID == client.clientID) {

				LocalDate date = delivery.requestedPickupTime.toLocalDate();
				if (date.isAfter(startDate.minusDays(1)) && date.isBefore(endDate.plusDays(1))) {
					total += delivery.totalDeliveryCost;
				}
			}
		}

		return "$" + Double.toString(total);
	}
}