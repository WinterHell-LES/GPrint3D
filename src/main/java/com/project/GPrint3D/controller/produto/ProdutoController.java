package com.project.GPrint3D.controller.produto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutoController 
{
    @RequestMapping("/produto")
    public String produto()
    {
        return "/produto";
    }
}
