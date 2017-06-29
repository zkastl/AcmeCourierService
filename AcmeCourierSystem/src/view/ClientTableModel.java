package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import main.CourierSystem;
import model.Client;

public final class ClientTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	public List<Client> clients;

	public ClientTableModel() {
		super(new Object[] { "ID", "Name", "Phone #", "Street", "Ave", "Instructions", "Email Address" }, 0);
		clients = new ArrayList<Client>();

		for (Client c : CourierSystem.Clients.values()) {
			if (!c.getIsArchived()) {
				addRow(c);
			}
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return int.class;
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
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return clients.get(rowIndex).clientID;
		default:
			return super.getValueAt(rowIndex, columnIndex);
		}

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);

		switch (columnIndex) {
		case 1:
			clients.get(rowIndex).name = aValue.toString();
			break;
		case 2:
			clients.get(rowIndex).phoneNumber = aValue.toString();
			break;
		case 3:
			clients.get(rowIndex).trueAddress.setStreet(aValue.toString());
			break;
		case 4:
			clients.get(rowIndex).trueAddress.setAve(aValue.toString());
			break;
		case 5:
			clients.get(rowIndex).dropoffInstructions = aValue.toString();
			break;
		case 6:
			clients.get(rowIndex).emailAddress = aValue.toString();
			break;
		default:
			break;
		}
	}

	public void addRow(Client client) {
		super.addRow(new Object[] { client.clientID, client.name, client.phoneNumber, client.trueAddress.getStreet(),
				client.trueAddress.getAve(), client.dropoffInstructions, client.emailAddress });
		clients.add(client);
	}

	public void removeRow(int rowNumber) {
		super.removeRow(rowNumber);
		clients.remove(rowNumber);
	}

	public void refresh() {
		while (clients.size() > 0) {
			removeRow(0);
		}

		for (Client c : CourierSystem.Clients.values()) {
			if (!c.getIsArchived()) {
				addRow(c);
			}
		}
	}

}
