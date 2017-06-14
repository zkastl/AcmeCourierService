package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.EnterKeyListenerForButtons;
import main.CourierSystem;
import model.Employee;
import net.miginfocom.swing.MigLayout;

public class LogInScreen extends Container {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JTextField txtUsername;
	private static JTextField txtPassword;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public LogInScreen(JFrame window) {

		window.setLocation(screenSize.width / 3, screenSize.height / 4);
		window.setSize(screenSize.width / 3, screenSize.height / 2);
		setLayout(new MigLayout("", "[grow][][][grow]", "[grow][][][10][][][10][][grow]"));

		JLabel logo = new JLabel("");
		logo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		logo.setAlignmentX(Component.CENTER_ALIGNMENT);
		logo.setIcon(new ImageIcon(main.Application.class.getResource("/view/courier logo.png")));
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

		txtPassword = new JPasswordField();
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
				lblFailure.setVisible(!AttemptLogin(window, txtUsername.getText(), txtPassword.getText()));
			}
		});

		btnLogin.addKeyListener(new EnterKeyListenerForButtons(btnLogin));
		txtPassword.addKeyListener(new EnterKeyListenerForButtons(btnLogin));
	}

	private boolean AttemptLogin(JFrame window, String username, String password) {
		for (Employee e : CourierSystem.Employees.values()) {
			System.out.println(e.userName);
			if (e.userName.equalsIgnoreCase(username) && e.password.equals(password) && !e.getIsArchived()) {
				CourierSystem.currentUser = e;

				window.setLocation(screenSize.width / 6, 0);
				window.setSize(screenSize.width * 2 / 3, screenSize.height);
				window.setContentPane(new MainScreen(window));
				window.validate();
				return true;
			} else {
				System.out.println(e.userName + " " + username);
				System.out.println(e.password + " " + password);
			}
		}
		return false;
	}

}
