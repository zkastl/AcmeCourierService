package view;
//https://github.com/LGoodDatePicker/LGoodDatePicker.git

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
		super(new Object[] { "ID", "From", "To", "Date", "Depart", "Pickup", "Deliver", "Return", "Status", "Courier" },
				0);
		deliveries = new ArrayList<Delivery>();

		for (Delivery d : CourierSystem.Deliveries.values()) {
			if (d.status == DeliveryStatus.Requested) {
				addRow(d);
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
		case 7:
		case 8:
		case 9:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy hh mm");
		formatter = formatter.withLocale(Locale.US);
		
		switch (columnIndex) {
		case 7:
			deliveries.get(rowIndex).actualReturnTime = LocalDateTime.parse(aValue.toString(), formatter);
			break;
		case 8:
			deliveries.get(rowIndex).status = DeliveryStatus.valueOf(aValue.toString());
			break;
		case 9:
			deliveries.get(rowIndex).assignedCourier = CourierSystem.Employees.get(aValue);
		default:
			break;
		}
	}

	public void addRow(Delivery delivery) {
		super.addRow(new Object[] { delivery.packageID, delivery.pickupClient.name, delivery.deliveryClient.name,
				delivery.requestedPickupTime.toLocalDate().toString(),
				delivery.calculatedDepartureTime.toLocalTime().toString(),
				delivery.requestedPickupTime.toLocalTime().toString(),
				delivery.estimatedDeliveryTime.toLocalTime().toString(),
				delivery.actualReturnTime.toLocalTime().toString(), delivery.status, delivery.assignedCourier.name });
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
