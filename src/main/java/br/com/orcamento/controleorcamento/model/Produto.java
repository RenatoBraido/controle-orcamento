package br.com.orcamento.controleorcamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "PRODUTO")
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPRODUTO", unique = true, nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, unique = true)
    private String nome;

    @Column(name = "DESCRICAO")
    private String observacoes;

    @Column(name = "ID_ALMOX", nullable = false)
    private Long idAlmoxarifado;

    @Column(name = "TEMPIDEAL")
    private Double temperaturaProduto;

    @Column(name = "CODBARRAS")
    private String codigoBarras;

    @Column(name = "STQMAX", nullable = false)
    private int estoqueMaximo;

    @Column(name = "STQMIN", nullable = false)
    private int estoqueMinimo;

    @Column(name = "PNTPEDIDO", nullable = false)
    private int pontoAbastecimento;

    @Column(name = "ID_UNMEDI")
    private Long idUnidadeMedida;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "FORNECPROD",
            joinColumns = @JoinColumn(name = "ID_PRODUTO"),
            inverseJoinColumns = @JoinColumn(name = "ID_FORNECEDOR")
    )
    private List<Fornecedor> fornecedores;

}