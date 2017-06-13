package view;

import java.awt.Container;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ChangePasswordScreen extends Container {
	public ChangePasswordScreen(JFrame window) {
		
		textField = new JTextField();
		textField.setBounds(183, 74, 81, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(183, 125, 81, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(178, 180, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Change Password");
		btnNewButton.setBounds(163, 226, 119, 23);
		add(btnNewButton);
	}

	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
}
