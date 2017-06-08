package view;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import model.Employee;
import model.EmployeeRole;

public class EmployeeTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private List<Employee> employees;

	public EmployeeTableModel(List<Employee> employees) {
		super(new Object[] { "ID", "Name", "Role", "User Name" }, 0);
		this.employees = employees;
		for (Employee e : employees) {
			super.addRow(new Object[] { e.id, e.name, e.role, e.userName });
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
		default:
			return employees.get(rowIndex).role == EmployeeRole.Administrator;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return employees.get(rowIndex).id;
		case 1:
			return employees.get(rowIndex).name;
		case 2:
			return employees.get(rowIndex).role;
		case 3:
			return employees.get(rowIndex).userName;
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 1:
			employees.get(rowIndex).name = aValue.toString();
			break;
		case 2:
			employees.get(rowIndex).role = EmployeeRole.valueOf(aValue.toString());
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

}
