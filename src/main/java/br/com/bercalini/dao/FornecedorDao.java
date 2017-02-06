package br.com.bercalini.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.bercalini.modelo.Fornecedor;

@SuppressWarnings("serial")
public class FornecedorDao implements Serializable{
	
	@Inject
	EntityManager manager;
	
	public void salvar(Fornecedor fornecedor) {
		manager.persist(fornecedor);
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> fornecedores() {
		return manager.createNamedQuery("Fornecedor.lista").getResultList();
	}

	public void excluir(Fornecedor fornecedor) {
		manager.remove(fornecedor);
	}

	public void alterar(Fornecedor fornecedor) {
		manager.merge(fornecedor);		
	}
	
	public Fornecedor buscaPorCodigo(Long codigo) {
		Fornecedor fornecedor = manager.find(Fornecedor.class, codigo);
		return fornecedor;
	}
	
	
}
