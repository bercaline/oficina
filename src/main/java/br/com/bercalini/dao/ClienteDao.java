package br.com.bercalini.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.bercalini.modelo.Cliente;

@SuppressWarnings("serial")
public class ClienteDao implements Serializable{
	
	@Inject
	EntityManager manager;
	
	public void salvar(Cliente cliente) {
		manager.persist(cliente);
	}
	
	public Cliente buscarPorId(Long codigo) {
		Cliente cliente = manager.find(Cliente.class, codigo);
		return cliente;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> clientes() {
		List<Cliente> lista = manager.createNamedQuery("Cliente.lista").getResultList();
		return lista;
	}

	public void excluir(Cliente cliente) {
		manager.remove(cliente);
	}

	public void alterar(Cliente cliente) {
		manager.merge(cliente);
	}
}
