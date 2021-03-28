package com.project.GPrint3D.controller.produtos;

import com.project.GPrint3D.repository.ProdutosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController 
{
    @Autowired
    private ProdutosRepository produtos;

    @GetMapping("/c/{categoria}/p/{produto}")
    public ModelAndView listagemProduro(@PathVariable(value = "categoria") String categoria, @PathVariable(value = "produto") String produto)
    {
        ModelAndView mv = new ModelAndView("/produtos/detalhesProduto");

        mv.addObject("produto", produtos.findOneByNome(produto.replaceAll("_", " ")));

        return mv;
    }
}
