package main;

import java.awt.Toolkit;
import java.util.Calendar;

import javax.swing.JFrame;

import model.Map;
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
		//Map map = new Map();
		CourierSystem.CityMap.getRoute(CourierSystem.CityMap.getIntersection("A1"), CourierSystem.CityMap.getIntersection("F1")).print();
		Calendar start = Calendar.getInstance();
		start.set(2016, 0, 1, 0, 0);
		Calendar end = Calendar.getInstance();
		end.set(2018, 11, 30, 23, 59);
		CourierSystem.CityMap.getIntersection("B2").changeClosure(start, end);
		CourierSystem.CityMap.getRoute(CourierSystem.CityMap.getIntersection("A1"), CourierSystem.CityMap.getIntersection("F1")).print();

		/*
		 * for(Character a = 'A'; a < 'H'; a++){ for(Integer i = 1; i < 8; i++)
		 * { Intersection intersection = new Intersection(a.toString(),
		 * i.toString()); map.addIntersection(intersection);
		 * System.out.print(intersection.getName()); } System.out.println(); }
		 */
	}
}
