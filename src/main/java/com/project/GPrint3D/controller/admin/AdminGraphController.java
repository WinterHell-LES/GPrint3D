package com.project.GPrint3D.controller.admin;

import java.util.HashMap;
import java.util.List;

import com.project.GPrint3D.service.AdminFacadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminGraphController 
{
    @Autowired
    private AdminFacadeService adminFacadeService;

    @RequestMapping("visualizarGraficoPedidos")
    public ModelAndView visualizarGraficoPedidos () 
    {
        return new ModelAndView("/admin/graficos/visualizarGraficoPedidos");
    }

    @RequestMapping("visualizarGraficoProdutos")
    public ModelAndView visualizarGraficoProdutos () 
    {
        return new ModelAndView("/admin/graficos/visualizarGraficoProdutos");
    }

    @RequestMapping("visualizarGraficoCategorias")
    public ModelAndView visualizarGraficoCategorias () 
    {
        return new ModelAndView("/admin/graficos/visualizarGraficoCategorias");
    }

    @GetMapping("graphFeed/pedidos")
    @ResponseBody
    public List<HashMap<String, String>> graphFeedPedidos () 
    {
        return adminFacadeService.gerarGrafPedidos();
    }

    @GetMapping("graphFeed/produtos")
    @ResponseBody
    public List<HashMap<String, String>> graphFeedProdutos () 
    {
        return adminFacadeService.gerarGrafProdutos();
    }

    @GetMapping("graphFeed/categorias")
    @ResponseBody
    public List<HashMap<String, String>> graphFeedCategorias () 
    {
        return adminFacadeService.gerarGrafCategorias();
    }
}