package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.github.lgooddatepicker.tableeditors.DateTimeTableEditor;
import com.github.lgooddatepicker.tableeditors.TimeTableEditor;

import controller.EnterKeyListenerForButtons;
import controller.TableValidator;
import main.CourierSystem;
import model.Delivery;
import model.DeliveryStatus;
import model.Employee;
import model.EmployeeRole;
import net.miginfocom.swing.MigLayout;

public class DeliveryManagement extends Container {

	private static final long serialVersionUID = 1L;
	private JTable table;
	DeliveryTableModel deliveryTable;

	public DeliveryManagement() {
		deliveryTable = new DeliveryTableModel();
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

		// date picker
		table.setDefaultEditor(LocalTime.class, new TimeTableEditor());
		table.setDefaultRenderer(LocalTime.class, new TimeTableEditor());
		table.setDefaultRenderer(LocalDateTime.class, new DateTimeTableEditor());

		JLabel lblNewLabel = new JLabel(" ");
		add(lblNewLabel, "cell 0 4");

		JComboBox<DeliveryStatus> statusComboBox = new JComboBox<DeliveryStatus>(
				new DefaultComboBoxModel<>(DeliveryStatus.values()));
		table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(statusComboBox));
		updateAvailableCouriers();

		btnCreateDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!TableValidator.isValid(table))
					return;

				// open delivery creator
				Delivery newDelivery = new Delivery();
				deliveryTable.addRow(newDelivery);
				new DeliveryTicketEditor(newDelivery, deliveryTable);
			}
		});

		btnEditDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!TableValidator.isValid(table))
					return;

				int selectedRow = table.getSelectedRow();
				System.out.println("Selected Row: " + selectedRow);
				Delivery delivery = deliveryTable.deliveries.get(selectedRow);

				new DeliveryTicketEditor(delivery, deliveryTable);
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

	public void updateAvailableCouriers() {
		JComboBox<String> availableCouriers = new JComboBox<String>();
		for (Employee e : CourierSystem.Employees.values()) {
			if (!e.getIsArchived() && e.role == EmployeeRole.Courier) {
				availableCouriers.addItem(e.name);
			}
		}
		table.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(availableCouriers));
	}
}
