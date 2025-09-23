package br.com.orcamento.controleorcamento.controller;

import br.com.orcamento.controleorcamento.model.Orcamento;
import br.com.orcamento.controleorcamento.model.Produto;
import br.com.orcamento.controleorcamento.repository.OrcamentoRepository;
import br.com.orcamento.controleorcamento.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class OrcamentoController {

    private final ProdutoRepository produtoRepository;
    private final OrcamentoRepository orcamentoRepository; // ADICIONADO

    // Modificado para receber os dois repositórios
    public OrcamentoController(ProdutoRepository produtoRepository, OrcamentoRepository orcamentoRepository) {
        this.produtoRepository = produtoRepository;
        this.orcamentoRepository = orcamentoRepository;
    }

    @GetMapping("/orcamentos/novo")
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

    // ----- MÉTODO NOVO PARA SALVAR -----
    @PostMapping("/orcamentos")
    public String salvarOrcamento(@ModelAttribute Orcamento orcamento) {
        // Define a data e hora atuais antes de salvar
        orcamento.setDataDoOrcamento(LocalDateTime.now());

        // Salva o objeto orcamento no banco de dados
        orcamentoRepository.save(orcamento);

        // Redireciona o usuário de volta para a lista de produtos
        return "redirect:/produtos";
    }
}