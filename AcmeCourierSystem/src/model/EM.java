package model;

import javax.persistence.*;


public class EM {
	
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	private static void initializeEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("model.Employee");
		entityManager = entityManagerFactory.createEntityManager();
	}
	
	public static EntityManager getEntityManager() {
		if(entityManager == null) {
			initializeEntityManager();
		}
		
		return entityManager;
	}
	
	public static void close() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	private EM() {}
	
}
