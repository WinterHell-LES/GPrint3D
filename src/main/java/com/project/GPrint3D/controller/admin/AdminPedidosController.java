package com.project.GPrint3D.controller.admin;

import javax.validation.Valid;

import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.PedidosTrocasModel;
import com.project.GPrint3D.repository.PedidosComprasRepository;
import com.project.GPrint3D.repository.PedidosTrocasRepository;
import com.project.GPrint3D.service.PedidosComprasService;
import com.project.GPrint3D.service.PedidosTrocasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminPedidosController 
{
    @Autowired
    private PedidosComprasRepository pedidosComprasRepository;

    @Autowired
    private PedidosTrocasRepository pedidosTrocasRepository;

    @Autowired
    private PedidosComprasService pedidosComprasService;
    
    @Autowired
    private PedidosTrocasService pedidosTrocasService;

    @RequestMapping("listarPedidosCompras")
    public ModelAndView listarPedidosCompras()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/listarPedidosCompras");

        mv.addObject("pedidos", pedidosComprasRepository.findAll());

        return mv;
    }

    @PostMapping("infoPedidosCompras")
    public ModelAndView infoPedidosCompras(@RequestParam(name = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/infoPedidosCompras");

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));

        return mv;
    }
    
    @PostMapping("alterarPedidosCompras")
    public ModelAndView alterarPedidosCompras(@RequestParam(name = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/alterarPedidosCompras");

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));

        return mv;
    }
    @PostMapping("alterarPedidoCompra")
    public ModelAndView alterarPedidoCompra(@Valid PedidosComprasModel pedido, RedirectAttributes attributes)
    {
        String[] mensagem = pedidosComprasService.atualizar(pedido);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarPedidosCompras");
    }

    @RequestMapping("listarPedidosTrocas")
    public ModelAndView listarPedidosTrocas()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/listarPedidosTrocas");

        mv.addObject("pedidos", pedidosTrocasRepository.findAll());

        return mv;
    }

    @PostMapping("infoPedidosTrocas")
    public ModelAndView infoPedidosTrocas(@RequestParam(name = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/infoPedidosTrocas");

        mv.addObject("pedido", pedidosTrocasRepository.findOneById(id));

        return mv;
    }
    
    @PostMapping("alterarPedidosTrocas")
    public ModelAndView alterarPedidosTrocas(@RequestParam(name = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/alterarPedidosTrocas");

        mv.addObject("pedido", pedidosTrocasRepository.findOneById(id));

        return mv;
    }
    @PostMapping("alterarPedidoTroca")
    public ModelAndView alterarPedidoTroca(@Valid PedidosTrocasModel pedido, RedirectAttributes attributes)
    {
        String[] mensagem = pedidosTrocasService.atualizar(pedido);
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/listarPedidosCompras");
    }
}
