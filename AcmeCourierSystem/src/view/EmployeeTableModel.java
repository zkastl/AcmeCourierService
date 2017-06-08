package view;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import model.Employee;
import model.EmployeeRole;

public class EmployeeTableModel implements TableModel {

	private ArrayList<Employee> employees;

	public EmployeeTableModel(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int getRowCount() {
		return employees.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return "ID";
		case 1:
			return "Name";
		case 2:
			return "Role";
		default:
			return null;
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return false;
		default:
			return employees.get(rowIndex).role == EmployeeRole.Administrator;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return employees.get(rowIndex).id;
		case 1:
			return employees.get(rowIndex).name;
		case 2:
			return employees.get(rowIndex).role;
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 1:
			employees.get(rowIndex).name = aValue.toString();
		case 2:
			employees.get(rowIndex).role = EmployeeRole.valueOf(aValue.toString());
		default:
			break;
		}
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

}
