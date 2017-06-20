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
		lblChangePassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(lblChangePassword, "cell 1 2 2 1,alignx center,aligny center");
		
		JLabel lblCurrentPassword = new JLabel("Old Password");
		lblCurrentPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(lblCurrentPassword, "cell 1 4,alignx right,aligny center");

		JTextField txtOldPassword = new JPasswordField();
		txtOldPassword.setSize(new Dimension(200, 20));
		add(txtOldPassword, "cell 2 4,growx,aligny center");
		txtOldPassword.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(lblNewPassword, "cell 1 5,alignx right,aligny center");

		JTextField txtNewPassword = new JPasswordField();
		txtNewPassword.setSize(new Dimension(200, 20));
		add(txtNewPassword, "cell 2 5,growx,aligny center");
		txtNewPassword.setColumns(10);
		
		JLabel lblNewPasswordConfirm = new JLabel("Confirm New Password");
		lblNewPasswordConfirm.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(lblNewPasswordConfirm, "cell 1 6,alignx right,aligny center");

		JTextField txtNewPasswordConfirm = new JPasswordField();
		txtNewPasswordConfirm.setSize(new Dimension(200, 20));
		add(txtNewPasswordConfirm, "cell 2 6,growx,aligny center");
		txtNewPasswordConfirm.setColumns(10);

		JLabel lblStatus = new JLabel();
		lblStatus.setVisible(false);
		lblStatus.setForeground(Color.RED);
		add(lblStatus, "cell 1 8 2 1,alignx center,aligny top");

		JButton btnPasswordChange = new JButton("Change Password");
		btnPasswordChange.setAlignmentY(Component.TOP_ALIGNMENT);
		btnPasswordChange.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnPasswordChange, "cell 1 7 2 1,alignx center,aligny top");
		btnPasswordChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean passwordChanged = false;
				passwordChanged = changePassword(window, txtOldPassword.getText(), txtNewPassword.getText(), txtNewPasswordConfirm.getText());
				if (passwordChanged) {
					txtOldPassword.setText("");
					txtNewPassword.setText("");
					txtNewPasswordConfirm.setText("");
					lblStatus.setText("Password Updated Successfully.");
					lblStatus.setForeground(Color.BLACK);
				}
				else {
					lblStatus.setText("Unable to change password with supplied parameters.");
					lblStatus.setForeground(Color.RED);
				}
				lblStatus.setVisible(true);
			}
		});
	}
	
	private boolean changePassword(JFrame window, String oldPassword, String newPassword, String newPasswordConfirm ) {
		if((CourierSystem.currentUser.password.equals(oldPassword)) && (!oldPassword.equals(newPassword))
				&& (newPassword.equals(newPasswordConfirm))) {
			CourierSystem.currentUser.password = newPassword;
			try {
				CourierSystem.SaveEmployee(CourierSystem.currentUser);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
}
