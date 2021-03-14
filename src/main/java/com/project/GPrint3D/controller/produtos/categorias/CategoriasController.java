package com.project.GPrint3D.controller.produtos.categorias;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoriasController 
{
    @RequestMapping("/categoria1")
    public ModelAndView categoria1()
    {
        ModelAndView mv = new ModelAndView("/produtos/categorias/categoria1");

        return mv;
    }

    @RequestMapping("/categoria2")
    public ModelAndView categoria2()
    {
        ModelAndView mv = new ModelAndView("/produtos/categorias/categoria2");

        return mv;
    }
}
