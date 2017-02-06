package br.com.bercalini.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.bercalini.modelo.Item;

@SuppressWarnings("serial")
public class ItemDao implements Serializable{
	
	@Inject
	private EntityManager manager;
	
	public void salvar(Item item) {
		manager.persist(item);
	}
}
