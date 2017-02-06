package br.com.bercalini.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@SuppressWarnings("serial")
public class Item implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private Integer quantidade;
    private BigDecimal valorParcial = BigDecimal.valueOf(0.0D);
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_codigo")
    private Produto produto = new Produto();
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "venda_codigo")
    private Venda venda = new Venda();

    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Venda getVenda() {
        return venda;
    }
    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    public BigDecimal getValorParcial() {
        return valorParcial;
    }
    public void setValorParcial(BigDecimal valorParcial) {
        this.valorParcial = valorParcial;
    }


}
