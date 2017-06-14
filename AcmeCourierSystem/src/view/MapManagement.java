package view;

import java.awt.Container;
import java.awt.Dialog;

import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import model.Intersection;
import model.Map;
import model.Road;

import java.awt.BorderLayout;

public class MapManagement  extends Container {
	private static final long serialVersionUID = 1L;
	private Map map;
	
	public MapManagement(JFrame window) {
		map = new Map();
		
		setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane);
		layeredPane.setLayout(null);
		
		JLabel backgroundMap = new JLabel("");
		backgroundMap.setIcon(new ImageIcon(MapManagement.class.getResource("/view/MapLow.png")));
		backgroundMap.setBounds(0, 0, backgroundMap.getIcon().getIconWidth(), backgroundMap.getIcon().getIconHeight());
		layeredPane.add(backgroundMap);
		
		layeredPane.setBounds(0, 0, backgroundMap.getIcon().getIconWidth(), backgroundMap.getIcon().getIconHeight());
		
		JButton a1 = new JButton("");
		layeredPane.setLayer(a1, 1);
		a1.setIcon(null);
		a1.setBounds(100, 36, 33, 33);
		a1.setOpaque(false);
		a1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Dialog closureDatePicker = new Dialog(window); //TODO
				//map.getIntersection("a1").changeClosure(start, end);
			}
		});
		layeredPane.add(a1);
		
		setBounds(layeredPane.getBounds());
		
	}
	
}
