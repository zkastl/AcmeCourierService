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

import main.CourierSystem;
import controller.EnterKeyListenerForButtons;
import model.Employee;
import model.EmployeeRole;
import net.miginfocom.swing.MigLayout;

public class EmployeeManagement extends Container {
		
	private static final long serialVersionUID = 1L;
	private JTable table;

	public EmployeeManagement() {
		EmployeeTableModel employeeTable = new EmployeeTableModel(CourierSystem.Employees);
		setLayout(new MigLayout("", "[grow][50%][grow][10]", "[25][40][5][grow][][20]"));

		JLabel lblEmployeeManagement = new JLabel("Employee Management");
		lblEmployeeManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblEmployeeManagement, "cell 0 0 3 1,alignx center,aligny center");

		JButton btnAddEmployee = new JButton("Add Employee");
		add(btnAddEmployee, "cell 0 1,alignx center,aligny bottom");
		btnAddEmployee.addKeyListener(new EnterKeyListenerForButtons(btnAddEmployee));

		JButton btnRemoveEmployee = new JButton("Remove Employee");
		add(btnRemoveEmployee, "cell 1 1,alignx center");
		btnRemoveEmployee.addKeyListener(new EnterKeyListenerForButtons(btnAddEmployee));

		JButton btnSaveChanges = new JButton("Save Changes");
		add(btnSaveChanges, "cell 2 1");
		btnSaveChanges.addKeyListener(new EnterKeyListenerForButtons(btnSaveChanges));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3 3 1,grow");
		table = new JTable(employeeTable);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);

		JComboBox<EmployeeRole> roleComboBox = new JComboBox<EmployeeRole>();
		roleComboBox.addItem(EmployeeRole.Administrator);
		roleComboBox.addItem(EmployeeRole.Courier);
		roleComboBox.addItem(EmployeeRole.OrderTaker);
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(roleComboBox));

		JLabel lblNewLabel = new JLabel(" ");
		add(lblNewLabel, "cell 0 4");

		btnAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeTable.addRow(new Employee());
			}
		});

		btnRemoveEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				System.out.println("Selected Row: " + selectedRow);
				employeeTable.employees.get(selectedRow).ArchiveEmployee();
				employeeTable.removeRow(selectedRow);
			}
		});

		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CourierSystem.Employees = employeeTable.employees;
					CourierSystem.UpdateEmployees();
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
				finally {
					employeeTable.employees = CourierSystem.Employees;
					employeeTable.fireTableRowsUpdated(0, table.getRowCount());
				}
			}
		});
	}
}
