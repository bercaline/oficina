package br.com.bercalini.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bercalini.anotacoes.Transacao;
import br.com.bercalini.dao.ClienteDao;
import br.com.bercalini.modelo.Cliente;
import br.com.bercalini.util.MensagemUtil;
import br.com.bercalini.util.RedirecionadorPaginas;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class ClienteBean implements Serializable {

	@Inject
	private Cliente cliente;
	@Inject
	private ClienteDao clienteDao;
	@Inject
	private MensagemUtil mensagem;
	@Inject
	private RedirecionadorPaginas pagina;
	
	
	private List<Cliente> clientes;

	@Transacao
	public void salvar() {
		if (cliente.getCodigo() == null) {
			cliente.setEndereco(cliente.getEndereco());
			clienteDao.salvar(cliente);
			System.out.println("Código do cliente: " + cliente.getCodigo());
			System.out.println("Código do endereço do cliente: " + cliente.getEndereco().getCodigo());
			this.clientes = clienteDao.clientes();
			mensagem.mensagem("Cliente adicionado com sucesso");
		} else {
			clienteDao.alterar(cliente);
			mensagem.mensagem("Cliente alterado com sucesso");
		}
		this.cliente = new Cliente();
	}
	
	@Transacao
	public List<Cliente> getListaClientes() {
		return clienteDao.clientes();
	}
	
	@Transacao
	public void excluir(Cliente cliente) {
		clienteDao.excluir(cliente);
		this.clientes = clienteDao.clientes();
		mensagem.mensagem("Cliente excluido com sucesso");
	}
	
	
	public String editar(Cliente cliente) {
		this.cliente = cliente;
		return pagina.direcionadorPaginas("cliente.xhtml");
	}
	
	public String formularioNovo() {
		this.cliente = new Cliente();
		return pagina.direcionadorPaginas("cliente.xhtml");
	}
	
	public String formularioTabela() {
		return pagina.direcionadorPaginas("clientePesquisa.xhtml");
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		if(clientes == null) {
			this.clientes = clienteDao.clientes();
		}
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	
}
