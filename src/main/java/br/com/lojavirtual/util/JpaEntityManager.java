package br.com.lojavirtual.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaEntityManager {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("app_loja_virtual");
	private EntityManager em = factory.createEntityManager();

	public EntityManager getEntityManager() {
		return em;
	}
}
