package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.EnterKeyListenerForButtons;
import model.CourierSystem;
import model.Employee;
import net.miginfocom.swing.MigLayout;

public class EmployeeManagement extends Container {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	EmployeeTableModel employeeTable;

	public EmployeeManagement(CourierSystem data) {
		employeeTable = new EmployeeTableModel(data.Employees);
		setLayout(new MigLayout("", "[grow][50%][grow][10]", "[25][40][5][grow][][20]"));

		JLabel lblEmployeeManagement = new JLabel("Employee Management");
		lblEmployeeManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblEmployeeManagement, "cell 0 0 3 1,alignx center,aligny center");

		JButton btnAddEmployee = new JButton("Add Employee");
		add(btnAddEmployee, "cell 0 1,alignx center,aligny bottom");
		btnAddEmployee.addKeyListener(new EnterKeyListenerForButtons(btnAddEmployee));

		JButton btnSaveChanges = new JButton("Save Changes");
		add(btnSaveChanges, "cell 2 1");
		btnSaveChanges.addKeyListener(new EnterKeyListenerForButtons(btnSaveChanges));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3 3 1,grow");
		table = new JTable(employeeTable);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);

		JLabel lblNewLabel = new JLabel(" ");
		add(lblNewLabel, "cell 0 4");

		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeTable.addRow(new Employee());
			}
		});

		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data.SaveEmployees();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
