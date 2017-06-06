package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class LogOutScreen extends Container {

	public LogOutScreen(JFrame window) {
		setLayout(new MigLayout("", "[grow][grow]", "[grow][][][grow]"));

		JLabel lblAreYouSure = new JLabel("Are you sure you wish to logout?");
		add(lblAreYouSure, "cell 0 1 2 1,alignx center,aligny center");

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setContentPane(new LogInScreen(window));
				window.validate();
			}
		});
		add(btnLogOut, "cell 0 2 2 1,alignx center,aligny center");
	}

}
