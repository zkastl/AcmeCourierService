package view;
//https://github.com/LGoodDatePicker/LGoodDatePicker.git

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.table.DefaultTableModel;

import main.CourierSystem;
import model.Delivery;
import model.DeliveryStatus;
import model.Employee;

public class DeliveryTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	public List<Delivery> deliveries;

	public DeliveryTableModel() {
		super(new Object[] { "ID", "From", "To", "Pickup", "Depart", "Status", "Assigned Courier" }, 0);

		deliveries = new ArrayList<Delivery>();
		for (Delivery d : CourierSystem.Deliveries.values()) {
			if (d.status != DeliveryStatus.Canceled) {
				addRow(d);
			}
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 5:
			return DeliveryStatus.class;
		case 6:
			return Employee.class;
		default:
			return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 5:
		case 6:
			return true;
		default:
			return false;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (deliveries.size() <= rowIndex)
			return null;

		switch (columnIndex) {
		case 0:
			return String.valueOf(deliveries.get(rowIndex).packageID);
		case 1:
			return deliveries.get(rowIndex).pickupClient;
		case 2:
			return deliveries.get(rowIndex).deliveryClient;
		case 3:
			LocalDateTime pickupTime = deliveries.get(rowIndex).requestedPickupTime;
			if (pickupTime != null)
				return pickupTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy h:mm a"));
			break;
		case 4:
			LocalDateTime departTime = deliveries.get(rowIndex).calculatedDepartureTime;
			if (departTime != null)
				return departTime.format(DateTimeFormatter.ofPattern("h:mm a"));
			break;
		case 5:
			return deliveries.get(rowIndex).status;
		case 6:
			return deliveries.get(rowIndex).assignedCourier;
		}
		return super.getValueAt(rowIndex, columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (aValue == null)
			return;

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		formatter = formatter.withLocale(Locale.US);

		switch (columnIndex) {
		case 5:
			deliveries.get(rowIndex).status = DeliveryStatus.valueOf(aValue.toString());
			break;
		case 6:
			deliveries.get(rowIndex).assignedCourier = CourierSystem.Employees.get(aValue);
		default:
			break;
		}
	}

	public void addRow(Delivery delivery) {
		super.addRow(new Object[] { delivery.packageID, delivery.pickupClient, delivery.deliveryClient,
				delivery.requestedPickupTime, delivery.calculatedDepartureTime, delivery.status,
				delivery.assignedCourier });
		deliveries.add(delivery);
	}

	public void removeRow(int rowNumber) {
		super.removeRow(rowNumber);
		if (deliveries.size() > rowNumber)
			deliveries.remove(rowNumber);
	}

	public void refresh() {
		int lastRowIndex = deliveries.size() - 1;
		deliveries.clear();
		for (Delivery d : CourierSystem.Deliveries.values()) {
			if (d.status != DeliveryStatus.Canceled)
				deliveries.add(d);
		}
		fireTableRowsUpdated(0, lastRowIndex);
		while (getRowCount() > deliveries.size()) {
			removeRow(lastRowIndex);
			lastRowIndex--;
		}
	}
}
