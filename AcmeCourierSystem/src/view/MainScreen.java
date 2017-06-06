package view;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainScreen extends Container {
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	private JFrame window;

	public MainScreen(JFrame window) {
		this.window = window;
		tabbedPane.setBounds(0, 0, window.getWidth(), window.getHeight());
		add(tabbedPane);
		tabbedPane.addTab("Ticket Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window));
		tabbedPane.addTab("Client Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window));
		tabbedPane.addTab("Report Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window));
		tabbedPane.addTab("Map Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window));
		tabbedPane.addTab("Employee Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new EmployeeManagement());
		tabbedPane.addTab("System Settings", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window));
		tabbedPane.addTab("Change Password", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window));
		tabbedPane.addTab("Logout", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window));
	}

	@Override
	public void doLayout() {
		tabbedPane.setBounds(0, 0, window.getWidth(), window.getHeight());
		super.doLayout();
	}
}
