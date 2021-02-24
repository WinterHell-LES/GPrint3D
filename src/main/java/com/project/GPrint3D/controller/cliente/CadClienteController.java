package com.project.GPrint3D.controller.cliente;

import com.project.GPrint3D.model.ClientesModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Cliente")
public class CadClienteController 
{    
    // Controle de cadastro de informações
    @RequestMapping("/CadastroInformacoes")
    public ModelAndView cadastroInformacoes(ClientesModel cliente)
    {
        ModelAndView mv = new ModelAndView("/cliente/cadastro/cadInformacoes");

        return mv;
    }

    @RequestMapping("/CadastroEndereco")
    public String cadEndereco()
    {
        return "/cliente/cadastro/cadEndereco";
    }

    @RequestMapping("/CadastroCartao")
    public String cadCartao()
    {
        return "/cliente/cadastro/cadCartao";
    }
}