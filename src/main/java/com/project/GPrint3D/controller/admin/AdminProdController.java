package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminProdController 
{
    @RequestMapping("listarProdutos")
    public ModelAndView listagemProdutos()
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/listarProdutos");

        return mv;
    }

    @RequestMapping("cadastrarProdutos")
    public ModelAndView cadastrarProdutos()
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarProdutos");

        return mv;
    }
    @PostMapping("cadastrarProdutos")
    public ModelAndView cadastroProdutos()
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/cadastrarProdutos");

        return mv;
    }

    @RequestMapping("alterarProdutos")
    public ModelAndView alterarProdutos()
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/alterarProdutos");

        return mv;
    }

    @RequestMapping("entradaProdutos")
    public ModelAndView entradaProdutos()
    {
        ModelAndView mv = new ModelAndView("/admin/produtos/entradaProdutos");

        return mv;
    }
}
