package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public final class CourierSystem {
	
	public ArrayList<Employee> Employees;
	public ArrayList<Client> Clients;
	public ArrayList<Delivery> Deliveries;
	public Settings SystemSettings;
	
	@SuppressWarnings("unused")
	private static String settingsFile = "settings.xml";
	
	public CourierSystem() throws FileNotFoundException, IOException {
		LoadEmployees();
		
	}
	
	@SuppressWarnings("unchecked")
	private void LoadEmployees() throws FileNotFoundException, IOException {
		try {
			Employees = (ArrayList<Employee>)Serialize.LoadObject(Settings.EmployeeFile);
		}
		catch (FileNotFoundException fnfe) {
			Employees = new ArrayList<Employee>();
			SaveEmployees();
		}
	}
	
	private void SaveEmployees() throws FileNotFoundException, IOException {
		
		Serialize.SaveObject(Settings.EmployeeFile, Employees);
	}
}
