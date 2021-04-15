package com.project.GPrint3D.controller.admin;

import javax.validation.Valid;

import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.PedidosTrocasModel;
import com.project.GPrint3D.repository.PedidosComprasRepository;
import com.project.GPrint3D.repository.PedidosTrocasRepository;
import com.project.GPrint3D.service.PedidosComprasService;
import com.project.GPrint3D.service.PedidosTrocasService;
import com.project.GPrint3D.util.Listas.PedidosComprasListUtil;
import com.project.GPrint3D.util.Listas.PedidosTrocasListUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/logistica/")
public class AdminLogiPedController 
{
    @Autowired
    private PedidosComprasRepository pedidosComprasRepository;

    @Autowired
    private PedidosTrocasRepository pedidosTrocasRepository;

    @Autowired
    private PedidosComprasService pedidosComprasService;
    
    @Autowired
    private PedidosTrocasService pedidosTrocasService;

    @RequestMapping("listarLogisticaPedidos")
    public ModelAndView listarLogisticaPedidos()
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/pedidosCompras/listarLogisticaPedidos");

        mv.addObject("pedidos", pedidosComprasRepository.findAll());

        return mv;
    }

    @PostMapping("alterarLogisticaPedidos")
    public ModelAndView alterarLogisticaPedidos(@RequestParam(name = "id") Integer id, PedidosComprasModel pedidosCompra)
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/pedidosCompras/alterarLogisticaPedidos");
        PedidosComprasListUtil pedidos = new PedidosComprasListUtil();

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));
        mv.addObject("allStatus", pedidos.getListCompraLogistica());

        return mv;
    }
    @PostMapping("alterarLogisticaPedido")
    public ModelAndView alterarLogisticaPedido(@Valid PedidosComprasModel pedido, RedirectAttributes attributes)
    {
        String[] mensagem = pedidosComprasService.atualizarLogistica(pedido.getPdcStatusLogistica(), pedido.getPdcId());

        if (pedido.getPdcStatusLogistica() > 3)
        {
            pedidosComprasService.atualizarPedido(pedido.getPdcStatusLogistica() - 1, pedido.getPdcId());
        }
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/logistica/listarLogisticaPedidos");
    }

    @RequestMapping("listarLogisticaTrocas")
    public ModelAndView listarLogisticaTrocas()
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/pedidosTrocas/listarLogisticaTrocas");

        mv.addObject("pedidos", pedidosTrocasRepository.findAllByEscolha());

        return mv;
    }

    @PostMapping("alterarLogisticaTrocas")
    public ModelAndView alterarLogisticaTrocas(@RequestParam(name = "id") Integer id, PedidosComprasModel pedidosCompra)
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/pedidosTrocas/alterarLogisticaTrocas");

        PedidosTrocasListUtil pedidos = new PedidosTrocasListUtil();
        PedidosTrocasModel pedTroca = pedidosTrocasRepository.findOneById(id);

        mv.addObject("pedido", pedTroca);
        mv.addObject("allStatus", pedidos.getListTrocaLog(pedTroca.getPdtEscolha()));

        return mv;
    }
    @PostMapping("alterarLogisticaTroca")
    public ModelAndView alterarLogisticaTroca(@Valid PedidosTrocasModel pedido, RedirectAttributes attributes)
    {
        String[] mensagem = pedidosTrocasService.atualizarLogistica(pedido.getPdtStatusLogistica(), pedido.getPdtId());

        if (pedido.getPdtEscolha() == 1)
        {
            if (pedido.getPdtStatusLogistica() > 3)
            {
                pedidosTrocasService.atualizarPedido(pedido.getPdtStatusLogistica() - 2, pedido.getPdtId());
            }
        }
        else
        {
            if (pedido.getPdtStatusLogistica() > 2)
            {
                pedidosTrocasService.atualizarPedido(pedido.getPdtStatusLogistica() - 2, pedido.getPdtId());
            }
        }
  
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/logistica/listarLogisticaTrocas");
    }
}
