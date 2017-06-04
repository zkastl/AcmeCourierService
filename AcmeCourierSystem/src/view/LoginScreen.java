package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class LoginScreen extends Container {
	private static JTextField txtUsername;
	private static JTextField txtPassword;

	public LoginScreen() {

		setLayout(new MigLayout("", "[grow][][][grow]", "[grow][][][10][][][10][][grow]"));

		JLabel logo = new JLabel("");
		logo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		logo.setIcon(new ImageIcon(Application.class.getResource("/view/courier logo.png")));
		add(logo, "cell 1 1 2 1,alignx center,aligny bottom");

		JLabel lblAcmeCourierService = new JLabel("ACME Courier Service");
		lblAcmeCourierService.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblAcmeCourierService, "cell 1 2 2 1,alignx center,aligny center");

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblUsername, "cell 1 4,alignx right,aligny center");

		txtUsername = new JTextField();
		txtUsername.setSize(new Dimension(200, 20));
		add(txtUsername, "cell 2 4,growx,aligny center");
		txtUsername.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lblPassword, "cell 1 5,alignx left,aligny center");

		txtPassword = new JTextField();
		txtPassword.setSize(new Dimension(200, 20));
		add(txtPassword, "cell 2 5,growx,aligny center");
		txtPassword.setColumns(10);

		JLabel lblFailure = new JLabel("Invalid username or password.");
		lblFailure.setVisible(false);
		lblFailure.setForeground(Color.RED);
		add(lblFailure, "cell 1 8 2 1,alignx center,aligny top");

		JButton btnLogin = new JButton("Login");
		btnLogin.setAlignmentY(Component.TOP_ALIGNMENT);
		btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnLogin, "cell 1 7 2 1,alignx center,aligny top");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblFailure.setVisible(!AttemptLogin(txtUsername.getText(), txtPassword.getText()));
			}
		});
	}

	private boolean AttemptLogin(String username, String password) {
		/*
		 * TODO load main screen if valid
		 */
		if (username == null && password == null) {
			// load main screen
			return true;
		}
		return false;
	}

}
