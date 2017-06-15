package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.table.DefaultTableModel;

import main.CourierSystem;
import model.Delivery;
import model.DeliveryStatus;

public class DeliveryTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	public List<Delivery> deliveries;

	public DeliveryTableModel() {
		super(new Object[] { "ID", "From", "To", "Requested Pickup Time", "Calculated Departure Time", "Departure Time", "Pickup Time",
				"Delivery Time", "Return Time", "Status", "Assigned Courier" }, 0);
		deliveries = new ArrayList<Delivery>();

		for (Delivery d : CourierSystem.Deliveries.values()) {
			if (d.status == DeliveryStatus.Requested) {
				super.addRow(new Object[] { d.packageID, d.pickupClient.name, d.deliveryClient.name, d.requestedPickupTime,
						d.calculatedDepartureTime, d.actualDepartureTime, d.actualPickupTime, d.actualDeliveryTime, d.actualReturnTime,
						d.status, d.assignedCourier });
				deliveries.add(d);
			}
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return int.class;
		case 8:
			return DeliveryStatus.class;
		default:
			return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return false;
		default:
			return true;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		formatter = formatter.withLocale(Locale.US);
		
		switch (columnIndex) {
		case 1:
			deliveries.get(rowIndex).pickupClient = CourierSystem.Clients.get(aValue);
			break;
		case 2:
			deliveries.get(rowIndex).deliveryClient = CourierSystem.Clients.get(aValue);
			break;
		case 3:
			deliveries.get(rowIndex).requestedPickupTime = LocalDateTime.parse(aValue.toString(), formatter);
			break;
		case 4:
			deliveries.get(rowIndex).requestedPickupTime = LocalDateTime.parse(aValue.toString(), formatter);
			break;
		case 5:
			deliveries.get(rowIndex).actualDepartureTime = LocalDateTime.parse(aValue.toString(), formatter);
			break;
		case 6:
			deliveries.get(rowIndex).actualPickupTime = LocalDateTime.parse(aValue.toString(), formatter);
			break;
		case 7:
			deliveries.get(rowIndex).actualDeliveryTime = LocalDateTime.parse(aValue.toString(), formatter);
			break;
		case 8:
			deliveries.get(rowIndex).actualReturnTime = LocalDateTime.parse(aValue.toString(), formatter);
			break;
		case 9:
			deliveries.get(rowIndex).status = DeliveryStatus.valueOf(aValue.toString());
			break;
		case 10:
			deliveries.get(rowIndex).assignedCourier = CourierSystem.Employees.get(aValue);
		default:
			break;
		}
	}

	public void addRow(Delivery delivery) {
		super.addRow(new Object[] { delivery.packageID, delivery.pickupClient, delivery.deliveryClient,
				delivery.requestedPickupTime, delivery.actualDepartureTime, delivery.actualPickupTime,
				delivery.actualDeliveryTime, delivery.actualReturnTime, delivery.status, delivery.assignedCourier });
		deliveries.add(delivery);
	}
	
	public void removeRow(int rowNumber) {
		super.removeRow(rowNumber);
		deliveries.remove(rowNumber);
	}

	public void refresh() {
		deliveries.clear();
		for (Delivery d : CourierSystem.Deliveries.values()) {
			if (d.status == DeliveryStatus.Requested)
				deliveries.add(d);
		}
		fireTableRowsUpdated(0, deliveries.size() - 1);
	}
}
