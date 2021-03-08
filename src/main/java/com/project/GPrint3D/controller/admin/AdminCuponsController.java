package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminCuponsController 
{
    @RequestMapping("listarCupons")
    public ModelAndView listarCupons()
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/listarCupons");

        return mv;
    }

    @RequestMapping("cadastrarCupons")
    public ModelAndView cadastrarCupons()
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/cadastrarCupons");

        return mv;
    }

    @RequestMapping("alterarCupons")
    public ModelAndView alterarCupons()
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/alterarCupons");

        return mv;
    }
}
