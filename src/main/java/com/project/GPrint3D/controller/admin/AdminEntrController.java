package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminEntrController 
{
    @RequestMapping("listarEntradas")
    public ModelAndView listarEntradas()
    {
        ModelAndView mv = new ModelAndView("/admin/entradas/listarEntradas");

        return mv;
    }

    @RequestMapping("cadastrarEntradas")
    public ModelAndView cadastrarEntradas()
    {
        ModelAndView mv = new ModelAndView("/admin/entradas/cadastrarEntradas");

        return mv;
    }
}
