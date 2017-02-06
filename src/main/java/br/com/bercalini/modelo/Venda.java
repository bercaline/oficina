package br.com.bercalini.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SuppressWarnings("serial")
public class Venda implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar horario = Calendar.getInstance();
    private BigDecimal valorTotal = BigDecimal.valueOf(0.0D);
    @ManyToOne
    @JoinColumn(name = "funcionario_codigo")
    private Funcionario funcionario = new Funcionario();
    @OneToMany(mappedBy = "venda")
    private List<Item> itens = new ArrayList<Item>();

    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public Calendar getHorario() {
        return horario;
    }
    public void setHorario(Calendar horario) {
        this.horario = horario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public List<Item> getItens() {
        return itens;
    }
    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}