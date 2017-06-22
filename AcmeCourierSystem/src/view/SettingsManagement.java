package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.*;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import main.CourierSystem;
import model.Intersection;
import model.Settings;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class SettingsManagement extends Container{

	private static final long serialVersionUID = 1L;
	private JFormattedTextField editBasePrice;
	private JFormattedTextField editPricePerBlock;
	private JFormattedTextField editPlannedNonTravelTime;
	private JComboBox<String> editCourierStartAddress;
	private JFormattedTextField editAverageCourierSpeed;
	private JFormattedTextField editBlocksPerMile;
	private JFormattedTextField editBonusLeewayTime;
	private JFormattedTextField editBonusAmount;
	private NumberFormat money = NumberFormat.getCurrencyInstance();
	private NumberFormat decimal = NumberFormat.getNumberInstance();
	private NumberFormat digit = NumberFormat.getIntegerInstance();
	
	SettingsManagement(JFrame window) {
		setLayout(new MigLayout("", "[grow][][][grow]", "[grow][][][][][][][][][][grow]"));
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CourierSystem.SystemSettings.averageCourierSpeed = (Double) editAverageCourierSpeed.getValue();
				CourierSystem.SystemSettings.baseCost = (Double) editBasePrice.getValue();
				CourierSystem.SystemSettings.blocksPerMile = Integer.valueOf(editBlocksPerMile.getValue().toString());
				CourierSystem.SystemSettings.bonusAmount = (Double) editBonusAmount.getValue();
				CourierSystem.SystemSettings.bonusLeeway = Integer.valueOf(editBonusLeewayTime.getValue().toString());
				CourierSystem.SystemSettings.courierStartAddress = CourierSystem.CityMap.getIntersection((String) editCourierStartAddress.getSelectedItem());
				CourierSystem.SystemSettings.plannedNonTravelTime = (Double) editPlannedNonTravelTime.getValue();
				CourierSystem.SystemSettings.pricePerBlock = Double.valueOf(editPricePerBlock.getValue().toString());
				try {
					CourierSystem.SaveSettings();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		add(btnSaveChanges, "cell 1 9,alignx right");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editAverageCourierSpeed.setValue(CourierSystem.SystemSettings.averageCourierSpeed);
				editBasePrice.setValue(CourierSystem.SystemSettings.baseCost);
				editBlocksPerMile.setValue(CourierSystem.SystemSettings.blocksPerMile);
				editBonusAmount.setValue(CourierSystem.SystemSettings.bonusAmount);
				editBonusLeewayTime.setValue(CourierSystem.SystemSettings.bonusLeeway);
				editCourierStartAddress.setSelectedItem(CourierSystem.SystemSettings.courierStartAddress.getName());
				editPlannedNonTravelTime.setValue(CourierSystem.SystemSettings.plannedNonTravelTime);
				editPricePerBlock.setValue(CourierSystem.SystemSettings.pricePerBlock);
			}
			
		});
		add(btnCancel, "cell 2 9");
		
		JLabel lblBasePrice = new JLabel("Base Price:");
		lblBasePrice.setLabelFor(editBasePrice);
		add(lblBasePrice, "cell 1 1,alignx trailing");
		
		editBasePrice = new JFormattedTextField(money);
		editBasePrice.setValue(CourierSystem.SystemSettings.baseCost);
		add(editBasePrice, "cell 2 1,growx");
		editBasePrice.setColumns(6);
		
		JLabel lblPricePerBlock = new JLabel("Price per Block:");
		lblPricePerBlock.setLabelFor(editPricePerBlock);
		add(lblPricePerBlock, "cell 1 2,alignx trailing");
		
		editPricePerBlock = new JFormattedTextField(money);
		editPricePerBlock.setValue(CourierSystem.SystemSettings.pricePerBlock);
		add(editPricePerBlock, "cell 2 2,growx");
		editPricePerBlock.setColumns(6);
		
		JLabel lblPlannedNontravelTime = new JLabel("Planned Non-travel Time (min):");
		lblPlannedNontravelTime.setLabelFor(editPlannedNonTravelTime);
		add(lblPlannedNontravelTime, "cell 1 3,alignx trailing");
		
		editPlannedNonTravelTime = new JFormattedTextField(decimal);
		editPlannedNonTravelTime.setValue(CourierSystem.SystemSettings.plannedNonTravelTime);
		add(editPlannedNonTravelTime, "cell 2 3,growx");
		editPlannedNonTravelTime.setColumns(6);
		
		JLabel lblStartAddress = new JLabel("Courier Start Address:");
		lblStartAddress.setLabelFor(editCourierStartAddress);
		add(lblStartAddress, "cell 1 4,alignx trailing");
		
		editCourierStartAddress = new JComboBox<String>();
		ArrayList<String> names = new ArrayList<String>();
		for(Intersection i : CourierSystem.CityMap.getIntersections().values()) {
			names.add(i.getName());
		}
		names.sort(null);
		for(String s: names) {
			editCourierStartAddress.addItem(s);
		}
		editCourierStartAddress.setSelectedItem(CourierSystem.SystemSettings.courierStartAddress.getName());
		add(editCourierStartAddress, "cell 2 4,growx");
				
		JLabel lblAverageCourierSpeed = new JLabel("Average Courier Speed (mph):");
		lblAverageCourierSpeed.setLabelFor(editAverageCourierSpeed);
		add(lblAverageCourierSpeed, "cell 1 5,alignx trailing");
		
		editAverageCourierSpeed = new JFormattedTextField(decimal);
		editAverageCourierSpeed.setValue(CourierSystem.SystemSettings.averageCourierSpeed);
		add(editAverageCourierSpeed, "cell 2 5,growx");
		editAverageCourierSpeed.setColumns(6);
		
		JLabel lblBlocksPerMile = new JLabel("Blocks per Mile:");
		lblBlocksPerMile.setLabelFor(editBlocksPerMile);
		add(lblBlocksPerMile, "cell 1 6,alignx trailing");
		
		editBlocksPerMile = new JFormattedTextField(digit);
		editBlocksPerMile.setValue(CourierSystem.SystemSettings.blocksPerMile);
		add(editBlocksPerMile, "cell 2 6,growx");
		editBlocksPerMile.setColumns(6);
		
		JLabel lblBonusLeewayTime = new JLabel("Bonus Leeway Time (min):");
		lblBonusLeewayTime.setLabelFor(editBonusLeewayTime);
		add(lblBonusLeewayTime, "cell 1 7,alignx trailing");
		
		editBonusLeewayTime = new JFormattedTextField(digit);
		editBonusLeewayTime.setValue(CourierSystem.SystemSettings.bonusLeeway);
		add(editBonusLeewayTime, "cell 2 7,growx");
		editBonusLeewayTime.setColumns(6);
		
		JLabel lblBonusAmount = new JLabel("Bonus Amount:");
		lblBonusAmount.setLabelFor(editBonusAmount);
		add(lblBonusAmount, "cell 1 8,alignx trailing");
		
		editBonusAmount = new JFormattedTextField(money);
		editBonusAmount.setValue(CourierSystem.SystemSettings.bonusAmount);
		add(editBonusAmount, "cell 2 8,growx");
		editBonusAmount.setColumns(6);
	}
}
