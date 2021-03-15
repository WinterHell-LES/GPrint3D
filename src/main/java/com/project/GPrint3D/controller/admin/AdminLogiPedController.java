package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminLogiPedController 
{
    @RequestMapping("logistica/listarLogisticaPedidos")
    public ModelAndView listarLogisticaPedidos()
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/pedidos/listarLogisticaPedidos");

        return mv;
    }

    @RequestMapping("logistica/alterarLogisticaPedidos")
    public ModelAndView alterarLogisticaPedidos()
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/pedidos/alterarLogisticaPedidos");

        return mv;
    }
}
