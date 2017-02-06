package br.com.bercalini.dao;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.bercalini.modelo.Venda;

@SuppressWarnings("serial")
public class VendaDao implements Serializable{
	
	@Inject
	EntityManager manager;
	
	public void salvar(Venda venda) {
		manager.persist(venda);
	}
	
	public Venda buscarPorCodigo(Long codigo) {
		return manager.find(Venda.class, codigo);
	}
	
}
