package view;

import java.awt.Container;

import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import main.CourierSystem;

import javax.swing.JLayeredPane;
import java.awt.BorderLayout;
import java.awt.Component;

public class MapManagement  extends Container {
	private static final long serialVersionUID = 1L;
	private JLabel lblIntersection;
	private JLabel lblClosureStart;
	private JLabel lblClosureEnd;
	private DatePicker startSpinner;
	private DatePicker endSpinner;
	private JButton saveChanges;
	private JButton cancel;
	
	public MapManagement(JFrame window) {
		
		setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new MigLayout("", "[0][4.00px][34.00][24.00][41.00][24.00][46.00][24.00][46.00][24][39.00][24][45.00][24][38.00][24.00][49.00]", "[25.00][24.00px][55.00][24.00][56.00][24.00][55.00][24.00][57.00][24.00][59.00][24.00][56.00][24.00][17.00][50][]"));
		
		JLabel backgroundMap = new JLabel("");
		backgroundMap.setIcon(new ImageIcon(MapManagement.class.getResource("/view/MapLow.png")));
		layeredPane.add(backgroundMap, "cell 0 0 17 15,alignx right,aligny center");
		
		lblIntersection = new JLabel("Z0 Closure");
		layeredPane.setLayer(lblIntersection, 1);
		lblIntersection.setVisible(false);
		layeredPane.add(lblIntersection, "cell 2 0");
		
		lblClosureStart = new JLabel("Start:");
		layeredPane.setLayer(lblClosureStart, 1);
		lblClosureStart.setVisible(false);
		layeredPane.add(lblClosureStart, "cell 3 0,alignx right");
		
		/*Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		SpinnerModel startModel = new SpinnerDateModel(start.getTime(), null, null,Calendar.YEAR);
		JSpinner startSpinner = new JSpinner(startModel);*/
		startSpinner = new DatePicker();
		startSpinner.setVisible(false);
		layeredPane.add(startSpinner, "cell 4 0 3 1,alignx left,aligny center");
		layeredPane.setLayer(startSpinner, 1);
		
		lblClosureEnd = new JLabel("End:");
		layeredPane.setLayer(lblClosureEnd, 1);
		lblClosureEnd.setVisible(false);
		layeredPane.add(lblClosureEnd, "cell 7 0,alignx right");
		
		/*Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		SpinnerModel endModel = new SpinnerDateModel(end.getTime(), null, null,Calendar.YEAR);
		JSpinner endSpinner = new JSpinner(endModel);*/
		endSpinner = new DatePicker();
		endSpinner.setVisible(false);
		layeredPane.add(endSpinner, "cell 8 0 3 1,alignx left,aligny center");
		layeredPane.setLayer(endSpinner, 1);
				
		saveChanges = new JButton("Save Changes");
		layeredPane.setLayer(saveChanges, 1);
		saveChanges.setIcon(null);
		saveChanges.setName("saveChanges");
		saveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate startDate = startSpinner.getDate();
				LocalDate endDate = endSpinner.getDate();

				CourierSystem.CityMap.getIntersection(lblIntersection.getText().substring(0, lblIntersection.getText().length() - " Closure".length())).changeClosure(startDate, endDate);
				try {
					CourierSystem.SaveCityMap();
					hideEditor();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				// indicate that the intersection is closed
				if(!CourierSystem.CityMap.getIntersection(lblIntersection.getText().substring(0, lblIntersection.getText().length() - " Closure".length())).isOpen()) {
					for(Component button :layeredPane.getComponentsInLayer(1)) {
						if(button.getClass() == JButton.class && ((JButton)button).getText().equals(lblIntersection.getText().substring(0, lblIntersection.getText().length() - " Closure".length()))) {
							((JButton)button).setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
							((JButton)button).setText("");
						}
					}
				} else {
					for(Component button :layeredPane.getComponentsInLayer(1)) {
						if(button.getClass() == JButton.class && ((JButton)button).getName().equals(lblIntersection.getText().substring(0, lblIntersection.getText().length() - " Closure".length()))) {
							((JButton)button).setIcon(null);
							((JButton)button).setText(button.getName());
						}
					}
				}
				
			}
			
		});
		saveChanges.setVisible(false);
		layeredPane.add(saveChanges, "cell 12 0 3");
		
		cancel = new JButton("Cancel");
		layeredPane.setLayer(cancel, 1);
		cancel.setIcon(null);
		cancel.setName("cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideEditor();
				CourierSystem.CityMap.getRoute(CourierSystem.CityMap.getIntersection("A1"), CourierSystem.CityMap.getIntersection("F1")).print();
			}
		});
		cancel.setVisible(false);
		layeredPane.add(cancel, "cell 15 0 2 1,alignx left");
		
		JButton a1 = new JButton("A1");
		layeredPane.setLayer(a1, 1);
		a1.setName("A1");
		if(!CourierSystem.CityMap.getIntersection("A1").isOpen()){
			a1.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			a1.setText("");
		}
		a1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("A1");
			}
		});
		layeredPane.add(a1, "cell 3 1,grow");
		
		JButton a2 = new JButton("A2");
		layeredPane.setLayer(a2, 1);
		a2.setName("A2");
		if(!CourierSystem.CityMap.getIntersection("A2").isOpen()){
			a2.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			a2.setText("");
		}
		a2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("A2");
			}
		});
		layeredPane.add(a2, "cell 3 3,grow");

		JButton a3 = new JButton("A3");
		layeredPane.setLayer(a3, 1);
		a3.setName("A3");
		if(!CourierSystem.CityMap.getIntersection("A3").isOpen()){
			a3.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			a3.setText("");
		}
		a3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("A3");
			}
		});
		layeredPane.add(a3, "cell 3 5,grow");

		JButton a4 = new JButton("A4");
		layeredPane.setLayer(a4, 1);
		a4.setName("A4");
		if(!CourierSystem.CityMap.getIntersection("A4").isOpen()){
			a4.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			a4.setText("");
		}
		a4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("A4");
			}
		});
		layeredPane.add(a4, "cell 3 7,grow");

		JButton a5 = new JButton("A5");
		layeredPane.setLayer(a5, 1);
		a5.setName("A5");
		if(!CourierSystem.CityMap.getIntersection("A5").isOpen()){
			a5.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			a5.setText("");
		}
		a5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("A5");
			}
		});
		layeredPane.add(a5, "cell 3 9,grow");

		JButton a6 = new JButton("A6");
		layeredPane.setLayer(a6, 1);
		a6.setName("A6");
		if(!CourierSystem.CityMap.getIntersection("A6").isOpen()){
			a6.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			a6.setText("");
		}
		a6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("A6");
			}
		});
		layeredPane.add(a6, "cell 3 11,grow");

		JButton a7 = new JButton("A7");
		layeredPane.setLayer(a7, 1);
		a7.setName("A7");
		if(!CourierSystem.CityMap.getIntersection("A7").isOpen()){
			a7.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			a7.setText("");
		}
		a7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("A7");
			}
		});
		layeredPane.add(a7, "cell 3 13,grow");
		
		JButton b1 = new JButton("B1");
		layeredPane.setLayer(b1, 1);
		b1.setName("B1");
		if(!CourierSystem.CityMap.getIntersection("B1").isOpen()){
			b1.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			b1.setText("");
		}
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("B1");
			}
		});
		layeredPane.add(b1, "cell 5 1,grow");
		
		JButton b2 = new JButton("B2");
		layeredPane.setLayer(b2, 1);
		b2.setName("B2");
		if(!CourierSystem.CityMap.getIntersection("B2").isOpen()){
			b2.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			b2.setText("");
		}
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("B2");
			}
		});
		layeredPane.add(b2, "cell 5 3,grow");

		JButton b3 = new JButton("B3");
		layeredPane.setLayer(b3, 1);
		b3.setName("B3");
		if(!CourierSystem.CityMap.getIntersection("B3").isOpen()){
			b3.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			b3.setText("");
		}
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("B3");
			}
		});
		layeredPane.add(b3, "cell 5 5,grow");

		JButton b4 = new JButton("B4");
		layeredPane.setLayer(b4, 1);
		b4.setName("B4");
		if(!CourierSystem.CityMap.getIntersection("B4").isOpen()){
			b4.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			b4.setText("");
		}
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("B4");
			}
		});
		layeredPane.add(b4, "cell 5 7,grow");

		JButton b5 = new JButton("B5");
		layeredPane.setLayer(b5, 1);
		b5.setName("B5");
		if(!CourierSystem.CityMap.getIntersection("B5").isOpen()){
			b5.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			b5.setText("");
		}
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("B5");
			}
		});
		layeredPane.add(b5, "cell 5 9,grow");

		JButton b6 = new JButton("B6");
		layeredPane.setLayer(b6, 1);
		b6.setName("B6");
		if(!CourierSystem.CityMap.getIntersection("B6").isOpen()){
			b6.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			b6.setText("");
		}
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("B6");
			}
		});
		layeredPane.add(b6, "cell 5 11,grow");

		JButton b7 = new JButton("B7");
		layeredPane.setLayer(b7, 1);
		b7.setName("B7");
		if(!CourierSystem.CityMap.getIntersection("B7").isOpen()){
			b7.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			b7.setText("");
		}
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("B7");
			}
		});
		layeredPane.add(b7, "cell 5 13,grow");

		JButton c1 = new JButton("C1");
		layeredPane.setLayer(c1, 1);
		c1.setName("C1");
		if(!CourierSystem.CityMap.getIntersection("C1").isOpen()){
			c1.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			c1.setText("");
		}
		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("C1");
			}
		});
		layeredPane.add(c1, "cell 7 1,grow");
		
		JButton c2 = new JButton("C2");
		layeredPane.setLayer(c2, 1);
		c2.setName("C2");
		if(!CourierSystem.CityMap.getIntersection("C2").isOpen()){
			c2.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			c2.setText("");
		}
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("C2");
			}
		});
		layeredPane.add(c2, "cell 7 3,grow");

		JButton c3 = new JButton("C3");
		layeredPane.setLayer(c3, 1);
		c3.setName("C3");
		if(!CourierSystem.CityMap.getIntersection("C3").isOpen()){
			c3.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			c3.setText("");
		}
		c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("C3");
			}
		});
		layeredPane.add(c3, "cell 7 5,grow");

		JButton c4 = new JButton("C4");
		layeredPane.setLayer(c4, 1);
		c4.setName("C4");
		if(!CourierSystem.CityMap.getIntersection("C4").isOpen()){
			c4.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			c4.setText("");
		}
		c4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("C4");
			}
		});
		layeredPane.add(c4, "cell 7 7,grow");

		JButton c5 = new JButton("C5");
		layeredPane.setLayer(c5, 1);
		c5.setName("C5");
		if(!CourierSystem.CityMap.getIntersection("C5").isOpen()){
			c5.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			c5.setText("");
		}
		c5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("C5");
			}
		});
		layeredPane.add(c5, "cell 7 9,grow");

		JButton c6 = new JButton("C6");
		layeredPane.setLayer(c6, 1);
		c6.setName("C6");
		if(!CourierSystem.CityMap.getIntersection("C6").isOpen()){
			c6.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			c6.setText("");
		}
		c6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("C6");
			}
		});
		layeredPane.add(c6, "cell 7 11,grow");

		JButton c7 = new JButton("C7");
		layeredPane.setLayer(c7, 1);
		c7.setIcon(null);
		c7.setName("C7");
		if(!CourierSystem.CityMap.getIntersection("C7").isOpen()){
			c7.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			c7.setText("");
		}
		c7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("C7");
			}
		});
		layeredPane.add(c7, "cell 7 13,grow");

		JButton d1 = new JButton("D1");
		layeredPane.setLayer(d1, 1);
		d1.setName("D1");
		if(!CourierSystem.CityMap.getIntersection("D1").isOpen()){
			d1.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			d1.setText("");
		}
		d1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("D1");
			}
		});
		layeredPane.add(d1, "cell 9 1,grow");
		
		JButton d2 = new JButton("D2");
		layeredPane.setLayer(d2, 1);
		d2.setName("D2");
		if(!CourierSystem.CityMap.getIntersection("D2").isOpen()){
			d2.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			d2.setText("");
		}
		d2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("D2");
			}
		});
		layeredPane.add(d2, "cell 9 3,grow");

		JButton d3 = new JButton("D3");
		layeredPane.setLayer(d3, 1);
		d3.setName("D3");
		if(!CourierSystem.CityMap.getIntersection("D3").isOpen()){
			d3.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			d3.setText("");
		}
		d3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("D3");
			}
		});
		layeredPane.add(d3, "cell 9 5,grow");

		JButton d4 = new JButton("D4");
		layeredPane.setLayer(d4, 1);
		d4.setName("D4");
		if(!CourierSystem.CityMap.getIntersection("D4").isOpen()){
			d4.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			d4.setText("");
		}
		d4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("D4");
			}
		});
		layeredPane.add(d4, "cell 9 7,grow");

		JButton d5 = new JButton("D5");
		layeredPane.setLayer(d5, 1);
		d5.setName("D5");
		if(!CourierSystem.CityMap.getIntersection("D5").isOpen()){
			d5.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			d5.setText("");
		}
		d5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("D5");
			}
		});
		layeredPane.add(d5, "cell 9 9,grow");

		JButton d6 = new JButton("D6");
		layeredPane.setLayer(d6, 1);
		d6.setName("D6");
		if(!CourierSystem.CityMap.getIntersection("D6").isOpen()){
			d6.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			d6.setText("");
		}
		d6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("D6");
			}
		});
		layeredPane.add(d6, "cell 9 11,grow");

		JButton d7 = new JButton("D7");
		layeredPane.setLayer(d7, 1);
		d7.setName("D7");
		if(!CourierSystem.CityMap.getIntersection("D7").isOpen()){
			d7.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			d7.setText("");
		}
		d7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("D7");
			}
		});
		layeredPane.add(d7, "cell 9 13,grow");

		JButton e1 = new JButton("E1");
		layeredPane.setLayer(e1, 1);
		e1.setName("E1");
		if(!CourierSystem.CityMap.getIntersection("E1").isOpen()){
			e1.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			e1.setText("");
		}
		e1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("E1");
			}
		});
		layeredPane.add(e1, "cell 11 1,grow");
		
		JButton e2 = new JButton("E2");
		layeredPane.setLayer(e2, 1);
		e2.setName("E2");
		if(!CourierSystem.CityMap.getIntersection("E2").isOpen()){
			e2.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			e2.setText("");
		}
		e2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("E2");
			}
		});
		layeredPane.add(e2, "cell 11 3,grow");

		JButton e3 = new JButton("E3");
		layeredPane.setLayer(e3, 1);
		e3.setName("E3");
		if(!CourierSystem.CityMap.getIntersection("E3").isOpen()){
			e3.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			e3.setText("");
		}
		e3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("E3");
			}
		});
		layeredPane.add(e3, "cell 11 5,grow");

		JButton e4 = new JButton("E4");
		layeredPane.setLayer(e4, 1);
		e4.setName("E4");
		if(!CourierSystem.CityMap.getIntersection("E4").isOpen()){
			e4.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			e4.setText("");
		}
		e4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("E4");
			}
		});
		layeredPane.add(e4, "cell 11 7,grow");

		JButton e5 = new JButton("E5");
		layeredPane.setLayer(e5, 1);
		e5.setName("E5");
		if(!CourierSystem.CityMap.getIntersection("E5").isOpen()){
			e5.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			e5.setText("");
		}
		e5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("E5");
			}
		});
		layeredPane.add(e5, "cell 11 9,grow");

		JButton e6 = new JButton("E6");
		layeredPane.setLayer(e6, 1);
		e6.setName("E6");
		if(!CourierSystem.CityMap.getIntersection("E6").isOpen()){
			e6.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			e6.setText("");
		}
		e6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("E6");
			}
		});
		layeredPane.add(e6, "cell 11 11,grow");

		JButton e7 = new JButton("E7");
		layeredPane.setLayer(e7, 1);
		e7.setName("E7");
		if(!CourierSystem.CityMap.getIntersection("E7").isOpen()){
			e7.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			e7.setText("");
		}
		e7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("E7");
			}
		});
		layeredPane.add(e7, "cell 11 13,grow");

		JButton f1 = new JButton("F1");
		layeredPane.setLayer(f1, 1);
		f1.setName("F1");
		if(!CourierSystem.CityMap.getIntersection("F1").isOpen()){
			f1.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			f1.setText("");
		}
		f1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("F1");
			}
		});
		layeredPane.add(f1, "cell 13 1,grow");
		
		JButton f2 = new JButton("F2");
		layeredPane.setLayer(f2, 1);
		f2.setName("F2");
		if(!CourierSystem.CityMap.getIntersection("F2").isOpen()){
			f2.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			f2.setText("");
		}
		f2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("F2");
			}
		});
		layeredPane.add(f2, "cell 13 3,grow");

		JButton f3 = new JButton("F3");
		layeredPane.setLayer(f3, 1);
		f3.setName("F3");
		if(!CourierSystem.CityMap.getIntersection("F3").isOpen()){
			f3.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			f3.setText("");
		}
		f3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("F3");
			}
		});
		layeredPane.add(f3, "cell 13 5,grow");

		JButton f4 = new JButton("F4");
		layeredPane.setLayer(f4, 1);
		f4.setName("F4");
		if(!CourierSystem.CityMap.getIntersection("F4").isOpen()){
			f4.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			f4.setText("");
		}
		f4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("F4");
			}
		});
		layeredPane.add(f4, "cell 13 7,grow");

		JButton f5 = new JButton("F5");
		layeredPane.setLayer(f5, 1);
		f5.setName("F5");
		if(!CourierSystem.CityMap.getIntersection("F5").isOpen()){
			f5.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			f5.setText("");
		}
		f5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("F5");
			}
		});
		layeredPane.add(f5, "cell 13 9,grow");

		JButton f6 = new JButton("F6");
		layeredPane.setLayer(f6, 1);
		f6.setName("F6");
		if(!CourierSystem.CityMap.getIntersection("F6").isOpen()){
			f6.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			f6.setText("");
		}
		f6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("F6");
			}
		});
		layeredPane.add(f6, "cell 13 11,grow");

		JButton f7 = new JButton("F7");
		layeredPane.setLayer(f7, 1);
		f7.setName("F7");
		if(!CourierSystem.CityMap.getIntersection("F7").isOpen()){
			f7.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			f7.setText("");
		}
		f7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("F7");
			}
		});
		layeredPane.add(f7, "cell 13 13,grow");

		JButton g1 = new JButton("G1");
		layeredPane.setLayer(g1, 1);
		g1.setName("G1");
		if(!CourierSystem.CityMap.getIntersection("G1").isOpen()){
			g1.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			g1.setText("");
		}
		g1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("G1");
			}
		});
		layeredPane.add(g1, "cell 15 1,grow");
		
		JButton g2 = new JButton("G2");
		layeredPane.setLayer(g2, 1);
		g2.setName("G2");
		if(!CourierSystem.CityMap.getIntersection("G2").isOpen()){
			g2.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			g2.setText("");
		}
		g2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("G2");
			}
		});
		layeredPane.add(g2, "cell 15 3,grow");

		JButton g3 = new JButton("G3");
		layeredPane.setLayer(g3, 1);
		g3.setName("G3");
		if(!CourierSystem.CityMap.getIntersection("G3").isOpen()){
			g3.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			g3.setText("");
		}
		g3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("G3");
			}
		});
		layeredPane.add(g3, "cell 15 5,grow");

		JButton g4 = new JButton("G4");
		layeredPane.setLayer(g4, 1);
		g4.setName("G4");
		if(!CourierSystem.CityMap.getIntersection("G4").isOpen()){
			g4.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			g4.setText("");
		}
		g4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("G4");
			}
		});
		layeredPane.add(g4, "cell 15 7,grow");

		JButton g5 = new JButton("G5");
		layeredPane.setLayer(g5, 1);
		g5.setName("G5");
		if(!CourierSystem.CityMap.getIntersection("G5").isOpen()){
			g5.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			g5.setText("");
		}
		g5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("G5");
			}
		});
		layeredPane.add(g5, "cell 15 9,grow");

		JButton g6 = new JButton("G6");
		layeredPane.setLayer(g6, 1);
		g6.setName("G6");
		if(!CourierSystem.CityMap.getIntersection("G6").isOpen()){
			g6.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			g6.setText("");
		}
		g6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("G6");
			}
		});
		layeredPane.add(g6, "cell 15 11,grow");

		JButton g7 = new JButton("G7");
		layeredPane.setLayer(g7, 1);
		g7.setName("G7");
		if(!CourierSystem.CityMap.getIntersection("G7").isOpen()){
			g7.setIcon(new ImageIcon(MapManagement.class.getResource("/view/cancelled.png")));
			g7.setText("");
		}
		g7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEditor("G7");
			}
		});
		layeredPane.add(g7, "cell 15 13,grow");

		setBounds(layeredPane.getBounds());
		
	}
	
	public void showEditor(String intersection) {
		lblIntersection.setText(CourierSystem.CityMap.getIntersection(intersection).getName() + " Closure");
		startSpinner.setDate(CourierSystem.CityMap.getIntersection(intersection).getCloseStart());
		endSpinner.setDate(CourierSystem.CityMap.getIntersection(intersection).getCloseEnd());
		lblClosureEnd.setVisible(true);
		lblClosureStart.setVisible(true);
		lblIntersection.setVisible(true);
		startSpinner.setVisible(true);
		endSpinner.setVisible(true);
		saveChanges.setVisible(true);
		cancel.setVisible(true);
	}
	
	public void hideEditor() {
		lblClosureEnd.setVisible(false);
		lblClosureStart.setVisible(false);
		lblIntersection.setVisible(false);
		startSpinner.setVisible(false);
		endSpinner.setVisible(false);
		saveChanges.setVisible(false);
		cancel.setVisible(false);
	}
}
