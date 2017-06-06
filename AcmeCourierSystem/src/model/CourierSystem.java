package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public final class CourierSystem {
	
	public ArrayList<Employee> Employees;
	public ArrayList<Client> Clients;
	public ArrayList<Delivery> Deliveries;
	public Map CityMap;
	public Settings SystemSettings;
	
	public CourierSystem() throws FileNotFoundException, IOException {
		LoadEmployees();
		LoadCouriers();
		LoadClients();
		LoadDeliveries();
	}
	
	public void LoadDeliveries() {
		// TODO Auto-generated method stub
		
	}

	public void LoadClients() {
		// TODO Auto-generated method stub
		
	}

	public void LoadCouriers() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public void LoadEmployees() throws FileNotFoundException, IOException {
		try {
			Employees = (ArrayList<Employee>)Serialize.LoadObject(Settings.EmployeeFile);
		}
		catch (FileNotFoundException fnfe) {
			Employees = new ArrayList<Employee>();
			SaveEmployees();
		}
	}
	
	public void SaveEmployees() throws FileNotFoundException, IOException {
		
		Serialize.SaveObject(Settings.EmployeeFile, Employees);
	}
}
