package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminCategController 
{
    @RequestMapping("listarCategorias")
    public ModelAndView listarCategorias()
    {
        ModelAndView mv = new ModelAndView("/admin/categorias/listarCategorias");

        return mv;
    }

    @RequestMapping("cadastrarCategorias")
    public ModelAndView cadastrarCategorias()
    {
        ModelAndView mv = new ModelAndView("/admin/categorias/cadastrarCategorias");

        return mv;
    }

    @RequestMapping("alterarCategorias")
    public ModelAndView alterarCategorias()
    {
        ModelAndView mv = new ModelAndView("/admin/categorias/alterarCategorias");

        return mv;
    }
}
