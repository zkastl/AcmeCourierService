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
		LoadEmployees(Settings.EmployeeFile);
		
	}
	
	@SuppressWarnings("unchecked")
	private void LoadEmployees(String s) throws FileNotFoundException, IOException {
		try {
			Employees = (ArrayList<Employee>)Serialize.LoadObject(s);
		}
		catch (FileNotFoundException fnfe) {
			Employees = new ArrayList<Employee>();
			SaveEmployees(s);
		}
	}
	
	private void SaveEmployees(String s) throws FileNotFoundException, IOException {
		
		Serialize.SaveObject(s, Employees);
	}
}
