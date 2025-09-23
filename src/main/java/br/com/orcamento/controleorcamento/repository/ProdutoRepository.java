package br.com.orcamento.controleorcamento.repository;

import br.com.orcamento.controleorcamento.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}