package view;

import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import main.CourierSystem;
import model.EmployeeRole;

public class MainScreen extends Container {

	private static final long serialVersionUID = 1L;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
	private JFrame window;

	public MainScreen(JFrame window, CourierSystem data) {
		this.window = window;
		tabbedPane.setBounds(0, 0, window.getWidth(), window.getHeight());
		add(tabbedPane);
		tabbedPane.addTab("Ticket Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window, data));
		tabbedPane.addTab("Client Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new ClientManagement(data));
		tabbedPane.addTab("Report Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window, data));
		tabbedPane.addTab("Map Management", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window, data));
		if (data.currentUser.role == EmployeeRole.Administrator)
			tabbedPane.addTab("Employee Management",
					new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")), new EmployeeManagement());
		if (data.currentUser.role == EmployeeRole.Administrator)
			tabbedPane.addTab("System Settings", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
					new LogOutScreen(window, data));
		tabbedPane.addTab("Change Password", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window, data));
		tabbedPane.addTab("Logout", new ImageIcon(MainScreen.class.getResource("/view/taller tab.png")),
				new LogOutScreen(window, data));
		
		/*tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if (e.getSource() instanceof JTabbedPane) {
					JTabbedPane pane = (JTabbedPane)e.getSource();
					switch(pane.getTitleAt(pane.getSelectedIndex())){
					case "Employee":
						
					}
				}
			}
		});*/
		
	}

	@Override
	public void doLayout() {
		tabbedPane.setBounds(0, 0, window.getWidth(), window.getHeight());
		super.doLayout();
	}
}
