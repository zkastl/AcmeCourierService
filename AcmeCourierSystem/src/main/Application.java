package main;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import view.*;
import model.*;

@SuppressWarnings("unused")
public class Application {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		CourierSystem data = new CourierSystem();

		final JFrame window = new JFrame();
		window.setContentPane(new LogInScreen(window, data));
		window.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/view/courier logo.png")));
		window.setTitle("ACME Delivery Management System");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setVisible(true);
	}
}