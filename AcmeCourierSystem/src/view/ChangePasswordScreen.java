package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import main.CourierSystem;
import net.miginfocom.swing.MigLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class ChangePasswordScreen extends Container {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChangePasswordScreen(JFrame window) {
		setLayout(new MigLayout("", "[grow][][][grow]", "[grow][][][10][][][10][][grow]"));

		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblChangePassword, "cell 1 2 2 1,alignx center,aligny center");
		
		JLabel lblCurrentPassword = new JLabel("Old Password");
		lblCurrentPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblCurrentPassword, "cell 1 4,alignx right, aligny center");

		JTextField txtOldPassword = new JPasswordField();
		txtOldPassword.setSize(new Dimension(200, 20));
		add(txtOldPassword, "cell 2 4,growx,aligny center");
		txtOldPassword.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("Password");
		lblNewPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblNewPassword, "cell 1 5,alignx left,aligny center");

		JTextField txtNewPassword = new JPasswordField();
		txtNewPassword.setSize(new Dimension(200, 20));
		add(txtNewPassword, "cell 2 5,growx,aligny center");
		txtNewPassword.setColumns(10);

		JLabel lblFailure = new JLabel("Unable to change password with supplied parameters.");
		lblFailure.setVisible(false);
		lblFailure.setForeground(Color.RED);
		add(lblFailure, "cell 1 8 2 1,alignx center,aligny top");
		
		JLabel lblSuccess = new JLabel("Password Updated Successfully.");
		lblSuccess.setVisible(false);
		lblSuccess.setForeground(Color.RED);
		add(lblSuccess, "cell 1 8 2 1,alignx center,aligny top");

		JButton btnPasswordChange = new JButton("Change Password");
		btnPasswordChange.setAlignmentY(Component.TOP_ALIGNMENT);
		btnPasswordChange.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnPasswordChange, "cell 1 7 2 1,alignx center,aligny top");
		btnPasswordChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean passwordChanged = false;
				passwordChanged = changePassword(window, txtOldPassword.getText(), txtNewPassword.getText());
				lblFailure.setVisible(!passwordChanged);
				lblSuccess.setVisible(passwordChanged);
				if (passwordChanged) {
					txtOldPassword.setText("");
					txtNewPassword.setText("");
				}
			}
		});
	}
	
	private boolean changePassword(JFrame window, String oldPassword, String newPassword ) {
		if(CourierSystem.currentUser.password.equals(oldPassword) && oldPassword != newPassword ) {
			CourierSystem.currentUser.password = newPassword;
			try {
				CourierSystem.SaveEmployee(CourierSystem.currentUser);
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}
