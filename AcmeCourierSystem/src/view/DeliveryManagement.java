package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.github.lgooddatepicker.tableeditors.DateTimeTableEditor;

import javax.swing.JSpinner;

import controller.EnterKeyListenerForButtons;
import controller.TableValidator;
import main.CourierSystem;
import model.Client;
import model.Delivery;
import model.DeliveryStatus;
import model.Employee;
import model.EmployeeRole;
import model.Intersection;
import model.Route;
import model.Settings;
import net.miginfocom.swing.MigLayout;

public class DeliveryManagement extends Container {

	private static final long serialVersionUID = 1L;
	private JTable table;

	public DeliveryManagement() {
		DeliveryTableModel deliveryTable = new DeliveryTableModel();
		setLayout(new MigLayout("", "[grow][50%][grow][10]", "[25][40][5][grow][][20]"));

		JLabel lblDeliveryManagement = new JLabel("Delivery Management");
		lblDeliveryManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblDeliveryManagement, "cell 0 0 3 1,alignx center,aligny center");

		JButton btnCreateDelivery = new JButton("Create Delivery");
		add(btnCreateDelivery, "cell 0 1,alignx center,aligny bottom");
		btnCreateDelivery.addKeyListener(new EnterKeyListenerForButtons(btnCreateDelivery));

		JButton btnEditDelivery = new JButton("Edit Delivery");
		add(btnEditDelivery, "cell 1 1,alignx center");
		btnEditDelivery.addKeyListener(new EnterKeyListenerForButtons(btnEditDelivery));

		JButton btnSaveChanges = new JButton("Save Changes");
		add(btnSaveChanges, "cell 2 1");
		btnSaveChanges.addKeyListener(new EnterKeyListenerForButtons(btnSaveChanges));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3 3 1,grow");
		table = new JTable(deliveryTable);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
<<<<<<< Upstream, based on branch 'master' of https://github.com/zkastl/AcmeCourierService.git
		
		// date picker
		table.setDefaultEditor(LocalDateTime.class, new DateTimeTableEditor());
		table.setDefaultRenderer(LocalDateTime.class, new DateTimeTableEditor());
		TableColumn column = table.getColumnModel().getColumn(3);
		column.setCellEditor(table.getDefaultEditor(LocalDateTime.class));
		column.setCellRenderer(table.getDefaultRenderer(LocalDateTime.class));
		
		/*column.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				TableModel model = table.getModel();
				int row = table.getSelectedRow();
				if(row != -1 && model.getValueAt(row, 1) != null  && model.getValueAt(row, 2) != null)	{
					// set Calculated Departure time
					Route pickupRoute = CourierSystem.CityMap.getRoute(Settings.courierStartAddress, CourierSystem.Clients.get(model.getValueAt(row, 1)).address);
					model.setValueAt(((LocalDateTime)model.getValueAt(row, 3)).minus(pickupRoute.getTime()), row, 4);
					
					// set Estimated Delivery Time
					Route deliveryRoute = CourierSystem.CityMap.getRoute(CourierSystem.Clients.get(model.getValueAt(row, 1)).address, CourierSystem.Clients.get(model.getValueAt(row, 2)).address);
					model.setValueAt(((LocalDateTime)model.getValueAt(row, 3)).plus(deliveryRoute.getTime()), row, 5);
				}
			}
		});*/
		
		updateAvailableClients();		
=======

		updateAvailableClients();
>>>>>>> f011918 initial layout of delivery editor and merging with date picker stuff

		JLabel lblNewLabel = new JLabel(" ");
		add(lblNewLabel, "cell 0 4");

		JComboBox<DeliveryStatus> statusComboBox = new JComboBox<DeliveryStatus>();
		statusComboBox.addItem(DeliveryStatus.Requested);
		statusComboBox.addItem(DeliveryStatus.Completed);
		statusComboBox.addItem(DeliveryStatus.Canceled);
		table.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(statusComboBox));
		updateAvailableCouriers();

		btnCreateDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!TableValidator.isValid(table))
					return;

				// open delivery creator
				deliveryTable.addRow(new Delivery());
			}
		});

		btnEditDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getCellEditor() != null)
					table.getCellEditor().cancelCellEditing();

				int selectedRow = table.getSelectedRow();
				System.out.println("Selected Row: " + selectedRow);
				Delivery delivery = deliveryTable.deliveries.get(selectedRow);
				delivery.status = DeliveryStatus.Canceled;

				deliveryTable.removeRow(selectedRow);
			}
		});

		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!TableValidator.isValid(table))
					return;

				try {
					CourierSystem.Deliveries = new HashMap<String, Delivery>();
					for (Delivery del : deliveryTable.deliveries) {
						CourierSystem.Deliveries.put(String.valueOf(del.packageID), del);
					}
					CourierSystem.UpdateDeliveries();
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					deliveryTable.refresh();
				}
			}
		});
	}

	public void updateAvailableClients() {
		JComboBox<String> fromComboBox = new JComboBox<String>();
		JComboBox<String> toComboBox = new JComboBox<String>();
		for (Client c : CourierSystem.Clients.values()) {
			if (!c.getIsArchived()) {
				fromComboBox.addItem(c.name);
				toComboBox.addItem(c.name);
			}
		}
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(fromComboBox));
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(toComboBox));
	}

	public void updateAvailableCouriers() {
		JComboBox<String> availableCouriers = new JComboBox<String>();
		for (Employee e : CourierSystem.Employees.values()) {
			if (!e.getIsArchived() && e.role == EmployeeRole.Courier) {
				availableCouriers.addItem(e.name);
			}
		}
		table.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(availableCouriers));
	}
}
