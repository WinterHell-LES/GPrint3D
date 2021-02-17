package com.project.GPrint3D.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Cliente")
public class CadClienteController 
{
    @RequestMapping("/Cadastro")
    public String cadCliente()
    {
        return "/cadastro/cadastroCliente";
    }
}
