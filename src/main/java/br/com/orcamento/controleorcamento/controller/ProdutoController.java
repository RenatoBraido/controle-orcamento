package br.com.orcamento.controleorcamento.controller;

import br.com.orcamento.controleorcamento.model.Produto;
import br.com.orcamento.controleorcamento.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public List<Produto> listarProdutos(Model model) {
        List<Produto> listaDeProdutos = produtoRepository.findAll();
        model.addAttribute("produtos", listaDeProdutos);
        return listaDeProdutos;
    }
}