package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import main.CourierSystem;
import model.Client;
import model.Intersection;
import model.Map;

public final class ClientTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private List<Client> clients;
	private Map map;

	public ClientTableModel() {
		super(new Object[] { "ID", "Name", "Phone #", "Street", "Ave", "Instructions", "Email Address" }, 0);
		this.clients = CourierSystem.Clients;
		this.map = CourierSystem.CityMap;

		if (clients == null)
			clients = new ArrayList<Client>();
		for (Client c : clients) {
			super.addRow(new Object[] { c.clientID, c.name, c.phoneNumber, c.address.getStreet(), c.address.getAve(),
					c.dropoffInstructions, c.emailAddress });
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return int.class;
		case 3:
			return Intersection.class;
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
			clients.get(rowIndex).address = map.getIntersection(aValue.toString());
			break;
		case 4:
			clients.get(rowIndex).dropoffInstructions = aValue.toString();
			break;
		case 5:
			clients.get(rowIndex).emailAddress = aValue.toString();
			break;
		default:
			break;
		}
	}

	public void addRow(Client client) {
		super.addRow(new Object[] { client.clientID, client.name, client.phoneNumber, client.address,
				client.dropoffInstructions, client.emailAddress });
		clients.add(client);
	}

}
