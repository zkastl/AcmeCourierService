package view;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;

import main.Application;
import main.CourierSystem;
import model.Client;
import model.Courier;
import model.Delivery;
import net.miginfocom.swing.MigLayout;;

public class DeliveryTicketEditor extends JDialog {

	JComboBox<Client> pickupClient;
	JComboBox<Client> deliveryClient;
	JLabel blocks;
	JLabel price;
	Delivery delivery;

	public DeliveryTicketEditor(Delivery delivery) {
		super((JFrame) null, "ACME Delivery Ticket Editor", true);
		this.delivery = delivery;

		setSize(650, 700);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/view/courier logo.png")));
		setTitle("ACME Delivery Ticket Editor");
		setResizable(false);

		getContentPane().setLayout(new MigLayout("", "[10%][10%][10%][10%][10%][20%][20%][10%]",
				"[][][][5:n][][][20:n][][][][][][20:n][][][][][20:n][][][][][][][20][]"));

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(DeliveryTicketEditor.class.getResource("/view/courier logo.png")));
		getContentPane().add(logo, "cell 0 0 8 1,alignx center");

		JLabel lblAcmeCourierService_1 = new JLabel("ACME Courier Service");
		lblAcmeCourierService_1.setIcon(null);
		lblAcmeCourierService_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblAcmeCourierService_1, "flowy,cell 0 1 8 1,alignx center,aligny center");

		JLabel lblDeliveryTicket = new JLabel("Delivery Ticket");
		lblDeliveryTicket.setIcon(null);
		lblDeliveryTicket.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblDeliveryTicket, "cell 0 2 8 1,alignx center,aligny center");

		JLabel lblDate = new JLabel("Date:");
		getContentPane().add(lblDate, "flowx,cell 0 4 8 1,alignx center");

		JLabel lblOrderTakenBy = new JLabel("Order taken by:");
		getContentPane().add(lblOrderTakenBy, "flowx,cell 0 5 8 1,alignx center");

		JLabel lblPickUpInfo = new JLabel("Pick Up Info");
		lblPickUpInfo.setIcon(null);
		lblPickUpInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblPickUpInfo, "cell 2 7 2 1,alignx left");

		JLabel lblClientName = new JLabel("Client Name:");
		lblClientName.setIcon(null);
		getContentPane().add(lblClientName, "cell 2 8 2 1,alignx left");

		JComboBox<Client> pickupClient = new JComboBox<Client>(CourierSystem.Clients.values().toArray(new Client[0]));
		getContentPane().add(pickupClient, "cell 4 8 2 1,growx");

		JLabel lblClientId = new JLabel("Client Id:");
		lblClientId.setIcon(null);
		getContentPane().add(lblClientId, "cell 2 9 2 1,alignx left");

		JLabel pickupClientId = new JLabel("");
		getContentPane().add(pickupClientId, "flowx,cell 4 9");
		pickupClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pickupClientId.setText(String.valueOf(((Client) pickupClient.getSelectedItem()).clientID));
			}
		});

		JLabel lblRequestedPickup = new JLabel("Requested Pickup Time:");
		getContentPane().add(lblRequestedPickup, "cell 2 10 2 1,alignx left");

		DatePickerSettings pickupDateSettings = new DatePickerSettings();
		TimePickerSettings pickupTimeSettings = new TimePickerSettings();
		DateTimePicker pickupEditor = new DateTimePicker(pickupDateSettings, pickupTimeSettings);
		pickupDateSettings.setAllowEmptyDates(false);
		pickupDateSettings.setDateRangeLimits(LocalDate.now(), LocalDate.MAX);
		pickupTimeSettings.setAllowEmptyTimes(false);
		pickupTimeSettings.generatePotentialMenuTimes(TimeIncrement.FifteenMinutes, LocalTime.of(8, 0),
				LocalTime.of(20, 0));
		getContentPane().add(pickupEditor, "cell 4 10 3 1,alignx left");
		int minutesToAdd = 15 - LocalTime.now().getMinute() % 15;
		pickupEditor.timePicker.setTime(LocalTime.now().plusMinutes(minutesToAdd));

		JLabel lblBillPickupClient = new JLabel("Bill Pickup Client?");
		getContentPane().add(lblBillPickupClient, "cell 2 11 2 1,alignx left");

		JCheckBox billToPickup = new JCheckBox("");
		billToPickup.setSelected(true);
		getContentPane().add(billToPickup, "cell 4 11");

		JLabel lblDeliveryInfo = new JLabel("Delivery Info");
		lblDeliveryInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblDeliveryInfo, "cell 2 13 2 1,alignx left");

		JLabel lblClientName_1 = new JLabel("Client Name:");
		getContentPane().add(lblClientName_1, "cell 2 14 2 1,alignx left");

		deliveryClient = new JComboBox<Client>(CourierSystem.Clients.values().toArray(new Client[0]));
		getContentPane().add(deliveryClient, "cell 4 14 2 1,growx");

		JLabel lblClientId_1 = new JLabel("Client Id:");
		getContentPane().add(lblClientId_1, "flowx,cell 2 15 2 1,alignx left");

		JLabel deliveryclientId = new JLabel("");
		getContentPane().add(deliveryclientId, "cell 4 15,alignx center");
		deliveryClient.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deliveryclientId.setText(String.valueOf(((Client) deliveryClient.getSelectedItem()).clientID));
			}
		});

		JLabel lblBillDeliveryClient = new JLabel("Bill Delivery Client?");
		getContentPane().add(lblBillDeliveryClient, "cell 2 16 2 1,alignx left");

		JCheckBox billToDeliver = new JCheckBox("");
		getContentPane().add(billToDeliver, "cell 4 16");

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(billToPickup);
		radioGroup.add(billToDeliver);

		JLabel lblNewLabel = new JLabel("Ticket Info");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblNewLabel, "cell 1 18 2 1,alignx left");

		JLabel lblDeliveryInfo_1 = new JLabel("Delivery Info");
		lblDeliveryInfo_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblDeliveryInfo_1, "cell 5 18 2 1,alignx left");

		JLabel lblPackageId = new JLabel("Package Id:");
		getContentPane().add(lblPackageId, "cell 1 19 2 1,alignx left");

		JLabel packageId = new JLabel(String.valueOf(delivery.packageID));
		getContentPane().add(packageId, "cell 3 19,alignx left");

		JLabel lblCourier = new JLabel("Courier:");
		getContentPane().add(lblCourier, "cell 5 19,alignx left");

		JComboBox<Courier> cbCourier = new JComboBox<Courier>(CourierSystem.Couriers.toArray(new Courier[0]));
		getContentPane().add(cbCourier, "cell 6 19,growx");

		JLabel lblEstimatedDeliveryTime = new JLabel("Estimated Delivery Time:");
		getContentPane().add(lblEstimatedDeliveryTime, "cell 1 20 2 1,alignx left");

		// TODO set this
		JLabel estimatedDelivery = new JLabel("");
		getContentPane().add(estimatedDelivery, "cell 3 20");

		JLabel lblAssignedTime = new JLabel("Assigned Time:");
		getContentPane().add(lblAssignedTime, "cell 5 20,alignx left");

		// TODO set this
		JLabel departureTime = new JLabel("");
		getContentPane().add(departureTime, "cell 6 20");

		JLabel lblEstimatedBlocks = new JLabel("Estimated Blocks:");
		getContentPane().add(lblEstimatedBlocks, "cell 1 21 2 1,alignx left");

		blocks = new JLabel("");
		getContentPane().add(blocks, "cell 3 21");

		JLabel lblPickedUpTime = new JLabel("Picked Up Time:");
		getContentPane().add(lblPickedUpTime, "cell 5 21,alignx left");

		JLabel pickedUpTime = new JLabel("");
		getContentPane().add(pickedUpTime, "cell 6 21,alignx trailing");

		JLabel lblQuotedPrice = new JLabel("Quoted Price:");
		getContentPane().add(lblQuotedPrice, "cell 1 22 2 1,alignx left");

		price = new JLabel("");
		getContentPane().add(price, "cell 3 22");

		JLabel lblDeliveredTime = new JLabel("Delivered Time?");
		getContentPane().add(lblDeliveredTime, "cell 5 22,alignx left");

		JLabel lblBonus = new JLabel("Bonus?");
		getContentPane().add(lblBonus, "cell 5 23,alignx left");

		// TODO: Disable without looking disabled
		JCheckBox bonusEarned = new JCheckBox("");
		getContentPane().add(bonusEarned, "cell 6 23");

		// TODO
		JButton btnSave = new JButton("Ok");
		getContentPane().add(btnSave, "cell 0 25,growx");

		// TODO
		JButton btnApply = new JButton("Apply");
		getContentPane().add(btnApply, "cell 1 25,alignx center");

		// TODO
		JButton btnCancel = new JButton("Cancel");
		getContentPane().add(btnCancel, "cell 2 25");

		JLabel lblOrdertaker = new JLabel(CourierSystem.currentUser.name);
		getContentPane().add(lblOrdertaker, "flowx,cell 0 5 8 1,alignx center");

		JLabel label = new JLabel("06/20/2017");
		getContentPane().add(label, "flowx,cell 0 4 8 1,alignx center");

		JLabel label_1 = new JLabel("Time:");
		getContentPane().add(label_1, "flowx,cell 0 4 8 1,alignx center");

		JLabel label_2 = new JLabel(LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm")));
		getContentPane().add(label_2, "flowx,cell 0 4 8 1,alignx center");

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void CalculateRoute() {
		delivery.calculateRoutes();
		// blocks.setText(String.valueOf(route.getDistance()));
		// price.setText(String.valueOf(route.getDistance() *
		// Settings.pricePerBlock));
	}
}