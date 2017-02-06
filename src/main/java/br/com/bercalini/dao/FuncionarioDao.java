package br.com.bercalini.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.bercalini.modelo.Funcionario;

@SuppressWarnings("serial")
public class FuncionarioDao implements Serializable{
	
	@Inject
	EntityManager manager;

	public void salvar(Funcionario funcionario) {
		manager.persist(funcionario);
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> funcionarios() {
		return manager.createNamedQuery("Funcionario.lista").getResultList();
	}

	public void excluir(Funcionario funcionario) {
		manager.remove(funcionario);
	}
	
	public void alterar(Funcionario funcionario) {
		manager.merge(funcionario);
	}
	
	public Funcionario buscarPorId(Long codigo) {
		Funcionario funcionario = manager.find(Funcionario.class, codigo);
		return funcionario;
	}

}
