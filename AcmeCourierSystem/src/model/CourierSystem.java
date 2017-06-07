package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public final class CourierSystem {
	
	public ArrayList<Employee> Employees;
	public ArrayList<Courier> Couriers;
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
	
	@SuppressWarnings("unchecked")
	public void LoadDeliveries() throws IOException {
		try {
			Deliveries = (ArrayList<Delivery>)Serialize.LoadObject(Settings.DeliveriesFile);
		}
		catch (FileNotFoundException fnfe) {
			Deliveries = new ArrayList<Delivery>();
			SaveDeliveries();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void LoadClients() throws IOException {
		try {
			Clients = (ArrayList<Client>)Serialize.LoadObject(Settings.ClientFile);
		}
		catch (FileNotFoundException fnfe) {
			Clients = new ArrayList<Client>();
			SaveClients();
		}
	}

	@SuppressWarnings("unchecked")
	public void LoadCouriers() throws IOException {
		try {
			Couriers = (ArrayList<Courier>)Serialize.LoadObject(Settings.CourierFile);
		}
		catch (FileNotFoundException fnfe) {
			Couriers = new ArrayList<Courier>();
			SaveCouriers();
		}
	}

	@SuppressWarnings("unchecked")
	public void LoadEmployees() throws FileNotFoundException, IOException {

		Employees = (ArrayList<Employee>)Serialize.LoadObject(Settings.EmployeeFile);
		if(Employees.size() == 0) {
			Employees = new ArrayList<Employee>();
			Employees.add(new Employee());
			SaveEmployees();
		}
	}
	
	public void SaveDeliveries() throws FileNotFoundException, IOException {
		
		Serialize.SaveObject(Settings.DeliveriesFile, Deliveries);
	}
	
	public void SaveClients() throws FileNotFoundException, IOException {
		
		Serialize.SaveObject(Settings.ClientFile, Clients);
	}
	
	public void SaveCouriers() throws FileNotFoundException, IOException {
		
		Serialize.SaveObject(Settings.CourierFile, Couriers);
	}
	
	public void SaveEmployees() throws FileNotFoundException, IOException {
		
		Serialize.SaveObject(Settings.EmployeeFile, Employees);
	}
}
