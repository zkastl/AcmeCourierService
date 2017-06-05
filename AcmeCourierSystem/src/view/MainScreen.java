package view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainScreen extends Container {
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

	public MainScreen(JFrame window) {
		tabbedPane.setBounds(0, 0, window.getWidth(), window.getHeight());
		add(tabbedPane);
		tabbedPane.addTab("Ticket Management", new LogOutScreen(window));
		tabbedPane.addTab("Client Management", new LogOutScreen(window));
		tabbedPane.addTab("Report Management", new LogOutScreen(window));
		tabbedPane.addTab("Map Management", new LogOutScreen(window));
		tabbedPane.addTab("Employee Management", new LogOutScreen(window));
		tabbedPane.addTab("System Settings", new LogOutScreen(window));
		tabbedPane.addTab("Change Password", new LogOutScreen(window));
		tabbedPane.addTab("Logout", new LogOutScreen(window));
	}
}
