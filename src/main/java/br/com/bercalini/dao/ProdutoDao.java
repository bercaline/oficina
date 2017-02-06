package br.com.bercalini.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.bercalini.modelo.Fornecedor;
import br.com.bercalini.modelo.Produto;

@SuppressWarnings("serial")
public class ProdutoDao implements Serializable{
	
	@Inject
	EntityManager manager;

	public void salvar(Produto produto) {
		manager.persist(produto);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> fornecedores() {
		return manager.createNamedQuery("Produto.Fornecedores").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> produtos() {
		return manager.createNamedQuery("Produto.lista").getResultList();
	}

	public void excluir(Produto produto) {
		manager.remove(produto);
	}

	public void editar(Produto produto) {
		manager.merge(produto);
	}
	
	public Produto buscarPorId(Long codigo) {
		Produto produto = manager.find(Produto.class, codigo);
		return produto;
	}
}
