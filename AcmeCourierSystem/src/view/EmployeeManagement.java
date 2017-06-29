package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.UUID;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.EnterKeyListenerForButtons;
import controller.MandatoryStringCellEditor;
import controller.TableValidator;
import controller.UsernameCellEditor;
import main.CourierSystem;
import model.Employee;
import model.EmployeeRole;
import net.miginfocom.swing.MigLayout;

public class EmployeeManagement extends Container {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private EmployeeTableModel employeeTable;

	public EmployeeManagement() {
		employeeTable = new EmployeeTableModel();
		setLayout(new MigLayout("", "[grow][50%][grow][10]", "[25][40][5][grow][][20]"));

		JLabel lblEmployeeManagement = new JLabel("Employee Management");
		lblEmployeeManagement.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblEmployeeManagement, "cell 0 0 3 1,alignx center,aligny center");

		JButton btnAddEmployee = new JButton("Add Employee");
		add(btnAddEmployee, "cell 0 1,alignx center");
		btnAddEmployee.addKeyListener(new EnterKeyListenerForButtons(btnAddEmployee));

		JButton btnRemoveEmployee = new JButton("Remove Employee");
		add(btnRemoveEmployee, "cell 1 1,alignx center");
		btnRemoveEmployee.addKeyListener(new EnterKeyListenerForButtons(btnRemoveEmployee));

		JButton btnSaveChanges = new JButton("Save Changes");
		add(btnSaveChanges, "cell 2 1");
		btnSaveChanges.addKeyListener(new EnterKeyListenerForButtons(btnSaveChanges));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 3 3 1,grow");
		table = new JTable(employeeTable);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		table.getColumnModel().getColumn(1).setCellEditor(new MandatoryStringCellEditor(new JTextField()));
		table.getColumnModel().getColumn(3).setCellEditor(new UsernameCellEditor(new JTextField()));

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
				btnAddEmployee.setEnabled(false);
			}
		});

		btnRemoveEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getCellEditor() != null)
					table.getCellEditor().cancelCellEditing();

				int selectedRow = table.getSelectedRow();
				System.out.println("Selected Row: " + selectedRow);
				Employee employee = employeeTable.employees.get(selectedRow);
				if (employee.id != 0) {
					if (!CourierSystem.currentUser.equals(employee)) {
						employee.ArchiveEmployee();
						saveAction();
					}
					else {
						JOptionPane.showMessageDialog(btnRemoveEmployee, "You cannot delete yourself.");
					}
				}
				employeeTable.removeRow(selectedRow);
				btnAddEmployee.setEnabled(true);
			}
		});

		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAction();
				btnAddEmployee.setEnabled(true);
			}
		});
	}

	private void saveAction() {
		if (!TableValidator.isValid(table))
			return;
		try {
			UUID uuid = UUID.randomUUID();
			CourierSystem.Employees = new HashMap<String, Employee>();
			for (Employee emp : employeeTable.employees) {
				if (emp.userName == "" && emp.role == EmployeeRole.Courier) {
					emp.userName = "courier" + uuid.toString();
				}
				CourierSystem.Employees.put(emp.name, emp);
			}
			CourierSystem.UpdateEmployees();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			employeeTable.refresh();
		}
	}
}
