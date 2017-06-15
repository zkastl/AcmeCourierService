package view;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JLayeredPane;
import model.Map;
import java.awt.BorderLayout;
import java.awt.Component;

public class MapManagement  extends Container {
	private static final long serialVersionUID = 1L;
	private Map map;
	
	public MapManagement(JFrame window) {
		map = new Map();
		
		setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(new MigLayout("", "[0][4.00px][34.00][24.00][41.00][24.00][46.00][24.00][46.00][24][39.00][24][45.00][24][38.00][24.00][49.00]", "[25.00][24.00px][55.00][24.00][56.00][24.00][55.00][24.00][57.00][24.00][59.00][24.00][56.00][24.00][17.00][50][]"));
		
		JLabel backgroundMap = new JLabel("");
		backgroundMap.setIcon(new ImageIcon(MapManagement.class.getResource("/view/MapLow.png")));
		layeredPane.add(backgroundMap, "cell 0 0 17 15,alignx right,aligny center");
		
		JLabel lblIntersection = new JLabel("Z0 Closure");
		layeredPane.setLayer(lblIntersection, 1);
		lblIntersection.setVisible(false);
		layeredPane.add(lblIntersection, "cell 2 0");
		
		JLabel lblClosureStart = new JLabel("Start:");
		layeredPane.setLayer(lblClosureStart, 1);
		lblClosureStart.setVisible(false);
		layeredPane.add(lblClosureStart, "cell 3 0 2");
		
		Calendar start = Calendar.getInstance();
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		SpinnerModel startModel = new SpinnerDateModel(start.getTime(), null, null,Calendar.YEAR);
		JSpinner startSpinner = new JSpinner(startModel);
		startSpinner.setVisible(false);
		layeredPane.add(startSpinner, "cell 4 0 4 1,alignx left,aligny center");
		layeredPane.setLayer(startSpinner, 1);
		
		JLabel lblClosureEnd = new JLabel("End:");
		layeredPane.setLayer(lblClosureEnd, 1);
		lblClosureEnd.setVisible(false);
		layeredPane.add(lblClosureEnd, "cell 7 0");
		
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		SpinnerModel endModel = new SpinnerDateModel(end.getTime(), null, null,Calendar.YEAR);
		JSpinner endSpinner = new JSpinner(endModel);
		endSpinner.setVisible(false);
		layeredPane.add(endSpinner, "cell 8 0 4 1,alignx left,aligny center");
		layeredPane.setLayer(endSpinner, 1);
		
		//layeredPane.setBounds(0, 0, backgroundMap.getIcon().getIconWidth(), backgroundMap.getIcon().getIconHeight());
		
		JButton saveChanges = new JButton("Save Changes");
		layeredPane.setLayer(saveChanges, 1);
		saveChanges.setIcon(null);
		saveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar startTime = Calendar.getInstance();
				startTime.setTime((Date)startSpinner.getValue());
				Calendar endTime = Calendar.getInstance();
				endTime.setTime((Date)endSpinner.getValue());
				
				map.getIntersection(lblIntersection.getText().substring(0, lblIntersection.getText().length() - " Closure".length())).changeClosure(startTime, endTime);
				if(!map.getIntersection(lblIntersection.getText().substring(0, lblIntersection.getText().length() - " Closure".length())).isOpen()) {
					for(Component button :layeredPane.getComponentsInLayer(1)) {
						if(button.getClass() == JButton.class && ((JButton)button).getText().equals(lblIntersection.getText().length() - " Closure".length())) {
							((JButton)button).setFont(new Font("Tahoma", 1, 5));
						}
					}
				} else {
					for(Component button :layeredPane.getComponentsInLayer(1)) {
						if(button.getClass() == JButton.class && ((JButton)button).getText().equals(lblIntersection.getText().length() - " Closure".length())) {
							((JButton)button).setFont(new Font("Tahoma", 1, 11));
						}
					}
				}
				
			}
		});
		saveChanges.setVisible(false);
		layeredPane.add(saveChanges, "cell 11 0 3");
		
		JButton cancel = new JButton("Cancel");
		layeredPane.setLayer(cancel, 1);
		cancel.setIcon(null);
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblClosureEnd.setVisible(false);
				lblClosureStart.setVisible(false);
				lblIntersection.setVisible(false);
				startSpinner.setVisible(false);
				endSpinner.setVisible(false);
				saveChanges.setVisible(false);
				cancel.setVisible(false);
				map.getRoute(map.getIntersection("A1"), map.getIntersection("F1")).print();
			}
		});
		cancel.setVisible(false);
		layeredPane.add(cancel, "cell 14 0 2 1,alignx left");
		
		JButton a1 = new JButton("A1");
		layeredPane.setLayer(a1, 1);
		a1.setIcon(null);
		a1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("A1").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("A1").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("A1").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(a1, "cell 3 1,grow");
		
		JButton a2 = new JButton("A2");
		layeredPane.setLayer(a2, 1);
		a2.setIcon(null);
		a2.setOpaque(false);
		a2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("A2").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("A2").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("A2").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(a2, "cell 3 3,grow");

		JButton a3 = new JButton("A3");
		layeredPane.setLayer(a3, 1);
		a3.setIcon(null);
		a3.setOpaque(false);
		a3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("A3").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("A3").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("A3").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(a3, "cell 3 5,grow");

		JButton a4 = new JButton("A4");
		layeredPane.setLayer(a4, 1);
		a4.setIcon(null);
		a4.setOpaque(false);
		a4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("A4").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("A4").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("A4").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(a4, "cell 3 7,grow");

		JButton a5 = new JButton("A5");
		layeredPane.setLayer(a5, 1);
		a5.setIcon(null);
		a5.setOpaque(false);
		a5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("A5").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("A5").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("A5").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(a5, "cell 3 9,grow");

		JButton a6 = new JButton("A6");
		layeredPane.setLayer(a6, 1);
		a6.setIcon(null);
		a6.setOpaque(false);
		a6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("A6").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("A6").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("A6").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(a6, "cell 3 11,grow");

		JButton a7 = new JButton("A7");
		layeredPane.setLayer(a7, 1);
		a7.setIcon(null);
		a7.setOpaque(false);
		a7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("A7").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("A7").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("A7").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(a7, "cell 3 13,grow");
		
		JButton b1 = new JButton("B1");
		layeredPane.setLayer(b1, 1);
		b1.setIcon(null);
		b1.setOpaque(false);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("B1").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("B1").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("B1").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(b1, "cell 5 1,grow");
		
		JButton b2 = new JButton("B2");
		layeredPane.setLayer(b2, 1);
		b2.setIcon(null);
		b2.setOpaque(false);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("B2").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("B2").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("B2").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(b2, "cell 5 3,grow");

		JButton b3 = new JButton("B3");
		layeredPane.setLayer(b3, 1);
		b3.setIcon(null);
		b3.setOpaque(false);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("B3").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("B3").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("B3").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(b3, "cell 5 5,grow");

		JButton b4 = new JButton("B4");
		layeredPane.setLayer(b4, 1);
		b4.setIcon(null);
		b4.setOpaque(false);
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("B4").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("B4").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("B4").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(b4, "cell 5 7,grow");

		JButton b5 = new JButton("B5");
		layeredPane.setLayer(b5, 1);
		b5.setIcon(null);
		b5.setOpaque(false);
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("B5").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("B5").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("B5").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(b5, "cell 5 9,grow");

		JButton b6 = new JButton("B6");
		layeredPane.setLayer(b6, 1);
		b6.setIcon(null);
		b6.setOpaque(false);
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("B6").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("B6").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("B6").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(b6, "cell 5 11,grow");

		JButton b7 = new JButton("B7");
		layeredPane.setLayer(b7, 1);
		b7.setIcon(null);
		b7.setOpaque(false);
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("B7").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("B7").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("B7").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(b7, "cell 5 13,grow");

		JButton c1 = new JButton("C1");
		layeredPane.setLayer(c1, 1);
		c1.setIcon(null);
		c1.setOpaque(false);
		c1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("C1").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("C1").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("C1").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(c1, "cell 7 1,grow");
		
		JButton c2 = new JButton("C2");
		layeredPane.setLayer(c2, 1);
		c2.setIcon(null);
		c2.setOpaque(false);
		c2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("C2").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("C2").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("C2").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(c2, "cell 7 3,grow");

		JButton c3 = new JButton("C3");
		layeredPane.setLayer(c3, 1);
		c3.setIcon(null);
		c3.setOpaque(false);
		c3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("C3").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("C3").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("C3").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(c3, "cell 7 5,grow");

		JButton c4 = new JButton("C4");
		layeredPane.setLayer(c4, 1);
		c4.setIcon(null);
		c4.setOpaque(false);
		c4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("C4").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("C4").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("C4").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(c4, "cell 7 7,grow");

		JButton c5 = new JButton("C5");
		layeredPane.setLayer(c5, 1);
		c5.setIcon(null);
		c5.setOpaque(false);
		c5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("C5").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("C5").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("C5").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(c5, "cell 7 9,grow");

		JButton c6 = new JButton("C6");
		layeredPane.setLayer(c6, 1);
		c6.setIcon(null);
		c6.setOpaque(false);
		c6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("C6").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("C6").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("C6").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(c6, "cell 7 11,grow");

		JButton c7 = new JButton("C7");
		layeredPane.setLayer(c7, 1);
		c7.setIcon(null);
		c7.setOpaque(false);
		c7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("C7").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("C7").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("C7").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(c7, "cell 7 13,grow");

		JButton d1 = new JButton("D1");
		layeredPane.setLayer(d1, 1);
		d1.setIcon(null);
		d1.setOpaque(false);
		d1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("D1").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("D1").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("D1").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(d1, "cell 9 1,grow");
		
		JButton d2 = new JButton("D2");
		layeredPane.setLayer(d2, 1);
		d2.setIcon(null);
		d2.setOpaque(false);
		d2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("D2").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("D2").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("D2").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(d2, "cell 9 3,grow");

		JButton d3 = new JButton("D3");
		layeredPane.setLayer(d3, 1);
		d3.setIcon(null);
		d3.setOpaque(false);
		d3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("D3").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("D3").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("D3").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(d3, "cell 9 5,grow");

		JButton d4 = new JButton("D4");
		layeredPane.setLayer(d4, 1);
		d4.setIcon(null);
		d4.setOpaque(false);
		d4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("D4").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("D4").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("D4").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(d4, "cell 9 7,grow");

		JButton d5 = new JButton("D5");
		layeredPane.setLayer(d5, 1);
		d5.setIcon(null);
		d5.setOpaque(false);
		d5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("D5").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("D5").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("D5").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(d5, "cell 9 9,grow");

		JButton d6 = new JButton("D6");
		layeredPane.setLayer(d6, 1);
		d6.setIcon(null);
		d6.setOpaque(false);
		d6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("D6").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("D6").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("D6").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(d6, "cell 9 11,grow");

		JButton d7 = new JButton("D7");
		layeredPane.setLayer(d7, 1);
		d7.setIcon(null);
		d7.setOpaque(false);
		d7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("D7").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("D7").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("D7").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(d7, "cell 9 13,grow");

		JButton e1 = new JButton("E1");
		layeredPane.setLayer(e1, 1);
		e1.setIcon(null);
		e1.setOpaque(false);
		e1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("E1").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("E1").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("E1").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(e1, "cell 11 1,grow");
		
		JButton e2 = new JButton("E2");
		layeredPane.setLayer(e2, 1);
		e2.setIcon(null);
		e2.setOpaque(false);
		e2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("E2").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("E2").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("E2").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(e2, "cell 11 3,grow");

		JButton e3 = new JButton("E3");
		layeredPane.setLayer(e3, 1);
		e3.setIcon(null);
		e3.setOpaque(false);
		e3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("E3").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("E3").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("E3").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(e3, "cell 11 5,grow");

		JButton e4 = new JButton("E4");
		layeredPane.setLayer(e4, 1);
		e4.setIcon(null);
		e4.setOpaque(false);
		e4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("E4").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("E4").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("E4").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(e4, "cell 11 7,grow");

		JButton e5 = new JButton("E5");
		layeredPane.setLayer(e5, 1);
		e5.setIcon(null);
		e5.setOpaque(false);
		e5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("E5").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("E5").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("E5").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(e5, "cell 11 9,grow");

		JButton e6 = new JButton("E6");
		layeredPane.setLayer(e6, 1);
		e6.setIcon(null);
		e6.setOpaque(false);
		e6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("E6").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("E6").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("E6").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(e6, "cell 11 11,grow");

		JButton e7 = new JButton("E7");
		layeredPane.setLayer(e7, 1);
		e7.setIcon(null);
		e7.setOpaque(false);
		e7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("E7").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("E7").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("E7").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(e7, "cell 11 13,grow");

		JButton f1 = new JButton("F1");
		layeredPane.setLayer(f1, 1);
		f1.setIcon(null);
		f1.setOpaque(false);
		f1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("F1").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("F1").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("F1").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(f1, "cell 13 1,grow");
		
		JButton f2 = new JButton("F2");
		layeredPane.setLayer(f2, 1);
		f2.setIcon(null);
		f2.setOpaque(false);
		f2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("F2").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("F2").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("F2").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(f2, "cell 13 3,grow");

		JButton f3 = new JButton("F3");
		layeredPane.setLayer(f3, 1);
		f3.setIcon(null);
		f3.setOpaque(false);
		f3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("F3").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("F3").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("F3").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(f3, "cell 13 5,grow");

		JButton f4 = new JButton("F4");
		layeredPane.setLayer(f4, 1);
		f4.setIcon(null);
		f4.setOpaque(false);
		f4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("F4").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("F4").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("F4").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(f4, "cell 13 7,grow");

		JButton f5 = new JButton("F5");
		layeredPane.setLayer(f5, 1);
		f5.setIcon(null);
		f5.setOpaque(false);
		f5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("F5").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("F5").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("F5").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(f5, "cell 13 9,grow");

		JButton f6 = new JButton("F6");
		layeredPane.setLayer(f6, 1);
		f6.setIcon(null);
		f6.setOpaque(false);
		f6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("F6").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("F6").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("F6").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(f6, "cell 13 11,grow");

		JButton f7 = new JButton("F7");
		layeredPane.setLayer(f7, 1);
		f7.setIcon(null);
		f7.setOpaque(false);
		f7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("F7").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("F7").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("F7").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(f7, "cell 13 13,grow");

		JButton g1 = new JButton("G1");
		layeredPane.setLayer(g1, 1);
		g1.setIcon(null);
		g1.setOpaque(false);
		g1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("G1").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("G1").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("G1").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(g1, "cell 15 1,grow");
		
		JButton g2 = new JButton("G2");
		layeredPane.setLayer(g2, 1);
		g2.setIcon(null);
		g2.setOpaque(false);
		g2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("G2").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("G2").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("G2").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(g2, "cell 15 3,grow");

		JButton g3 = new JButton("G3");
		layeredPane.setLayer(g3, 1);
		g3.setIcon(null);
		g3.setOpaque(false);
		g3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("G3").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("G3").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("G3").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(g3, "cell 15 5,grow");

		JButton g4 = new JButton("G4");
		layeredPane.setLayer(g4, 1);
		g4.setIcon(null);
		g4.setOpaque(false);
		g4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("G4").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("G4").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("G4").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(g4, "cell 15 7,grow");

		JButton g5 = new JButton("G5");
		layeredPane.setLayer(g5, 1);
		g5.setIcon(null);
		g5.setOpaque(false);
		g5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("G5").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("G5").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("G5").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(g5, "cell 15 9,grow");

		JButton g6 = new JButton("G6");
		layeredPane.setLayer(g6, 1);
		g6.setIcon(null);
		g6.setOpaque(false);
		g6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("G6").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("G6").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("G6").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(g6, "cell 15 11,grow");

		JButton g7 = new JButton("G7");
		layeredPane.setLayer(g7, 1);
		g7.setIcon(null);
		g7.setOpaque(false);
		g7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblIntersection.setText(map.getIntersection("G7").getName() + " Closure");
				startSpinner.setValue(map.getIntersection("G7").getCloseStart().getTime());
				endSpinner.setValue(map.getIntersection("G7").getCloseEnd().getTime());
				lblClosureEnd.setVisible(true);
				lblClosureStart.setVisible(true);
				lblIntersection.setVisible(true);
				startSpinner.setVisible(true);
				endSpinner.setVisible(true);
				saveChanges.setVisible(true);
				cancel.setVisible(true);
			}
		});
		layeredPane.add(g7, "cell 15 13,grow");

		setBounds(layeredPane.getBounds());
	}
	
}
