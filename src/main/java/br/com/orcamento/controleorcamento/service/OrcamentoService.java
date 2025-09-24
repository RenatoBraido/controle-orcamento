package br.com.orcamento.controleorcamento.service;

import br.com.orcamento.controleorcamento.model.Orcamento;
import br.com.orcamento.controleorcamento.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

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
}