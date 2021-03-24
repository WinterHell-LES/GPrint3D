package com.project.GPrint3D.controller.produtos.categorias;

import com.project.GPrint3D.repository.CategoriasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/c")
public class CategoriasController 
{
    @Autowired
    private CategoriasRepository categorias;

    @GetMapping("/{categoria}")
    public ModelAndView listagemCategoria(@PathVariable(value = "categoria") String categoria)
    {
        ModelAndView mv = new ModelAndView("/produtos/categorias/categoria2");

        mv.addObject("tituloCategoria", categoria.replaceAll("_", " "));
        mv.addObject("produtos", categorias.findOneByNome(categoria.replaceAll("_", " ")).getListCategoriasProdutos());
        mv.addObject("link", "/c/" + categoria);

        return mv;
    }
}