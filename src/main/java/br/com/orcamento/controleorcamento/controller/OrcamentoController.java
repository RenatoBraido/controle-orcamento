package br.com.orcamento.controleorcamento.controller;

import br.com.orcamento.controleorcamento.model.Orcamento;
import br.com.orcamento.controleorcamento.model.Produto;
import br.com.orcamento.controleorcamento.repository.FornecedorRepository;
import br.com.orcamento.controleorcamento.repository.OrcamentoRepository;
import br.com.orcamento.controleorcamento.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/orcamento")
public class OrcamentoController {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @GetMapping("/novo")
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
}