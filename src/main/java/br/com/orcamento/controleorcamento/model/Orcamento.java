package br.com.orcamento.controleorcamento.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valorOrcado;

    private BigDecimal descontoAplicado;

    private LocalDateTime dataDoOrcamento;

    private String observacoes;

    @ManyToOne
    private Produto produto;

    // Getters e Setters serão gerados aqui

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorOrcado() {
        return valorOrcado;
    }

    public void setValorOrcado(BigDecimal valorOrcado) {
        this.valorOrcado = valorOrcado;
    }

    public BigDecimal getDescontoAplicado() {
        return descontoAplicado;
    }

    public void setDescontoAplicado(BigDecimal descontoAplicado) {
        this.descontoAplicado = descontoAplicado;
    }

    public LocalDateTime getDataDoOrcamento() {
        return dataDoOrcamento;
    }

    public void setDataDoOrcamento(LocalDateTime dataDoOrcamento) {
        this.dataDoOrcamento = dataDoOrcamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}