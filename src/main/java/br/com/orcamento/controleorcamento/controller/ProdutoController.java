package br.com.orcamento.controleorcamento.controller;

import br.com.orcamento.controleorcamento.model.Produto;
import br.com.orcamento.controleorcamento.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        List<Produto> listaDeProdutos = produtoRepository.findAll();
        model.addAttribute("produtos", listaDeProdutos);
        return "lista-produtos";
    }
}