package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.EnterKeyListenerForButtons;
import main.CourierSystem;
import model.Client;
import net.miginfocom.swing.MigLayout;

public class ClientManagement extends Container {

	private static final long serialVersionUID = 1L;
	private JTable table;

	public ClientManagement(CourierSystem data) {
		ClientTableModel clientTable = new ClientTableModel(data);
		setLayout(new MigLayout("", "[grow][50%][grow][10]", "[25][40][5][grow][][20]"));

		JLabel lblEmployeeManagement = new JLabel("Client Management");
		lblEmployeeManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblEmployeeManagement, "cell 0 0 3 1,alignx center,aligny center");

		JButton btnAddClient = new JButton("Add Client");
		add(btnAddClient, "cell 0 1,alignx center,aligny bottom");
		btnAddClient.addKeyListener(new EnterKeyListenerForButtons(btnAddClient));

		JButton btnRemoveClient = new JButton("Remove Client");
		add(btnRemoveClient, "cell 1 1,alignx center");
		btnRemoveClient.addKeyListener(new EnterKeyListenerForButtons(btnAddClient));

		JButton btnSaveChanges = new JButton("Save Changes");
		add(btnSaveChanges, "cell 2 1");
		btnSaveChanges.addKeyListener(new EnterKeyListenerForButtons(btnSaveChanges));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3 3 1,grow");
		table = new JTable(clientTable);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);

		JLabel lblNewLabel = new JLabel(" ");
		add(lblNewLabel, "cell 0 4");

		btnAddClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientTable.addRow(new Client());
			}
		});

		btnRemoveClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientTable.removeRow(table.getSelectedRow());
				data.Clients.remove(table.getSelectedRow());
			}
		});

		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data.SaveClients();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
