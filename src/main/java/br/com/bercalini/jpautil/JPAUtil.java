package br.com.bercalini.jpautil;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SuppressWarnings("serial")
public class JPAUtil implements Serializable{
	
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("oficina");
	
	@Produces
	@RequestScoped
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public void close(@Disposes EntityManager manager) {
		manager.close();
	}
}
