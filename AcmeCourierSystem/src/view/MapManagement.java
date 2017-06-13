package view;

import java.awt.Container;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Color;

public class MapManagement  extends Container {
	public MapManagement() {
		
		setLayout(new MigLayout("", "[grow][][][grow]", "[grow][][][10][][][10][][grow]"));
		
		JLabel backgroundMap = new JLabel("");
		backgroundMap.setAlignmentX(LEFT_ALIGNMENT);
		backgroundMap.setAlignmentY(TOP_ALIGNMENT);
		backgroundMap.setIcon(new ImageIcon(MapManagement.class.getResource("/view/MapLow.png")));
		add(backgroundMap, "cell 0 0 0 0,alignx center,aligny bottom");
		setBounds(0, 0, backgroundMap.getIcon().getIconWidth(), backgroundMap.getIcon().getIconHeight());
		validate();
	}
	private static final long serialVersionUID = 1L;
}
