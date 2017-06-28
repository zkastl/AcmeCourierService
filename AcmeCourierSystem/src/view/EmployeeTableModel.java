package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import main.CourierSystem;
import model.Employee;
import model.EmployeeRole;

public final class EmployeeTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	public List<Employee> employees;

	public EmployeeTableModel() {
		super(new Object[] { "ID", "Name", "Role", "User Name" }, 0);
		employees = new ArrayList<Employee>();

		for (Employee e : CourierSystem.Employees.values()) {
			if (!e.getIsArchived()) {
				super.addRow(new Object[] { e.id, e.name, e.role, e.userName });
				employees.add(e);
			}
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return int.class;
		case 1:
			return String.class;
		case 2:
			return EmployeeRole.class;
		case 3:
			return String.class;
		default:
			return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return false;
		case 3:
			return employees.get(rowIndex).role != EmployeeRole.Courier;
		default:
			return true;
		}
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return employees.get(row).id;
		case 3:
			return (employees.get(row).role == EmployeeRole.Courier) ? "" : super.getValueAt(row, column);
		default:
			return super.getValueAt(row, column);
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);

		switch (columnIndex) {
		case 1:
			employees.get(rowIndex).name = aValue.toString();
			break;
		case 2:
			EmployeeRole role = EmployeeRole.valueOf(aValue.toString());
			employees.get(rowIndex).role = role;
			if (role == EmployeeRole.Courier)
				setValueAt("", rowIndex, 3);
			break;
		case 3:
			employees.get(rowIndex).userName = aValue.toString();
		default:
			break;
		}
	}

	public void addRow(Employee employee) {
		super.addRow(new Object[] { employee.id, employee.name, employee.role, employee.userName });
		employees.add(employee);
	}

	public void removeRow(int rowNumber) {
		super.removeRow(rowNumber);
		employees.remove(rowNumber);
	}

	public void refresh() {
		employees.clear();
		for (Employee e : CourierSystem.Employees.values()) {
			if (!e.getIsArchived())
				employees.add(e);
		}
		fireTableRowsUpdated(0, employees.size() - 1);
	}

}
