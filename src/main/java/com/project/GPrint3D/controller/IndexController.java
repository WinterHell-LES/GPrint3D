package com.project.GPrint3D.controller;

import com.project.GPrint3D.model.CategoriasModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.CategoriasRepository;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.repository.VariaveisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController 
{
    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private ProdutosRepository produtosRepository;
    
    @Autowired
    private VariaveisRepository variaveisRepository;

    @RequestMapping({"/", "/index"})
    public ModelAndView index(CategoriasModel categoria)
    {
        ModelAndView mv = new ModelAndView("/index");

        VariaveisModel variavel = variaveisRepository.findOneById(1);

        mv.addObject("categorias", categoriasRepository.findAllRand(variavel.getVarCategoria()));
        
        return mv;
    }

    @GetMapping("/pesquisa")
    public ModelAndView pesquisa(@RequestParam(name = "search") String pesquisa, ProdutosModel produto)
    {
        ModelAndView mv = new ModelAndView("/produtos/procuras/procurasProdutos");

        mv.addObject("tituloPesquisa", pesquisa);
        mv.addObject("produtos", produtosRepository.findAllLike(pesquisa));
        
        return mv;
    }
}
