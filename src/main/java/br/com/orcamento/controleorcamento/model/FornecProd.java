package br.com.orcamento.controleorcamento.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FORNECPROD")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FornecProd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDFORNECPROD", unique = true, nullable = false)
    private Long idFornecProd;

    @Column(name = "ID_FORNECEDOR", nullable = false)
    private Long idFornecedor;

    @Column(name = "ID_PRODUTO", nullable = false)
    private Long idProduto;

    @Column(name = "STATUS_VINC", nullable = false)
    private String statusVinc;
}