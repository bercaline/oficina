package br.com.bercalini.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@SuppressWarnings("serial")
@NamedQueries({
		@NamedQuery(name = "Produto.Fornecedores", query = "Select f from Fornecedor f"),
		@NamedQuery(name = "Produto.lista", query = "Select p from Produto p join fetch p.fornecedor") })
public class Produto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String nome;
	private BigDecimal preco = BigDecimal.valueOf(0.0D);
	private Integer quantidade;
	@ManyToOne
	@JoinColumn(name = "fornecedor_codigo")
	private Fornecedor fornecedor = new Fornecedor();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", preco="
				+ preco + ", quantidade=" + quantidade + ", fornecedor="
				+ fornecedor + "]";
	}
	
}
