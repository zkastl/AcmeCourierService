package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.EnterKeyListenerForButtons;
import controller.MandatoryStringCellEditor;
import controller.TableValidator;
import main.CourierSystem;
import model.Client;
import net.miginfocom.swing.MigLayout;

public class ClientManagement extends Container {

	private static final long serialVersionUID = 1L;
	private JTable table;

	public ClientManagement() {
		ClientTableModel clientTable = new ClientTableModel();
		setLayout(new MigLayout("", "[grow][50%][grow][10]", "[25][40][5][grow][][20]"));

		JLabel lblEmployeeManagement = new JLabel("Client Management");
		lblEmployeeManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblEmployeeManagement, "cell 0 0 3 1,alignx center,aligny center");

		JButton btnAddClient = new JButton("Add Client");
		add(btnAddClient, "cell 0 1,alignx center,aligny bottom");
		btnAddClient.addKeyListener(new EnterKeyListenerForButtons(btnAddClient));

		JButton btnRemoveClient = new JButton("Remove Client");
		add(btnRemoveClient, "cell 1 1,alignx center");
		btnRemoveClient.addKeyListener(new EnterKeyListenerForButtons(btnRemoveClient));

		JButton btnSaveChanges = new JButton("Save Changes");
		add(btnSaveChanges, "cell 2 1");
		btnSaveChanges.addKeyListener(new EnterKeyListenerForButtons(btnSaveChanges));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3 3 1,grow");
		table = new JTable(clientTable);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.getColumnModel().getColumn(1).setCellEditor(new MandatoryStringCellEditor(new JTextField()));
		table.getColumnModel().getColumn(2).setCellEditor(new MandatoryStringCellEditor(new JTextField()));

		JComboBox<String> streets = new JComboBox<>(
				new String[] { "A Street", "B Street", "C Street", "D Street", "E Street", "F Street", "G Street" });
		streets.setSelectedIndex(0);
		JComboBox<String> avenues = new JComboBox<>(
				new String[] { "1st Ave", "2nd Ave", "3rd Ave", "4th Ave", "5th Ave", "6th Ave", "7th Ave" });
		avenues.setSelectedIndex(0);
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(streets));
		table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(avenues));

		JLabel lblNewLabel = new JLabel(" ");
		add(lblNewLabel, "cell 0 4");

		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!TableValidator.isValid(table))
					return;

				clientTable.addRow(new Client());
			}
		});

		btnRemoveClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getCellEditor() != null)
					table.getCellEditor().cancelCellEditing();

				int selectedRow = table.getSelectedRow();
				System.out.println("Selected Row: " + selectedRow);
				Client client = clientTable.clients.get(selectedRow);
				if (client.clientID != 0) {
					client.ArchiveClient();
				}

				clientTable.removeRow(selectedRow);
			}
		});

		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!TableValidator.isValid(table))
					return;

				try {
					CourierSystem.Clients = clientTable.clients;
					CourierSystem.UpdateClients();
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					clientTable.refresh();
				}
			}
		});
	}
}
