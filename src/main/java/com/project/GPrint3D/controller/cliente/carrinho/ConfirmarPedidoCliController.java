package com.project.GPrint3D.controller.cliente.carrinho;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class ConfirmarPedidoCliController
{    
    //Tela de confirmação do pedido
    @RequestMapping("/carrinho/confirmarPedido")
    public ModelAndView escolherEndereco(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/confirmarPedido");

        return mv;
    }
}
