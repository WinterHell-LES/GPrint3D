package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminLogiTrcController 
{
    @RequestMapping("logistica/listarLogisticaTrocas")
    public ModelAndView listarLogisticaTrocas()
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/trocas/listarLogisticaTrocas");

        return mv;
    }

    @RequestMapping("logistica/alterarLogisticaTrocas")
    public ModelAndView alterarLogisticaTrocas()
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/trocas/alterarLogisticaTrocas");

        return mv;
    }
}
