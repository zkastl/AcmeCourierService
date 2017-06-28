package view;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.github.lgooddatepicker.components.DatePicker;

import main.CourierSystem;
import model.Bill;
import model.Client;
import model.ClientReport;
import model.CourierReport;
import model.Employee;
import model.EmployeeRole;
import model.ReportType;
import net.miginfocom.swing.MigLayout;

public class ReportManagement extends Container {

	private JComboBox subject = new JComboBox();
	JComboBox<ReportType> report = new JComboBox<ReportType>(ReportType.values());
	private DatePicker startDate;
	private DatePicker endDate;

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

		startDate = new DatePicker();
		startDate.setDate(LocalDate.now().minusDays(6));
		add(startDate, "cell 5 3,alignx left");

		JLabel lblEndDate = new JLabel("End Date:");
		add(lblEndDate, "cell 4 5,alignx trailing");

		endDate = new DatePicker();
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
			try {
				String fileName = report.getSelectedItem().toString()
						+ Integer.toString(LocalDateTime.now().hashCode());
				File invoice = Files.createTempFile(fileName, ".csv").toFile();
				FileWriter invoiceWriter = new FileWriter(invoice);

				switch ((ReportType) report.getSelectedItem()) {
				case Bill:
					invoiceWriter.write(subject.getSelectedItem().getClass() == String.class
							? Bill.generateBill(startDate.getDate(), endDate.getDate())
							: Bill.generateBill((Client) subject.getSelectedItem(), startDate.getDate(),
									endDate.getDate()));
					break;
				case CompanyPerformance:
					invoiceWriter.write(subject.getSelectedItem().getClass() == String.class
					? ClientReport.generateClientReport(startDate.getDate(), endDate.getDate())
					: ClientReport.generateClientReport((Client) subject.getSelectedItem(), startDate.getDate(),
							endDate.getDate()));
					break;
				case CourierPerformance:
					invoiceWriter.write(subject.getSelectedItem().getClass() == String.class
					? CourierReport.generateCourierReport(startDate.getDate(), endDate.getDate())
					: CourierReport.generateCourierReport((Employee) subject.getSelectedItem(), startDate.getDate(),
							endDate.getDate()));
					break;
				default:
					break;

				}

				invoice.deleteOnExit();
				invoiceWriter.close();
				Desktop.getDesktop().open(invoice);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
