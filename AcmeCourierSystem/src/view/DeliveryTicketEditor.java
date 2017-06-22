package view;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import javax.swing.JOptionPane;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;

import main.Application;
import main.CourierSystem;
import model.Client;
import model.Delivery;
import model.DeliveryStatus;
import model.Employee;
import model.EmployeeRole;
import net.miginfocom.swing.MigLayout;;

public class DeliveryTicketEditor extends JDialog {

	JComboBox<Client> pickupClient;
	JComboBox<Client> deliveryClient;
	JLabel blocks;
	JLabel price;
	JLabel estimatedDeliveryTime;
	JLabel departureTime;
	Delivery delivery;
	JCheckBox billToPickup;
	JCheckBox billToDeliver;
	TimePicker pickedUpTime;
	TimePicker deliveredTime;
	JCheckBox bonusEarned;
	DeliveryTableModel deliveryTable;
	DateTimePicker pickupEditor;
	JComboBox<Employee> cbCourier;
	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm a");
	private boolean saved = true;

	public DeliveryTicketEditor(Delivery delivery, DeliveryTableModel deliveryTable) {
		super((JFrame) null, "ACME Delivery Ticket Editor", true);
		this.delivery = delivery;
		this.deliveryTable = deliveryTable;
		if (delivery.packageID == 0) {
			saved = false;
		}

		setSize(650, 740);
		setAlwaysOnTop(true);

		setIconImage(Toolkit.getDefaultToolkit().getImage(Application.class.getResource("/view/courier logo.png")));
		setTitle("ACME Delivery Ticket Editor");
		setResizable(false);

		getContentPane().setLayout(new MigLayout("", "[10%][10%][10%][10%][10%][20%][20%][10%]",
				"[][][][5:n][][][20:n][][][][][][20:n][][][][][20:n][][][][][][][20][]"));

		pickupClient = new JComboBox<Client>(CourierSystem.Clients.values().toArray(new Client[0]));
		pickupClient.setSelectedItem(delivery.pickupClient);
		getContentPane().add(pickupClient, "cell 4 8 2 1,growx");

		DatePickerSettings pickupDateSettings = new DatePickerSettings();
		TimePickerSettings pickupTimeSettings = new TimePickerSettings();
		pickupEditor = new DateTimePicker(pickupDateSettings, pickupTimeSettings);
		pickupDateSettings.setAllowEmptyDates(false);
		pickupDateSettings.setDateRangeLimits(LocalDate.now(), LocalDate.MAX);
		pickupTimeSettings.setAllowEmptyTimes(false);
		pickupTimeSettings.generatePotentialMenuTimes(TimeIncrement.FifteenMinutes, LocalTime.of(8, 0),
				LocalTime.of(20, 0));
		getContentPane().add(pickupEditor, "cell 4 10 3 1,alignx left");
		if (delivery.packageID == 0) {
			int minutesToAdd = 15 - LocalTime.now().getMinute() % 15;
			pickupEditor.timePicker.setTime(LocalTime.now().plusMinutes(minutesToAdd));
		} else {
			pickupEditor.setDateTimePermissive(delivery.requestedPickupTime);
		}

		billToPickup = new JCheckBox("", delivery.billToSender);
		getContentPane().add(billToPickup, "cell 4 11");

		deliveryClient = new JComboBox<Client>(CourierSystem.Clients.values().toArray(new Client[0]));
		deliveryClient.setSelectedItem(delivery.deliveryClient);
		getContentPane().add(deliveryClient, "cell 4 14 2 1,growx");

		billToDeliver = new JCheckBox("", !delivery.billToSender);
		getContentPane().add(billToDeliver, "cell 4 16");

		cbCourier = new JComboBox<Employee>();
		for (Employee e : CourierSystem.Employees.values()) {
			if (e.role == EmployeeRole.Courier)
				cbCourier.addItem(e);
		}
		cbCourier.setSelectedItem(delivery.assignedCourier);
		getContentPane().add(cbCourier, "cell 6 19,growx");

		estimatedDeliveryTime = new JLabel(
				delivery.estimatedDeliveryTime != null ? timeFormat.format(delivery.estimatedDeliveryTime) : "");
		getContentPane().add(estimatedDeliveryTime, "cell 3 20");

		departureTime = new JLabel(
				delivery.calculatedDepartureTime != null ? timeFormat.format(delivery.calculatedDepartureTime) : "");
		getContentPane().add(departureTime, "cell 6 20");

		blocks = new JLabel(String.valueOf(delivery.estimatedDistanceTraveled));
		getContentPane().add(blocks, "cell 3 21");

		TimePickerSettings actualTimeSettings = new TimePickerSettings();
		actualTimeSettings.setAllowEmptyTimes(true);

		pickedUpTime = new TimePicker(actualTimeSettings);
		pickedUpTime.setTime(delivery.actualPickupTime != null ? delivery.actualPickupTime.toLocalTime() : null);
		getContentPane().add(pickedUpTime, "cell 6 21,alignx left");

		price = new JLabel(String.valueOf(delivery.totalDeliveryCost));
		getContentPane().add(price, "cell 3 22");

		deliveredTime = new TimePicker(actualTimeSettings);
		deliveredTime.setTime(delivery.actualDeliveryTime != null ? delivery.actualDeliveryTime.toLocalTime() : null);
		getContentPane().add(deliveredTime, "cell 6 22, alignx left");

		bonusEarned = new JCheckBox("", delivery.bonusEarned);
		bonusEarned.setEnabled(false);
		getContentPane().add(bonusEarned, "cell 6 23");

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validInputs()) {
					save();
					close();
				}
			}
		});
		getContentPane().add(btnOk, "cell 0 25,growx");

		JButton btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validInputs())
					save();
			}
		});
		getContentPane().add(btnApply, "cell 1 25");

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		getContentPane().add(btnCancel, "cell 2 25");

		doBaseLayout();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public boolean validInputs() {
		boolean valid = pickupClient.getSelectedItem() != null && deliveryClient.getSelectedItem() != null;
		if (!valid)
			JOptionPane.showMessageDialog(this,
					"Pickup and Delivery clients must be specified before delivery route can be calculated.");
		return valid;
	}

	protected void save() {
		try {
			populateDeliveryInfo();
			CourierSystem.SaveDelivery(delivery);
			saved = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			deliveryTable.refresh();
		}
	}

	protected void close() {
		dispose();
	}

	private void populateDeliveryInfo() {
		delivery.pickupClient = (Client) pickupClient.getSelectedItem();
		delivery.deliveryClient = (Client) deliveryClient.getSelectedItem();
		delivery.requestedPickupTime = pickupEditor.getDateTimePermissive();
		delivery.billToSender = billToPickup.isSelected();
		delivery.calculateDeliveryStatistics();

		departureTime.setText(delivery.calculatedDepartureTime.format(timeFormat));
		estimatedDeliveryTime.setText(delivery.estimatedDeliveryTime.format(timeFormat));
		blocks.setText(Double.toString(delivery.estimatedDistanceTraveled));
		price.setText(Double.toString(delivery.totalDeliveryCost));

		delivery.calculateBonus();
		bonusEarned.setSelected(delivery.bonusEarned);

		if (delivery.assignedCourier != null && delivery.actualPickupTime != null
				&& delivery.actualDeliveryTime != null)
			delivery.status = DeliveryStatus.Completed;
	}

	@Override
	public void dispose() {
		if (!saved) {
			deliveryTable.removeRow(delivery);
		}
		super.dispose();
	}

	private void doBaseLayout() {
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(DeliveryTicketEditor.class.getResource("/view/courier logo.png")));
		getContentPane().add(logo, "cell 0 0 8 1,alignx center");

		JLabel lblAcmeCourierService_1 = new JLabel("ACME Courier Service");
		lblAcmeCourierService_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblAcmeCourierService_1, "flowy,cell 0 1 8 1,alignx center,aligny center");

		JLabel lblDeliveryTicket = new JLabel("Delivery Ticket");
		lblDeliveryTicket.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblDeliveryTicket, "cell 0 2 8 1,alignx center,aligny center");

		JLabel lblDate = new JLabel("Date:" + LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		getContentPane().add(lblDate, "flowx,cell 0 4 8 1,alignx center");

		JLabel lblOrderTakenBy = new JLabel("Order taken by:" + CourierSystem.currentUser.name);
		getContentPane().add(lblOrderTakenBy, "flowx,cell 0 5 8 1,alignx center");

		JLabel lblPickUpInfo = new JLabel("Pick Up Info");
		lblPickUpInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblPickUpInfo, "cell 2 7 2 1,alignx left");

		JLabel lblClientName = new JLabel("Client Name:");
		getContentPane().add(lblClientName, "cell 2 8 2 1,alignx left");

		JLabel lblClientId = new JLabel("Client Id:");
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

		JLabel lblBillPickupClient = new JLabel("Bill Pickup Client?");
		getContentPane().add(lblBillPickupClient, "cell 2 11 2 1,alignx left");

		JLabel lblDeliveryInfo = new JLabel("Delivery Info");
		lblDeliveryInfo.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(lblDeliveryInfo, "cell 2 13 2 1,alignx left");

		JLabel lblClientName_1 = new JLabel("Client Name:");
		getContentPane().add(lblClientName_1, "cell 2 14 2 1,alignx left");

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

		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(billToPickup);
		radioGroup.add(billToDeliver);

		JLabel lblEstimatedDeliveryTime = new JLabel("Estimated Delivery Time:");
		getContentPane().add(lblEstimatedDeliveryTime, "cell 1 20 2 1,alignx left");

		JLabel lblAssignedTime = new JLabel("Assigned Time:");
		getContentPane().add(lblAssignedTime, "cell 5 20,alignx left");

		JLabel lblEstimatedBlocks = new JLabel("Estimated Blocks:");
		getContentPane().add(lblEstimatedBlocks, "cell 1 21 2 1,alignx left");

		JLabel lblPickedUpTime = new JLabel("Picked Up Time:");
		getContentPane().add(lblPickedUpTime, "cell 5 21,alignx left");

		JLabel lblQuotedPrice = new JLabel("Quoted Price:");
		getContentPane().add(lblQuotedPrice, "cell 1 22 2 1,alignx left");

		JLabel lblDeliveredTime = new JLabel("Delivered Time:");
		getContentPane().add(lblDeliveredTime, "cell 5 22,alignx left");

		JLabel lblBonus = new JLabel("Bonus?");
		getContentPane().add(lblBonus, "cell 5 23,alignx left");

		JLabel lblTime = new JLabel("Time:" + LocalTime.now().format(DateTimeFormatter.ofPattern("h:mm")));
		getContentPane().add(lblTime, "flowx,cell 0 4 8 1,alignx center");

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
	}
}