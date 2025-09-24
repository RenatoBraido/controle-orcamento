package br.com.orcamento.controleorcamento.controller;

import br.com.orcamento.controleorcamento.model.Orcamento;
import br.com.orcamento.controleorcamento.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*
    *
    *@GetMapping("/novo")
    public String exibirFormulario(@RequestParam("produtoId") Long produtoId, Model model) {

        Optional<Produto> resultadoBusca = produtoRepository.findById(produtoId);

        if (resultadoBusca.isPresent()) {
            Produto produtoEncontrado = resultadoBusca.get();

            Orcamento novoOrcamento = new Orcamento();
            novoOrcamento.setProduto(produtoEncontrado); // Associamos o produto ao orçamento

            model.addAttribute("produto", produtoEncontrado);
            model.addAttribute("orcamento", novoOrcamento);

            return "formulario-orcamento";
        }

        return "redirect:/produtos";
    }

    // ----- METODO NOVO PARA SALVAR -----
    @PostMapping("/orcamentos")
    public String salvarOrcamento(@ModelAttribute Orcamento orcamento) {
        // Define a data e hora atuais antes de salvar
        orcamento.setDataEmissao(LocalDate.now());

        // Salva o objeto orcamento no banco de dados
        orcamentoRepository.save(orcamento);

        // Redireciona o usuário de volta para a lista de produtos
        return "redirect:/produtos";
    }
    * */

}