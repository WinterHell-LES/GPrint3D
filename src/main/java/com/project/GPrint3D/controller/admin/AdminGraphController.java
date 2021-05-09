package com.project.GPrint3D.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.project.GPrint3D.repository.PedidosComprasRepository;

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
    private PedidosComprasRepository pedidosComprasRepository;

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
        List<HashMap<String, String>> response = new ArrayList<>();

        for (String aux : pedidosComprasRepository.findAllByStatusPedido()) 
        {
            String[] map = aux.split(",", 4);

            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put("pedidos_recebidos", map[0]);
            hashMap.put("produtos_vendidos", map[1]);
            hashMap.put("sale", map[2]);
            hashMap.put("date", map[3]);

            response.add(hashMap);
        }

        return response;
    }

    @GetMapping("graphFeed/produtos")
    @ResponseBody
    public List<HashMap<String, String>> graphFeedProdutos () 
    {
        List<HashMap<String, String>> response = new ArrayList<>();

        for (String aux : pedidosComprasRepository.findAllByStatusProduto()) 
        {
            String[] map = aux.split(",", 3);

            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put("produto_nome", map[0]);
            hashMap.put("produtos_vendidos", map[1]);
            hashMap.put("date", map[2]);

            response.add(hashMap);
        }

        return response;
    }

    @GetMapping("graphFeed/categorias")
    @ResponseBody
    public List<HashMap<String, String>> graphFeedCategorias () 
    {
        List<HashMap<String, String>> response = new ArrayList<>();

        for (String aux : pedidosComprasRepository.findAllByStatusCategoria()) 
        {
            String[] map = aux.split(",", 3);

            HashMap<String, String> hashMap = new HashMap<>();

            hashMap.put("categoria_nome", map[0]);
            hashMap.put("produtos_vendidos", map[1]);
            hashMap.put("date", map[2]);

            response.add(hashMap);
        }

        return response;
    }
}