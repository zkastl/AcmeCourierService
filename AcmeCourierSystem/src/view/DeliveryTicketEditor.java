package view;

import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.github.lgooddatepicker.components.DateTimePicker;

import main.Application;
import main.CourierSystem;
import model.Client;
import model.Courier;
import model.Delivery;
import net.miginfocom.swing.MigLayout;;

public class DeliveryTicketEditor extends JDialog {

	public DeliveryTicketEditor(Delivery delivery) {
		super((JFrame) null, "ACME Delivery Ticket Editor", true);

		setSize(650, 700);
		setAlwaysOnTop(true);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/view/courier logo.png")));
		setTitle("ACME Delivery Ticket Editor");
		setResizable(false);

		getContentPane().setLayout(new MigLayout("", "[grow][20%][20%,grow][20%,grow][20%][grow]",
				"[][][5:n][][][20:n][][][][][][20:n][][][][][20:n][][][][][][][20][]"));

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(DeliveryTicketEditor.class.getResource("/view/courier logo.png")));
		getContentPane().add(logo, "cell 1 0 4 1,alignx center");

		JLabel lblAcmeCourierService_1 = new JLabel("ACME Courier Service");
		lblAcmeCourierService_1.setIcon(null);
		lblAcmeCourierService_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblAcmeCourierService_1, "flowy,cell 1 1 4 1,alignx center,aligny center");

		JLabel lblDate = new JLabel("Date:");
		getContentPane().add(lblDate, "flowx,cell 2 3 2 1,alignx center");

		JLabel lblOrderTakenBy = new JLabel("Order taken by:");
		getContentPane().add(lblOrderTakenBy, "flowx,cell 2 4 2 1,alignx center");

		JLabel lblPickUpInfo = new JLabel("Pick Up Info");
		lblPickUpInfo.setIcon(null);
		lblPickUpInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblPickUpInfo, "cell 2 6 2 1,alignx center");

		JLabel lblClientName = new JLabel("Client Name:");
		lblClientName.setIcon(null);
		getContentPane().add(lblClientName, "cell 2 7,alignx trailing");

		JComboBox<Client> pickupClient = new JComboBox<Client>(CourierSystem.Clients.values().toArray(new Client[0]));
		getContentPane().add(pickupClient, "cell 3 7,growx");

		JLabel lblClientId = new JLabel("Client Id:");
		lblClientId.setIcon(null);
		getContentPane().add(lblClientId, "cell 2 8,alignx trailing");

		JLabel pickupClientId = new JLabel("");
		getContentPane().add(pickupClientId, "flowx,cell 3 8");

		JLabel lblRequestedPickup = new JLabel("Requested Pickup Time:");
		getContentPane().add(lblRequestedPickup, "cell 2 9,alignx trailing");

		// TODO: validation
		DateTimePicker pickupEditor = new DateTimePicker();
		getContentPane().add(pickupEditor, "cell 3 9 2 1,alignx left");

		JLabel lblBillPickupClient = new JLabel("Bill Pickup Client?");
		getContentPane().add(lblBillPickupClient, "cell 2 10,alignx trailing");

		JCheckBox billToPickup = new JCheckBox("");
		billToPickup.setSelected(true);
		getContentPane().add(billToPickup, "cell 3 10");

		JLabel lblDeliveryInfo = new JLabel("Delivery Info");
		lblDeliveryInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblDeliveryInfo, "cell 2 12 2 1,alignx center");

		JLabel lblClientName_1 = new JLabel("Client Name:");
		getContentPane().add(lblClientName_1, "cell 2 13,alignx trailing");

		JComboBox<Client> deliveryClient = new JComboBox<Client>(CourierSystem.Clients.values().toArray(new Client[0]));
		getContentPane().add(deliveryClient, "cell 3 13,growx");

		JLabel lblClientId_1 = new JLabel("Client Id:");
		getContentPane().add(lblClientId_1, "flowx,cell 2 14,alignx trailing");

		JLabel lblDeliveryclient = new JLabel("");
		getContentPane().add(lblDeliveryclient, "cell 3 14,alignx center");

		JLabel lblBillDeliveryClient = new JLabel("Bill Delivery Client?");
		getContentPane().add(lblBillDeliveryClient, "cell 2 15,alignx trailing");

		JCheckBox billToDeliver = new JCheckBox("");
		getContentPane().add(billToDeliver, "cell 3 15");

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(billToPickup);
		radioGroup.add(billToDeliver);

		JLabel lblNewLabel = new JLabel("Ticket Info");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblNewLabel, "cell 1 17 2 1,alignx center");

		JLabel lblDeliveryInfo_1 = new JLabel("Delivery Info");
		lblDeliveryInfo_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblDeliveryInfo_1, "cell 3 17 2 1,alignx center");

		JLabel lblPackageId = new JLabel("Package Id:");
		getContentPane().add(lblPackageId, "cell 1 18,alignx trailing");

		JLabel packageId = new JLabel("");
		getContentPane().add(packageId, "cell 2 18");

		JLabel lblCourier = new JLabel("Courier:");
		getContentPane().add(lblCourier, "cell 3 18,alignx trailing");

		JComboBox<Courier> cbCourier = new JComboBox<Courier>(CourierSystem.Couriers.toArray(new Courier[0]));
		getContentPane().add(cbCourier, "cell 4 18,growx");

		JLabel lblEstimatedDeliveryTime = new JLabel("Estimated Delivery Time:");
		getContentPane().add(lblEstimatedDeliveryTime, "cell 1 19,alignx trailing");

		JLabel estimatedDelivery = new JLabel("");
		getContentPane().add(estimatedDelivery, "cell 2 19");

		JLabel lblAssignedTime = new JLabel("Assigned Time:");
		getContentPane().add(lblAssignedTime, "cell 3 19,alignx trailing");

		JLabel departureTime = new JLabel("");
		getContentPane().add(departureTime, "cell 4 19");

		JLabel lblEstimatedBlocks = new JLabel("Estimated Blocks:");
		getContentPane().add(lblEstimatedBlocks, "cell 1 20,alignx trailing");

		JLabel blocks = new JLabel("");
		getContentPane().add(blocks, "cell 2 20");

		JLabel lblPickedUpTime = new JLabel("Picked Up Time:");
		getContentPane().add(lblPickedUpTime, "cell 3 20,alignx trailing");

		JLabel lblQuotedPrice = new JLabel("Quoted Price:");
		getContentPane().add(lblQuotedPrice, "cell 1 21,alignx trailing");

		JLabel price = new JLabel("");
		getContentPane().add(price, "cell 2 21");

		JLabel lblDeliveredTime = new JLabel("Delivered Time?");
		getContentPane().add(lblDeliveredTime, "cell 3 21,alignx trailing");

		JLabel lblBonus = new JLabel("Bonus?");
		getContentPane().add(lblBonus, "cell 3 22,alignx trailing");

		// TODO: Disable without looking disabled
		JCheckBox bonusEarned = new JCheckBox("");
		getContentPane().add(bonusEarned, "cell 4 22");

		// TODO
		JButton btnSave = new JButton("Ok");
		getContentPane().add(btnSave, "cell 0 24,growx");

		// TODO
		JButton btnApply = new JButton("Apply");
		getContentPane().add(btnApply, "flowx,cell 1 24,alignx center");

		// TODO
		JButton btnCancel = new JButton("Cancel");
		getContentPane().add(btnCancel, "cell 1 24");

		JLabel lblDateValue = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		getContentPane().add(lblDateValue, "cell 2 3 2 1");

		JLabel lblTime = new JLabel("Time:");
		getContentPane().add(lblTime, "cell 2 3 2 1");

		JLabel lblTimeValue = new JLabel(LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm a")));
		getContentPane().add(lblTimeValue, "cell 2 3 2 1");

		JLabel lblOrdertaker = new JLabel(CourierSystem.currentUser.name);
		getContentPane().add(lblOrdertaker, "cell 2 4 2 1");

		JLabel lblDeliveryTicket = new JLabel("Delivery Ticket");
		lblDeliveryTicket.setIcon(null);
		lblDeliveryTicket.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblDeliveryTicket, "cell 1 1 4 1,alignx center,aligny center");

		setLocationRelativeTo(null);
		setVisible(true);
	}
}