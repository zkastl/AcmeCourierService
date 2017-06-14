package main;

import java.awt.Toolkit;
import java.time.LocalDate;

import javax.swing.JFrame;

import model.Intersection;
import model.Map;
import model.Road;
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

		// test map stuff
		Map map = new Map();
		map.getRoute(map.getIntersection("A1"), map.getIntersection("F1")).print();
		map.getIntersection("B2").changeClosure(LocalDate.of(2017, 6, 7), LocalDate.of(2018, 6, 9));
		map.getRoute(map.getIntersection("A1"), map.getIntersection("F1")).print();

		/*
		 * for(Character a = 'A'; a < 'H'; a++){ for(Integer i = 1; i < 8; i++)
		 * { Intersection intersection = new Intersection(a.toString(),
		 * i.toString()); map.addIntersection(intersection);
		 * System.out.print(intersection.getName()); } System.out.println(); }
		 */
	}
}
