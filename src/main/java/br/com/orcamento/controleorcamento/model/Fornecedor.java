package br.com.orcamento.controleorcamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "FORNECEDOR")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDFORNECEDOR", unique = true, nullable = false)
    private Long id;

    @Column(name = "REPRESENT")
    private String nome;

    @Column(name = "CONTREPRE")
    private String contato;

    @Column(name = "ID_PESSOA", nullable = false, unique = true)
    private Long idPessoa;

    @Column(name = "DECRICAO")
    private String decricao;

    @JsonIgnore
    @ManyToMany(mappedBy = "fornecedores")
    private List<Produto> produtos;
}