package br.com.orcamento.controleorcamento.controller;

import br.com.orcamento.controleorcamento.model.Orcamento;
import br.com.orcamento.controleorcamento.model.Produto;
import br.com.orcamento.controleorcamento.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    // FUNÇÃO 1: VISUALIZAR (GET)
    @GetMapping
    public List<Orcamento> listarOrcamentos() {
        return orcamentoService.listarTodosOsOrcamentos();
    }

    // FUNÇÃO 2: CADASTRAR (POST)
    @PostMapping
    public Orcamento salvarOrcamento(@RequestBody Orcamento orcamento) {
        return orcamentoService.criarNovoOrcamento(orcamento);
    }

    // FUNÇÃO 3: DELETAR (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrcamento(@PathVariable Long id) {
        orcamentoService.deletarOrcamento(id);
        return ResponseEntity.noContent().build(); // Retorna uma resposta HTTP 204 (No Content)
    }

    @GetMapping("/ListarProdutos")
    public List<Produto> listarProdutos(){
        return orcamentoService.listarProdutos();
    }


}