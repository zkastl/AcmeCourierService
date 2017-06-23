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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

public final class CourierSystem {

	public static HashMap<String, Employee> Employees;
	public static HashMap<String, Client> Clients;
	public static HashMap<String, Delivery> Deliveries;
	public static Map CityMap;
	public static Settings SystemSettings;
	public static Employee currentUser;

	private static EntityManagerFactory factory;
	private static EntityManager em;

	public static void InitializeCourierSystem() throws Exception {

		// Load the database.
		factory = Persistence.createEntityManagerFactory("entities");
		em = factory.createEntityManager();

		LoadCityMap();
		LoadEmployees();
		LoadClients();
		LoadDeliveries();		
		LoadSettings();		
	}

	private static void LoadSettings() {
		try {
			FileInputStream fin = new FileInputStream("./settings.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			SystemSettings = (Settings) ois.readObject();
			ois.close();
			fin.close();
			
			if (SystemSettings == null){
				System.out.println("Settings failed to load.");
				SystemSettings = new Settings();
				SystemSettings.setDefaultValues();
			}
		}
		catch(IOException ioex) {
			SystemSettings = new Settings();
			SystemSettings.setDefaultValues();
			ioex.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			SystemSettings = new Settings();
			SystemSettings.setDefaultValues();
			e.printStackTrace();
		}
		
	}
	
	public static void SaveSettings() throws IOException {
		FileOutputStream fout = new FileOutputStream("./settings.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(SystemSettings);
		oos.close();
		fout.close();
		System.out.println("Settings saved successfully.");
	}

	@SuppressWarnings("unchecked")
	public static void LoadEmployees() throws Exception {
		// Load the employee table
		// If tables is empty, create default employee.
		if (Employees == null) {
			Employees = new HashMap<String, Employee>();
		}
		try {
			Query eQuery = em.createQuery("SELECT e FROM Employees e", Employee.class);
			List<Employee> emp = eQuery.getResultList();
			for (Employee e : emp) {
				Employees.put(e.name, e);
			}
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}

		if (Employees.size() == 0) {
			Employees.put("Admin", new Employee("admin"));
		}
	}

	public static void UpdateEmployees() throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		for (Employee e : Employees.values()) {
			em.persist(e);
		}
		trans.commit();
		LoadEmployees();
	}

	public static void SaveEmployee(Employee e) throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.persist(e);
		trans.commit();
		LoadEmployees();
	}

	public static void RemoveEmployee(Employee e) throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.remove(e);
		Employees.remove(e.name);
		trans.commit();
	}

	@SuppressWarnings("unchecked")
	public static void LoadClients() throws Exception {
		Clients = new HashMap<String, Client>();
		try {
			Query eQuery = em.createQuery("SELECT c FROM Clients c", Client.class);
			List<Client> cli = eQuery.getResultList();
			for (Client c : cli) {
				c.trueAddress = CityMap.getIntersection(c.address);
				Clients.put(c.name, c);
			}
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}
	}

	public static void UpdateClients() throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		for (Client c : Clients.values()) {
			c.address = c.trueAddress.getName();
			em.persist(c);
		}
		trans.commit();
		LoadClients();
	}

	public static void SaveClient(Client c) throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.merge(c);
		trans.commit();
		LoadClients();
	}

	public static void RemoveClient(Client c) throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.remove(c);
		Clients.remove(c.name);
		trans.commit();
	}

	public static void LoadDeliveries() throws IOException {
		Deliveries = new HashMap<String, Delivery>();
		try {
			Query eQuery = em.createQuery("SELECT d from Deliveries d", Delivery.class);
			@SuppressWarnings("unchecked")
			List<Delivery> del = eQuery.getResultList();
			for (Delivery d : del) {
				Deliveries.put(String.valueOf(d.packageID), d);
			}
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}
	}

	public static void UpdateDeliveries() throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		for (Delivery d : Deliveries.values()) {
			em.persist(d);
		}
		trans.commit();
		LoadDeliveries();
	}

	public static void SaveDelivery(Delivery d) throws FileNotFoundException, IOException {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.merge(d);
		trans.commit();
		LoadDeliveries();
	}

	public static void RemoveDelivery(Delivery d) throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		em.remove(d);
		Deliveries.remove(String.valueOf(d.packageID));
		trans.commit();
	}

	@SuppressWarnings("unchecked")
	public static void LoadCityMap() throws Exception {
		try {
			Query eQuery = em.createQuery("SELECT m FROM CityMap m", Intersection.class);
			List<Intersection> savedIntersections = eQuery.getResultList();
			HashMap<String, Intersection> hashedIntersections = new HashMap<String, Intersection>();
			for (Intersection i : savedIntersections) {
				hashedIntersections.put(i.getName(), i);
			}

			CityMap = new Map();
			if (hashedIntersections != null && !hashedIntersections.isEmpty()) {
				CityMap.setIntersections(hashedIntersections);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void SaveCityMap() throws Exception {
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		for (Intersection i : CityMap.intersections.values()) {
			em.persist(i);
		}
		trans.commit();
	}

	public static void PrintMapToConsole() {
		System.out.println("City Map: ID-" + CityMap.mapId);
		System.out.println("Last Saved: " + CityMap.lastSavedDate);
		System.out.println("Closed Intersections:");
		for (String s : CityMap.getClosedIntersections()) {
			System.out.println("  " + s);
		}
	}

	private CourierSystem() throws Exception {
		InitializeCourierSystem();
	};
}
