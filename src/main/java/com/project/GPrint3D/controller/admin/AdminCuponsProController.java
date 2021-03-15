package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminCuponsProController 
{
    @RequestMapping("cupons/listarCuponsPromocionais")
    public ModelAndView listarCuponsPromocionais()
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/promocionais/listarCuponsPromocionais");

        return mv;
    }

    @RequestMapping("cupons/cadastrarCuponsPromocionais")
    public ModelAndView cadastrarCuponsPromocionais()
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/promocionais/cadastrarCuponsPromocionais");

        return mv;
    }

    @RequestMapping("cupons/alterarCuponsPromocionais")
    public ModelAndView alterarCuponsPromocionais()
    {
        ModelAndView mv = new ModelAndView("/admin/cupons/promocionais/alterarCuponsPromocionais");

        return mv;
    }
}
