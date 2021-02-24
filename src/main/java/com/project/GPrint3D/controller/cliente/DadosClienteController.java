package com.project.GPrint3D.controller.cliente;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class DadosClienteController 
{
    @RequestMapping("/alterarSenha")
    public ModelAndView alterarSenha(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/alterarSenha");

        return mv;
    }

    @RequestMapping("/meuCadastro")
    public ModelAndView meuCadastro(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meuCadastro");

        return mv;
    }

    @RequestMapping("/meusCartoes")
    public ModelAndView meusCartoes(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meusCartoes");

        return mv;
    }

    @RequestMapping("/meusEnderecos")
    public ModelAndView meusEnderecos(HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/dadosPessoais/meusEnderecos");

        return mv;
    }
}