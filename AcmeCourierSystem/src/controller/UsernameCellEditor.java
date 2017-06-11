package controller;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import main.CourierSystem;
import model.Employee;
import view.EmployeeTableModel;

public class UsernameCellEditor extends DefaultCellEditor {

	public UsernameCellEditor(JTextField textField) {
		super(textField);
	}

	@Override
	public boolean stopCellEditing() {

		JTable table = (JTable) getComponent().getParent();
		int employeeIndex = table.getSelectedRow();
		Employee selectedEmployee = ((EmployeeTableModel) table.getModel()).employees.get(employeeIndex);

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
