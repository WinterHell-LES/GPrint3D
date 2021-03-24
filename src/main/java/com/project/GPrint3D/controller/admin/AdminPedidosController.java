package com.project.GPrint3D.controller.admin;

import com.project.GPrint3D.repository.PedidosComprasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminPedidosController 
{
    @Autowired
    private PedidosComprasRepository pedidosCompras;

    @RequestMapping("listarPedidos")
    public ModelAndView listagemPedidos()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/listarPedidos");

        mv.addObject("pedidos", pedidosCompras.findAll());

        return mv;
    }

    @PostMapping("infoPedidos")
    public ModelAndView informacoesPedidos()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/infoPedidos");

        return mv;
    }
    
    @PostMapping("alterarPedidos")
    public ModelAndView alterarPedidos()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/alterarPedidos");

        return mv;
    }

    @RequestMapping("listarPedidosTrocas")
    public ModelAndView listarPedidosTrocas()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/listarPedidosTrocas");

        return mv;
    }

    @RequestMapping("alterarPedidosTrocas")
    public ModelAndView alterarPedidosTrocas()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/alterarPedidosTrocas");

        return mv;
    }
}
