package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminPedidosController 
{
    @RequestMapping("listarPedidos")
    public ModelAndView listagemPedidos()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/listarPedidos");

        return mv;
    }

    @RequestMapping("listarPedidosTrocas")
    public ModelAndView listarPedidosTrocas()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/listarPedidosTrocas");

        return mv;
    }

    @RequestMapping("infoPedidos")
    public ModelAndView informacoesPedidos()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/infoPedidos");

        return mv;
    }

    @RequestMapping("alterarPedidos")
    public ModelAndView alterarPedidos()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/alterarPedidos");

        return mv;
    }

    @RequestMapping("alterarPedidosTrocas")
    public ModelAndView alterarPedidosTrocas()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/alterarPedidosTrocas");

        return mv;
    }
}
