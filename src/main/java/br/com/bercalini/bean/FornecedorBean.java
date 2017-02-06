package br.com.bercalini.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bercalini.anotacoes.Transacao;
import br.com.bercalini.dao.FornecedorDao;
import br.com.bercalini.modelo.Fornecedor;
import br.com.bercalini.util.MensagemUtil;
import br.com.bercalini.util.RedirecionadorPaginas;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class FornecedorBean implements Serializable{
	
	@Inject
	private Fornecedor fornecedor;
	@Inject 
	private FornecedorDao fornecedorDao;
	@Inject
	private MensagemUtil mensagem;
	@Inject
	private RedirecionadorPaginas paginas;
	
	@Transacao
	public void salvar() {
		if(fornecedor.getCodigo() == null) {
			fornecedorDao.salvar(fornecedor);
			System.out.println("Código do fornecedor: " + fornecedor.getCodigo());
			mensagem.mensagem("Fornecedor salvo com sucesso");
		} else {
			fornecedorDao.alterar(fornecedor);
			mensagem.mensagem("Fornecedor atualizado com sucesso");
		}
		this.fornecedor = new Fornecedor();
	}
	
	@Transacao
	public void excluir(Fornecedor fornecedor) {
		fornecedorDao.excluir(fornecedor);
		mensagem.mensagem("Fornecedor excluido com sucesso");
	}
	
	@Transacao
	public List<Fornecedor> getListaFornecedores() {
		return fornecedorDao.fornecedores();
	}
	
	public String carregar(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
		return paginas.direcionadorPaginas("fornecedor.xhtml");
	}
	
	public String formularioNovo() {
		return paginas.direcionadorPaginas("fornecedor.xhtml");
	}
	
	public String formularioTabela() {
		return paginas.direcionadorPaginas("fornecedorPesquisa.xhtml");
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
	
}
