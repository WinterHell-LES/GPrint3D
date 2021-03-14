package com.project.GPrint3D.controller.produtos.produtos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController 
{
    @RequestMapping("/produto")
    public ModelAndView produto()
    {
        ModelAndView mv = new ModelAndView("/produtos/descricao/descricaoProduto");

        return mv;
    }
}
