package main;

/**
 * 
 * @author Team 3
 * @version 1.0
 * 
 * This class represents all of the persistent objects contained by the system upon startup.  
 * 
 * At startup time, this class should:
 * 
 * 	- Query the database for the appropriate tables.
 * 		-- If those tables do not exist, or if the database itself doesn't exist, 
 * 			create a new database and add a single entry for one Administrator.
 * 
 * 	- Load the tables into the ArrayLists contained by this class
 * 	- Make this class available to the entire system.
 */
import model.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public final class CourierSystem {
	
	public List<Employee> Employees;
	public List<Courier> Couriers;
	public List<Client> Clients;
	public List<Delivery> Deliveries;
	public Map CityMap;
	public Settings SystemSettings;
	public Employee currentUser;
	
	private static EntityManagerFactory factory;
	private static EntityManager em;
	
	public void InitializeCourierSystem() throws Exception {
		
		// Load the database.
		factory = Persistence.createEntityManagerFactory("entities");
		em = factory.createEntityManager();
		
		LoadEmployees();
		LoadCouriers();
		LoadClients();
		LoadDeliveries();
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

	@SuppressWarnings("unchecked")
	public void LoadEmployees() throws Exception {
		
		// Load the employee table
		// If tables is empty, create default employee.
		try {
			Query eQuery = em.createQuery("SELECT e FROM Employee e", Employee.class);
			Employees = eQuery.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getStackTrace());
		}
		if (Employees == null) {
			Employees = new ArrayList<Employee>();
		}
		if (Employees.size() == 0) {
			Employees.add(new Employee());
		}
		
		// Save employees to database before application operations.
		SaveEmployees();
	}
	
	public void SaveDeliveries() throws FileNotFoundException, IOException {
	}
	
	public void SaveClients() throws FileNotFoundException, IOException {
	}
	
	public void SaveCouriers() throws FileNotFoundException, IOException {
	}
	
	public void SaveEmployees() throws Exception {
		EntityTransaction trans = em.getTransaction();
		
		trans.begin();
		for(Employee e : Employees) {
			em.persist(e);
		}
		trans.commit();
	}
	
	public CourierSystem() throws Exception {
		InitializeCourierSystem();
		Test();
	};
	
	private void Test() {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.createQuery("drop table Employee");
		em.persist(new Employee("TEST", EmployeeRole.Administrator, "TEST", "password"));
		trans.commit();
	}
}

