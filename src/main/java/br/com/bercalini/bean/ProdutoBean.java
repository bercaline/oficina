package br.com.bercalini.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bercalini.anotacoes.Transacao;
import br.com.bercalini.dao.FornecedorDao;
import br.com.bercalini.dao.ProdutoDao;
import br.com.bercalini.modelo.Fornecedor;
import br.com.bercalini.modelo.Produto;
import br.com.bercalini.util.MensagemUtil;
import br.com.bercalini.util.RedirecionadorPaginas;

@Named
@SessionScoped
@SuppressWarnings("serial")
public class ProdutoBean implements Serializable {

	@Inject
	private Produto produto;
	@Inject
	private ProdutoDao produtoDao;
	@Inject
	private MensagemUtil mensagem;
	@Inject
	private FornecedorDao FornecedorDao;
	@Inject
	private RedirecionadorPaginas paginas;
	
	@Transacao
	public void salvar() {
		if (produto.getCodigo() == null) {
			Fornecedor buscaPorCodigo = FornecedorDao.buscaPorCodigo(produto.getFornecedor().getCodigo());
			produto.setFornecedor(buscaPorCodigo);
			produtoDao.salvar(produto);
			System.out.println("Código do produto: " + produto.getCodigo());
			System.out.println("Código do fornecedor que realizou a venda:  " + buscaPorCodigo);
			mensagem.mensagem("Produto salvo com sucesso");
		} else {
			produtoDao.editar(produto);
			mensagem.mensagem("Produto editado com sucesso");
		}
		this.produto = new Produto();
	}
	
	@Transacao
	public void excluir(Produto produto) {
		produtoDao.excluir(produto);
		mensagem.mensagem("Produto excluido com sucesso");
	}
	
	@Transacao
	public List<Fornecedor> getFornecedores() {
		return produtoDao.fornecedores();
	}
	
	@Transacao
	public List<Produto> getListaProdutos() {
		return produtoDao.produtos();
	}
	
	public String carregar(Produto produto) {
		this.produto = produto;
		return paginas.direcionadorPaginas("produto.xhtml");
	}
	
	public String formularioNovo() {
		return paginas.direcionadorPaginas("produto.xhtml");
	}
	
	public String formularioTabela() {
		return paginas.direcionadorPaginas("produtoPesquisa.xhtml");
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
}
