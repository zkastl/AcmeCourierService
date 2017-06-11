package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
import model.Client;
import model.Courier;
import model.Delivery;
import model.Employee;
import model.Map;
import model.Settings;

public final class CourierSystem implements Serializable {

	public static List<Employee> Employees;
	public static List<Courier> Couriers;
	public static List<Client> Clients;
	public static List<Delivery> Deliveries;
	public static Map CityMap;
	public static Settings SystemSettings;
	public static Employee currentUser;

	private static final long serialVersionUID = -3114878375152548283L;
	private static EntityManagerFactory factory;
	private static EntityManager em;

	public static void InitializeCourierSystem() throws Exception {

		// Load the database.
		factory = Persistence.createEntityManagerFactory("entities");
		em = factory.createEntityManager();

		LoadEmployees();
		// LoadCouriers();
		LoadClients();
		// LoadDeliveries();
	}

	@SuppressWarnings("unchecked")
	public static void LoadEmployees() throws Exception {

		// Load the employee table
		// If tables is empty, create default employee.
		try {
			Query eQuery = em.createQuery("SELECT e FROM Employees e", Employee.class);
			Employees = eQuery.getResultList();
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}
		if (Employees == null) {
			Employees = new ArrayList<Employee>();
		}
		if (Employees.size() == 0) {
			Employees.add(new Employee("Admin"));
		}
	}

	public static void UpdateEmployees() throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		for (Employee e : Employees) {
			em.persist(e);
		}
		trans.commit();
		LoadEmployees();
	}

	public static void SaveEmployee(Employee e) throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.merge(e);
		trans.commit();
		LoadEmployees();
	}

	public static void RemoveEmployee(Employee e) throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.remove(e);
		Employees.remove(e);
		trans.commit();
	}

	@SuppressWarnings("unchecked")
	public static void LoadClients() throws Exception {
		try {
			Query eQuery = em.createQuery("SELECT c FROM Clients c", Client.class);
			Employees = eQuery.getResultList();
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}
		if (Clients == null) {
			Clients = new ArrayList<Client>();
		}
	}

	public static void UpdateClients() throws Exception {
	}

	public static void SaveClient(Client c) throws Exception {
	}

	public static void RemoveClient(Client c) throws Exception {
	}

	public static void SaveDeliveries() throws FileNotFoundException, IOException {
	}

	public void LoadDeliveries() throws IOException {
		if (Deliveries == null) {
			Deliveries = new ArrayList<Delivery>();
		}
	}

	public void LoadCouriers() throws IOException {
	}

	public void SaveCouriers() throws FileNotFoundException, IOException {
	}

	private CourierSystem() throws Exception {
		InitializeCourierSystem();
	};
}
