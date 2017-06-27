package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.github.lgooddatepicker.components.DatePicker;

import main.CourierSystem;
import model.Client;
import model.Employee;
import model.EmployeeRole;
import model.ReportType;
import net.miginfocom.swing.MigLayout;

public class ReportManagement extends Container {

	private JComboBox subject = new JComboBox();
	JComboBox<ReportType> report = new JComboBox<ReportType>(ReportType.values());

	public ReportManagement() {
		setLayout(new MigLayout("", "[grow][][][5%][][][grow]", "[5%][][15%][][5%][][5%][]"));

		JLabel lblDeliveryManagment = new JLabel("Delivery Managment");
		lblDeliveryManagment.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblDeliveryManagment, "cell 0 1 7 1,alignx center");

		JLabel lblReport = new JLabel("Report:");
		add(lblReport, "cell 1 3,alignx trailing");

		report.addActionListener(new reportChangeListener());
		report.setSelectedIndex(0);
		add(report, "cell 2 3,alignx left");

		JLabel lblStartDate = new JLabel("Start Date:");
		add(lblStartDate, "cell 4 3,alignx trailing");

		JLabel lblSubject = new JLabel("Subject:");
		add(lblSubject, "cell 1 5,alignx trailing");

		add(subject, "cell 2 5,alignx left");

		DatePicker startDate = new DatePicker();
		startDate.setDate(LocalDate.now().minusDays(6));
		add(startDate, "cell 5 3,alignx left");

		JLabel lblEndDate = new JLabel("End Date:");
		add(lblEndDate, "cell 4 5,alignx trailing");

		DatePicker endDate = new DatePicker();
		endDate.setDate(LocalDate.now());
		add(endDate, "cell 5 5,alignx left");

		JButton btnGenerate = new JButton("Generate Report");
		btnGenerate.addActionListener(new generateReportListener());
		add(btnGenerate, "cell 0 7 7 1,alignx center");
	}

	private class reportChangeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			subject.removeAllItems();

			switch ((ReportType) report.getSelectedItem()) {
			case Bill:
			case CompanyPerformance:
				subject.addItem("All Clients");
				for (Client client : CourierSystem.Clients.values()) {
					subject.addItem(client);
				}
				break;
			case CourierPerformance:
				subject.addItem("All Couriers");
				for (Employee employee : CourierSystem.Employees.values())
					if (employee.role == EmployeeRole.Courier) {
						subject.addItem(employee);
					}
				break;
			default:
				break;

			}
		}
	}

	private class generateReportListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch ((ReportType) report.getSelectedItem()) {
			case Bill:
				break;
			case CompanyPerformance:
				break;
			case CourierPerformance:
				break;
			default:
				break;

			}
		}
	}
}
