package view;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final JFrame window = new JFrame();
		window.setContentPane(new LoginScreen());
		window.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/view/courier logo.png")));
		window.setTitle("ACME Delivery Management System");
		window.setSize(600, 480);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setVisible(true);
	}
}