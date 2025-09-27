package br.com.orcamento.controleorcamento.service;

import br.com.orcamento.controleorcamento.model.Fornecedor;
import br.com.orcamento.controleorcamento.model.Orcamento;
import br.com.orcamento.controleorcamento.model.Produto;
import br.com.orcamento.controleorcamento.repository.FornecedorRepository;
import br.com.orcamento.controleorcamento.repository.OrcamentoRepository;
import br.com.orcamento.controleorcamento.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrcamentoService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private OrcamentoRepository orcamentoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    // Função 1: Visualizar
    public List<Orcamento> listarTodosOsOrcamentos() {
        return orcamentoRepository.findAll();
    }

    // Função 2: Cadastrar
    public Orcamento criarNovoOrcamento(Orcamento orcamento) {
        orcamento.setStatus("Pendente");

        LocalDate hoje = LocalDate.now();
        orcamento.setDataEmissao(hoje);
        orcamento.setDataGeracao(hoje);

        return orcamentoRepository.save(orcamento);
    }

    // Função 3: Deletar
    public void deletarOrcamento(Long id) {
        orcamentoRepository.deleteById(id);
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public List<Fornecedor> listarFornecedores(){
        return fornecedorRepository.findAll();
    }
}