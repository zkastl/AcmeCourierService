package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
				Settings.averageCourierSpeed = (Double) editAverageCourierSpeed.getValue();
				Settings.baseCost = (Double) editBasePrice.getValue();
				Settings.blocksPerMile = Integer.valueOf(editBlocksPerMile.getValue().toString());
				Settings.bonusAmount = (Double) editBonusAmount.getValue();
				Settings.bonusLeeway = Integer.valueOf(editBonusLeewayTime.getValue().toString());
				Settings.courierStartAddress = CourierSystem.CityMap.getIntersection((String) editCourierStartAddress.getSelectedItem());
				Settings.plannedNonTravelTime = (Double) editPlannedNonTravelTime.getValue();
				Settings.pricePerBlock = Double.valueOf(editPricePerBlock.getValue().toString());
			}
			
		});
		add(btnSaveChanges, "cell 1 9,alignx right");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editAverageCourierSpeed.setValue(Settings.averageCourierSpeed);
				editBasePrice.setValue(Settings.baseCost);
				editBlocksPerMile.setValue(Settings.blocksPerMile);
				editBonusAmount.setValue(Settings.bonusAmount);
				editBonusLeewayTime.setValue(Settings.bonusLeeway);
				editCourierStartAddress.setSelectedItem(Settings.courierStartAddress.getName());
				editPlannedNonTravelTime.setValue(Settings.plannedNonTravelTime);
				editPricePerBlock.setValue(Settings.pricePerBlock);
			}
			
		});
		add(btnCancel, "cell 2 9");
		
		JLabel lblBasePrice = new JLabel("Base Price:");
		lblBasePrice.setLabelFor(editBasePrice);
		add(lblBasePrice, "cell 1 1,alignx trailing");
		
		editBasePrice = new JFormattedTextField(money);
		editBasePrice.setValue(Settings.baseCost);
		add(editBasePrice, "cell 2 1,growx");
		editBasePrice.setColumns(6);
		
		JLabel lblPricePerBlock = new JLabel("Price per Block:");
		lblPricePerBlock.setLabelFor(editPricePerBlock);
		add(lblPricePerBlock, "cell 1 2,alignx trailing");
		
		editPricePerBlock = new JFormattedTextField(money);
		editPricePerBlock.setValue(Settings.pricePerBlock);
		add(editPricePerBlock, "cell 2 2,growx");
		editPricePerBlock.setColumns(6);
		
		JLabel lblPlannedNontravelTime = new JLabel("Planned Non-travel Time (min):");
		lblPlannedNontravelTime.setLabelFor(editPlannedNonTravelTime);
		add(lblPlannedNontravelTime, "cell 1 3,alignx trailing");
		
		editPlannedNonTravelTime = new JFormattedTextField(decimal);
		editPlannedNonTravelTime.setValue(Settings.plannedNonTravelTime);
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
		editCourierStartAddress.setSelectedItem(Settings.courierStartAddress.getName());
		add(editCourierStartAddress, "cell 2 4,growx");
				
		JLabel lblAverageCourierSpeed = new JLabel("Average Courier Speed (mph):");
		lblAverageCourierSpeed.setLabelFor(editAverageCourierSpeed);
		add(lblAverageCourierSpeed, "cell 1 5,alignx trailing");
		
		editAverageCourierSpeed = new JFormattedTextField(decimal);
		editAverageCourierSpeed.setValue(Settings.averageCourierSpeed);
		add(editAverageCourierSpeed, "cell 2 5,growx");
		editAverageCourierSpeed.setColumns(6);
		
		JLabel lblBlocksPerMile = new JLabel("Blocks per Mile:");
		lblBlocksPerMile.setLabelFor(editBlocksPerMile);
		add(lblBlocksPerMile, "cell 1 6,alignx trailing");
		
		editBlocksPerMile = new JFormattedTextField(digit);
		editBlocksPerMile.setValue(Settings.blocksPerMile);
		add(editBlocksPerMile, "cell 2 6,growx");
		editBlocksPerMile.setColumns(6);
		
		JLabel lblBonusLeewayTime = new JLabel("Bonus Leeway Time (min):");
		lblBonusLeewayTime.setLabelFor(editBonusLeewayTime);
		add(lblBonusLeewayTime, "cell 1 7,alignx trailing");
		
		editBonusLeewayTime = new JFormattedTextField(digit);
		editBonusLeewayTime.setValue(Settings.bonusLeeway);
		add(editBonusLeewayTime, "cell 2 7,growx");
		editBonusLeewayTime.setColumns(6);
		
		JLabel lblBonusAmount = new JLabel("Bonus Amount:");
		lblBonusAmount.setLabelFor(editBonusAmount);
		add(lblBonusAmount, "cell 1 8,alignx trailing");
		
		editBonusAmount = new JFormattedTextField(money);
		editBonusAmount.setValue(Settings.bonusAmount);
		add(editBonusAmount, "cell 2 8,growx");
		editBonusAmount.setColumns(6);
	}
}
