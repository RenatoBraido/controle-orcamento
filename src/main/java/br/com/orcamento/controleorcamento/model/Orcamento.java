package br.com.orcamento.controleorcamento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORCAMENTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORCAMENTO", unique = true, nullable = false)
    private Long id;

    @Column(name = "DATA_EMISSAO", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "DATA_VALIDADE", nullable = false)
    private LocalDate dataValidade;

    @Column(name = "DATA_ENTREGA", nullable = false)
    private LocalDate dataEntrega;

    @Column(name = "ID_FORNECEDOR")
    private Long idFornecedor;

    @Column(name = "ID_UNMEDI")
    private Long idUnmedi;

    @Column(name = "GARANTIA")
    private String garantia;

    @Column(name = "CONDICOES_PAGAMENTO")
    private String condicoesPagamento;

    @Column(name = "PRECO_COMPRA")
    private double precoCompra;

    @Column(name = "QUANTIDADE")
    private int quantidade;

    @Column(name = "ID_GRUPOAPROVADOR")
    private int idGrupoAprovador;

    @Column(name = "ID_USERAPROVE")
    private int idUserAprove;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DATA_GERACAO")
    private LocalDate dataGeracao;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

}