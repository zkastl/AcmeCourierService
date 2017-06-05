package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public final class CourierSystem {
	
	public ArrayList<Employee> Employees;
	public ArrayList<Client> Clients;
	public ArrayList<Delivery> Deliveries;
	public Settings SystemSettings;
	
	public CourierSystem(String settingsFile) throws FileNotFoundException, IOException {
		SaveEmployees("test2.xml");
		Employees = LoadEmployees("test2.xml");
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<Employee> LoadEmployees(String s) throws FileNotFoundException, IOException {
		
		return (ArrayList<Employee>) Serialize.LoadObject(s);
	}
	
	private void SaveEmployees(String s) throws FileNotFoundException, IOException {
		
		Serialize.SaveObject(s, Employees);
	}
}
