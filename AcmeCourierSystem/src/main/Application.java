package main;

import java.awt.Toolkit;
import javax.swing.JFrame;
import view.LogInScreen;

public class Application {

	public static void main(String[] args) throws Exception {
		
		CourierSystem.InitializeCourierSystem();
		
		final JFrame window = new JFrame();
		window.setContentPane(new LogInScreen(window));
		window.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/view/courier logo.png")));
		window.setTitle("ACME Delivery Management System");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setVisible(true);
	}
}
