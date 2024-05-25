package br.edu.univesp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("ProjetoIntegradorPU");
	}

	// Padrão singleton
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
