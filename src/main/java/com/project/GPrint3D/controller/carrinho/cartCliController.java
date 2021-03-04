package com.project.GPrint3D.controller.carrinho;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
public class cartCliController
{
    //Tela de alteração de dados do cartão do cliente
    @RequestMapping("/meuCarrinho")
    public ModelAndView meuCarrinho()
    {
        ModelAndView mv = new ModelAndView("/carrinho/meuCarrinho");

        return mv;
    }
    
}
