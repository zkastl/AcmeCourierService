package controller;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class TableValidator {

	public static boolean isValid(JTable table) {
		TableCellEditor editor = table.getCellEditor();
		// check currently selected cell (if any) is valid
		if (editor != null && !table.getCellEditor().stopCellEditing()) {
			return false;
		}

		// check lastRow is valid
		int lastRow = table.getRowCount() - 1;
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.changeSelection(lastRow, i, false, false);
			if (table.editCellAt(lastRow, i))
				editor = table.getCellEditor(lastRow, i);
			if (editor != null && !editor.stopCellEditing())
				return false;
		}

		return true;
	}
}
