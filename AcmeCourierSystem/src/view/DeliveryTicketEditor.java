package view;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import main.Application;
import model.Delivery;
import net.miginfocom.swing.MigLayout;

public class DeliveryTicketEditor extends JDialog {

	public DeliveryTicketEditor(Delivery delivery) {
		super((JFrame) null, "ACME Delivery Ticket Editor", true);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/view/courier logo.png")));
		setTitle("ACME Delivery Ticket Editor");
		setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

		setVisible(true);

		setLayout(new MigLayout("", "[25%][25%][25%][25%]", "[][][][][][][][][][][][][][][][][][][][][][]"));
		JLabel lblAcmeCourierService = new JLabel("");
		lblAcmeCourierService.setIcon(new ImageIcon(DeliveryTicketEditor.class.getResource("/view/courier logo.png")));
		add(lblAcmeCourierService, "cell 0 0 4 1,alignx center");

		JLabel lblAcmeCourierService_1 = new JLabel("ACME Courier Service");
		lblAcmeCourierService_1.setIcon(null);
		lblAcmeCourierService_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblAcmeCourierService_1, "cell 0 1 4 1,alignx center,aligny center");

		JLabel lblDeliveryTicket = new JLabel("Delivery Ticket");
		lblDeliveryTicket.setIcon(null);
		lblDeliveryTicket.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblDeliveryTicket, "cell 0 2 4 1,alignx center,aligny center");

		JLabel lblPickUpInfo = new JLabel("Pick Up Info");
		lblPickUpInfo.setIcon(null);
		lblPickUpInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblPickUpInfo, "cell 1 4,alignx trailing");

		JLabel lblClientName = new JLabel("Client Name:");
		lblClientName.setIcon(null);
		add(lblClientName, "cell 1 5,alignx trailing");

		JComboBox pickupClient = new JComboBox();
		add(pickupClient, "cell 2 5,growx");

		JLabel lblClientId = new JLabel("Client Id:");
		lblClientId.setIcon(null);
		add(lblClientId, "cell 1 6,alignx trailing");

		JLabel pickupClientId = new JLabel("");
		add(pickupClientId, "cell 2 6");

		JLabel lblRequestedPickup = new JLabel("Requested Pickup Time:");
		add(lblRequestedPickup, "cell 1 7,alignx trailing");

		JLabel lblBillPickupClient = new JLabel("Bill Pickup Client?");
		add(lblBillPickupClient, "cell 1 8,alignx trailing");

		JRadioButton radioButton = new JRadioButton("");
		radioButton.setSelected(true);
		add(radioButton, "cell 2 8");

		JLabel lblDeliveryInfo = new JLabel("Delivery Info");
		lblDeliveryInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblDeliveryInfo, "cell 1 10,alignx center");

		JLabel lblClientName_1 = new JLabel("Client Name:");
		add(lblClientName_1, "cell 1 11,alignx trailing");

		JLabel lblClientId_1 = new JLabel("Client Id:");
		add(lblClientId_1, "flowx,cell 1 12,alignx trailing");

		JLabel lblBillDeliveryClient = new JLabel("Bill Delivery Client?");
		add(lblBillDeliveryClient, "cell 1 13,alignx trailing");

		JRadioButton radioButton_1 = new JRadioButton("");
		add(radioButton_1, "cell 2 13");

		JLabel lblNewLabel = new JLabel("Ticket Info");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel, "cell 0 15,alignx trailing");

		JLabel lblDeliveryInfo_1 = new JLabel("Delivery Info");
		lblDeliveryInfo_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblDeliveryInfo_1, "cell 2 15,alignx trailing");

		JLabel lblPackageId = new JLabel("Package Id:");
		add(lblPackageId, "cell 0 16,alignx trailing");

		JLabel packageId = new JLabel("");
		add(packageId, "cell 1 16");

		JLabel lblCourier = new JLabel("Courier:");
		add(lblCourier, "cell 2 16,alignx trailing");

		JComboBox cbCourier = new JComboBox();
		add(cbCourier, "cell 3 16,growx");

		JLabel lblEstimatedDeliveryTime = new JLabel("Estimated Delivery Time:");
		add(lblEstimatedDeliveryTime, "cell 0 17,alignx trailing");

		JLabel estimatedDelivery = new JLabel("");
		add(estimatedDelivery, "cell 1 17");

		JLabel lblAssignedTime = new JLabel("Assigned Time:");
		add(lblAssignedTime, "cell 2 17,alignx trailing");

		JLabel departureTime = new JLabel("");
		add(departureTime, "cell 3 17");

		JLabel lblEstimatedBlocks = new JLabel("Estimated Blocks:");
		add(lblEstimatedBlocks, "cell 0 18,alignx trailing");

		JLabel blocks = new JLabel("");
		add(blocks, "cell 1 18");

		JLabel lblPickedUpTime = new JLabel("Picked Up Time:");
		add(lblPickedUpTime, "cell 2 18,alignx trailing");

		JLabel lblQuotedPrice = new JLabel("Quoted Price:");
		add(lblQuotedPrice, "cell 0 19,alignx trailing");

		JLabel price = new JLabel("");
		add(price, "cell 1 19");

		JLabel lblDeliveredTime = new JLabel("Delivered Time?");
		add(lblDeliveredTime, "cell 2 19,alignx trailing");

		JLabel lblBonus = new JLabel("Bonus?");
		add(lblBonus, "cell 2 20,alignx trailing");

	}
}