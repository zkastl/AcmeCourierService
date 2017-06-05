package view;

import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;

import model.Employee;
import model.Serialize;

public class Application {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		// TODO load data
		Employee e = new Employee("Zak", 0002, "King");
		Serialize.SaveObject("test.xml", e);
		Employee e2 = (Employee)Serialize.LoadObject("test.xml", Employee.class);
		System.out.println(e2.getName());

		final JFrame window = new JFrame();
		window.setContentPane(new LogInScreen(window));
		window.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/view/courier logo.png")));
		window.setTitle("ACME Delivery Management System");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setVisible(true);
	}
}
