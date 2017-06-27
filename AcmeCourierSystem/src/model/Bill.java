package model;

import java.io.File;
import java.time.LocalDate;

import main.CourierSystem;

/**
 * the accumulated charges a client has accrued over a period of time, usually a
 * week.
 */
public class Bill {

	public static String generateInvoice(LocalDate startDate, LocalDate endDate) {

		for (Client c : CourierSystem.Clients.values()) {

		}

		return null;
	}

	/**
	 * outputs the bill to a file that can be printed and sent to the client
	 */
	public static String generateInvoice(Client client, LocalDate startDate, LocalDate endDate) {

		throw new UnsupportedOperationException();
	}

	private static void invoiceClient(File invoice, Client client) {

	}

}