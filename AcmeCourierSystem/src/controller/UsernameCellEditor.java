package controller;

import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import main.CourierSystem;
import model.Employee;

public class UsernameCellEditor extends DefaultCellEditor {

	List<Employee> employees;

	public UsernameCellEditor(JTextField textField, List<Employee> employees) {
		super(textField);

		this.employees = employees;
	}

	@Override
	public boolean stopCellEditing() {

		int employeeIndex = ((JTable) getComponent().getParent()).getSelectedRow();
		Employee selectedEmployee = employees.get(employeeIndex);

		if (getCellEditorValue().equals("")) {
			JOptionPane.showMessageDialog(getComponent(), "Invalid entry in row " + (employeeIndex + 1) + ". "
					+ selectedEmployee.role.toString() + " user name must be set.");
			return false;
		}

		for (Employee e : CourierSystem.Employees) {
			if (e != selectedEmployee && e.userName.equalsIgnoreCase(getCellEditorValue().toString())) {
				JOptionPane.showMessageDialog(getComponent(),
						"Invalid entry in row " + (employeeIndex + 1) + ". Employee user name is already in use.");
				return false;
			}
		}

		return super.stopCellEditing();
	}

}
