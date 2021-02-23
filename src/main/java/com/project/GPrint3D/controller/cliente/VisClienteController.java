package com.project.GPrint3D.controller.cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Cliente")
public class VisClienteController 
{
    @RequestMapping("/VisualizarInformacoes")
    public String visInformacoes()
    {
        return "/cliente/visualizar/visInformacoes";
    }

    @RequestMapping("/VisualizarEndereco")
    public String visEndereco()
    {
        return "/cliente/visualizar/visEndereco";
    }

    @RequestMapping("/VisualizarCartao")
    public String visCartao()
    {
        return "/cliente/visualizar/visCartao";
    }
}