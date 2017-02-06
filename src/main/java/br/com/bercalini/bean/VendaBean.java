package br.com.bercalini.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bercalini.anotacoes.Transacao;
import br.com.bercalini.dao.FornecedorDao;
import br.com.bercalini.dao.FuncionarioDao;
import br.com.bercalini.dao.ItemDao;
import br.com.bercalini.dao.ProdutoDao;
import br.com.bercalini.dao.VendaDao;
import br.com.bercalini.modelo.Funcionario;
import br.com.bercalini.modelo.Item;
import br.com.bercalini.modelo.Produto;
import br.com.bercalini.modelo.Venda;
import br.com.bercalini.util.MensagemUtil;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class VendaBean implements Serializable {

	@Inject
	private VendaDao vendaDao;
	@Inject
	private Venda venda;
	@Inject
	private MensagemUtil mensagem;
	@Inject
	private FuncionarioDao funcionarioDao;
	@Inject
	private Item item;
	@Inject
	private ItemDao itemDao;
	@Inject
	private ProdutoDao produtoDao;
	@Inject
	private Funcionario funcionario;
	@Inject
	private Produto produto;
	
	private List<Item> itens = new ArrayList<Item>();

	@Transacao
	public void salvar() {
		if (venda.getCodigo() == null) {
			itemDao.salvar(item);
			mensagem.mensagem("Venda efetuada com sucesso");
		} else {

		}
		this.venda = new Venda();
		this.itens = new ArrayList<Item>();
	}
	
	@Transacao
	public List<Funcionario> getListaFuncionarios() {
		return funcionarioDao.funcionarios();
	}

	public void adicionarProduto(Produto produto) {
		int posicaoEncontrada = -1;
		for (int posicao = 0; posicao < itens.size() && posicaoEncontrada < 0; posicao++) {
			Item temporario = itens.get(posicao);
			if (temporario.getProduto().getCodigo().equals(produto.getCodigo())) {
				posicaoEncontrada = posicao;
			}
		}
		Item item = new Item();
		item.setProduto(produto);
		if (posicaoEncontrada < 0) {
			item.setQuantidade(1);
			item.setValorParcial(produto.getPreco());
			itens.add(item);
		} else {
			Item itemTemporario = itens.get(posicaoEncontrada);
			item.setQuantidade(itemTemporario.getQuantidade() + 1);
			item.setValorParcial(produto.getPreco().multiply(
					new BigDecimal(item.getQuantidade())));
			itens.set(posicaoEncontrada, item);
		}
		venda.setValorTotal(venda.getValorTotal().add(produto.getPreco()));
	}

	public void remover(Item item) {
		int posicaoEncontrada = -1;
		for (int posicao = 0; posicao < itens.size() && posicaoEncontrada < 0; posicao++) {
			Item temporario = itens.get(posicao);
			if (temporario.getProduto().getCodigo()
					.equals(item.getProduto().getCodigo())) {
				posicaoEncontrada = posicao;
			}
		}
		if (posicaoEncontrada > -1) {
			itens.remove(posicaoEncontrada);
			venda.setValorTotal(venda.getValorTotal().subtract(
					item.getValorParcial()));
		}
	}

	public void carregarDadosVenda() {
		venda.setHorario(venda.getHorario());
		Funcionario fun = funcionarioDao.buscarPorId(1L);
		venda.setFuncionario(fun);
		venda.setValorTotal(venda.getValorTotal());
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public List<Produto> getListaProdutos() {
		return produtoDao.produtos();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	
}