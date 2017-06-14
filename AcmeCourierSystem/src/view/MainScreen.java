package view;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.CourierSystem;
import model.EmployeeRole;

public class MainScreen extends Container {

	private static final long serialVersionUID = 1L;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	private JFrame window;
	private static ClientManagement clientManagement;
	private static DeliveryManagement deliveryManagement;

	public MainScreen(JFrame window) {
		this.window = window;
		tabbedPane.setBounds(0, 0, window.getWidth(), window.getHeight());
		add(tabbedPane);
		clientManagement = new ClientManagement();
		deliveryManagement = new DeliveryManagement();
		
		tabbedPane.addTab("Delivery Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				deliveryManagement);
		tabbedPane.addTab("Client Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				clientManagement);
		tabbedPane.addTab("Report Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window));
		tabbedPane.addTab("Map Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new MapManagement(window));
		if (CourierSystem.currentUser.role == EmployeeRole.Administrator)
			tabbedPane.addTab("Employee Management",
					new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")), new EmployeeManagement());
		if (CourierSystem.currentUser.role == EmployeeRole.Administrator)
			tabbedPane.addTab("System Settings", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
					new LogOutScreen(window));
		tabbedPane.addTab("Change Password", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new ChangePasswordScreen(window));
		tabbedPane.addTab("Logout", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window));

		
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override 
			public void stateChanged(ChangeEvent e) { 
				try {
					deliveryManagement.updateAvailableClients();
				}
				catch (Exception ex) {
					System.out.println(ex.getStackTrace());
				}
			}
		});
	}

	@Override
	public void doLayout() {
		tabbedPane.setBounds(0, 0, window.getWidth(), window.getHeight());
		super.doLayout();
	}
}
