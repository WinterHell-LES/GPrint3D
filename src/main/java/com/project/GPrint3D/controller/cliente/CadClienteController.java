package com.project.GPrint3D.controller.cliente;

import com.project.GPrint3D.model.ClientesModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente")
public class CadClienteController 
{    
    // Controle de cadastro de informações
    @RequestMapping("/cadastroDadosPessoais")
    public ModelAndView cadDadosPessoais(ClientesModel cliente)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadDadosPessoais");

        return mv;
    }

    @RequestMapping("/cadastroEndereco")
    public ModelAndView cadEndereco(ClientesModel cliente)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadEndereco");

        return mv;
    }

    @RequestMapping("/cadastroEndereco2")
    public ModelAndView cadEndereco2(ClientesModel cliente)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadEndereco2");

        return mv;
    }

    @RequestMapping("/cadastroCartao")
    public ModelAndView cadCartao(ClientesModel cliente)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadCartao");

        return mv;
    }

    @RequestMapping("/cadastroCartao2")
    public ModelAndView cadCartao2(ClientesModel cliente)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadCartao2");

        return mv;
    }
}