package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.Persistence;

public final class CourierSystem {
	
	public ArrayList<Employee> Employees;
	public ArrayList<Courier> Couriers;
	public ArrayList<Client> Clients;
	public ArrayList<Delivery> Deliveries;
	public Map CityMap;
	public Settings SystemSettings;
	public Employee currentUser;
	
	public CourierSystem() throws FileNotFoundException, IOException {
		LoadEmployees();
		LoadCouriers();
		LoadClients();
		LoadDeliveries();
		
		//test();
	}
	
	private void test() {
		
		javax.persistence.EntityManagerFactory factory = Persistence.createEntityManagerFactory("example");
		javax.persistence.EntityManager em = factory.createEntityManager();
		Employee e = new Employee("Zak", EmployeeRole.Administrator, "zkast", "password");
		em.persist(e);
		
	}

	public void LoadDeliveries() throws IOException {
		if(Deliveries == null) {
			Deliveries = new ArrayList<Delivery>();
		}
	}
	
	public void LoadClients() throws IOException {
	}

	public void LoadCouriers() throws IOException {
	}

	public void LoadEmployees() throws FileNotFoundException, IOException {
	}
	
	public void SaveDeliveries() throws FileNotFoundException, IOException {
	}
	
	public void SaveClients() throws FileNotFoundException, IOException {
	}
	
	public void SaveCouriers() throws FileNotFoundException, IOException {
	}
	
	public void SaveEmployees() throws FileNotFoundException, IOException {
	}
}

