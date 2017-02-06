package br.com.bercalini.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bercalini.anotacoes.Transacao;
import br.com.bercalini.dao.FuncionarioDao;
import br.com.bercalini.modelo.Funcionario;
import br.com.bercalini.util.MensagemUtil;
import br.com.bercalini.util.RedirecionadorPaginas;

@Named
@SessionScoped
@SuppressWarnings("serial")
public class FuncionarioBean implements Serializable{
	
	@Inject
	private Funcionario funcionario;
	@Inject
	private FuncionarioDao funcionarioDao;
	@Inject
	private RedirecionadorPaginas paginas;
	@Inject
	private MensagemUtil mensagem;
	
	@Transacao
	public void salvar() {
		if(funcionario.getCodigo() == null) {
			funcionarioDao.salvar(funcionario);
			mensagem.mensagem("Funcionario adicionado com sucesso");
		} else {
			funcionarioDao.alterar(funcionario);
			mensagem.mensagem("Funcionario alterado com sucesso");
		}
		this.funcionario = new Funcionario();
	}
	
	@Transacao
	public List<Funcionario> getListaFuncionarios() {
		return funcionarioDao.funcionarios();
	}
	
	@Transacao
	public void excluir(Funcionario funcionario) {
		funcionarioDao.excluir(funcionario);
		mensagem.mensagem("Funcionario excluido com sucesso");
	}
	
	public String formularioNovo() {
		this.funcionario = new Funcionario();
		return paginas.direcionadorPaginas("funcionario.xhtml");
	}
	
	public String carregar(Funcionario funcionario) {
		this.funcionario = funcionario;
		return paginas.direcionadorPaginas("funcionario.xhtml");
	}

	public String formularioTabela() {
		return paginas.direcionadorPaginas("funcionarioPesquisa.xhtml");
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
