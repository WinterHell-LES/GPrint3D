package com.project.GPrint3D.controller.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Cliente")
public class CadClienteController 
{
    @RequestMapping("/CadastroInformacoes")
    public String cadInformacoes()
    {
        return "/cliente/cadastro/cadInformacoes";
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