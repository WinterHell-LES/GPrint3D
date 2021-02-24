package com.project.GPrint3D.controller.admin;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminPedidosController 
{
    @RequestMapping("pedidos")
    public ModelAndView listagemPedidos(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos");

        return mv;
    }
}
