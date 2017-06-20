package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import main.CourierSystem;
import model.Client;
import model.Intersection;

public final class ClientTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	public List<Client> clients;

	public ClientTableModel() {
		super(new Object[] { "ID", "Name", "Phone #", "Street", "Ave", "Instructions", "Email Address" }, 0);
		clients = new ArrayList<Client>();

		for (Client c : CourierSystem.Clients.values()) {
			if (!c.getIsArchived()) {
				super.addRow(new Object[] { c.clientID, c.name, c.phoneNumber, c.trueAddress.getStreet(),
						c.trueAddress.getAve(), c.dropoffInstructions, c.emailAddress });
				clients.add(c);
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
			clients.get(rowIndex).trueAddress.setStreet(aValue.toString().substring(0, 1));
			break;
		case 4:
			clients.get(rowIndex).trueAddress.setAve(aValue.toString().substring(0, 1));
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
		clients.clear();
		for (Client c : CourierSystem.Clients.values()) {
			if (!c.getIsArchived()) {
				clients.add(c);
			}
		}
		fireTableRowsUpdated(0, clients.size() - 1);
	}

}
