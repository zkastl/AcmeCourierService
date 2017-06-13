package controller;

import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MandatoryStringCellEditor extends DefaultCellEditor {
	private static final long serialVersionUID = 1L;
	
	public MandatoryStringCellEditor(JTextField textField) {
		super(textField);
	}

	@Override
	public boolean stopCellEditing() {
		JTable table = (JTable) getComponent().getParent();
		int rowNumber = table.getSelectedRow() + 1;
		String columnName = table.getColumnName(table.getSelectedColumn());

		if (getCellEditorValue().equals("")) {
			JOptionPane.showMessageDialog(getComponent(),
					"Invalid entry in row " + rowNumber + ". " + columnName + " must be set.");
			return false;
		}

		return super.stopCellEditing();
	}
}
