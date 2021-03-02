package com.project.GPrint3D.controller.cliente.pedidos;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class PedidosClienteController 
{
    @RequestMapping("/meusPedidos")
    public ModelAndView meusPedidos(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/Pedidos/meusPedidos");

        return mv;
    }

    @RequestMapping("/meuRanking")
    public ModelAndView meuRanking(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/Pedidos/meuRanking");

        return mv;
    }
}