package com.project.GPrint3D.controller.admin;

import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.PedidosTrocasModel;
import com.project.GPrint3D.repository.PedidosComprasRepository;
import com.project.GPrint3D.repository.PedidosTrocasRepository;
import com.project.GPrint3D.service.AdminFacadeService;
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
    private AdminFacadeService adminFacadeService;

    @RequestMapping("listarLogisticaPedidos")
    public ModelAndView listarLogisticaPedidos ()
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/pedidosCompras/listarLogisticaPedidos");

        mv.addObject("pedidos", pedidosComprasRepository.findAll());

        return mv;
    }

    @PostMapping("alterarLogisticaPedidos")
    public ModelAndView alterarLogisticaPedidos (@RequestParam(name = "id") Integer id,
            PedidosComprasModel pedidosCompra)
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/pedidosCompras/alterarLogisticaPedidos");
        PedidosComprasListUtil pedidos = new PedidosComprasListUtil();

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));
        mv.addObject("allStatus", pedidos.getListCompraLogisticaCli());
        mv.addObject("btStatus", pedidos.getBtListCompraLogistica());

        return mv;
    }

    @PostMapping("alterarLogisticaPedido")
    public ModelAndView alterarLogisticaPedido (PedidosComprasModel pedido, RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.atualizarLogisticaCompras(pedido);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/logistica/listarLogisticaPedidos");
    }

    @RequestMapping("listarLogisticaTrocas")
    public ModelAndView listarLogisticaTrocas ()
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/pedidosTrocas/listarLogisticaTrocas");

        mv.addObject("pedidos", pedidosTrocasRepository.findAllByEscolha());

        return mv;
    }

    @PostMapping("alterarLogisticaTrocas")
    public ModelAndView alterarLogisticaTrocas (@RequestParam(name = "id") Integer id,
            PedidosComprasModel pedidosCompra)
    {
        ModelAndView mv = new ModelAndView("/admin/logistica/pedidosTrocas/alterarLogisticaTrocas");

        PedidosTrocasListUtil pedidos = new PedidosTrocasListUtil();
        PedidosTrocasModel pedTroca = pedidosTrocasRepository.findOneById(id);

        mv.addObject("pedido", pedTroca);
        mv.addObject("allStatus", pedidos.getListTrocaLog(pedTroca.getPdtEscolha()));
        mv.addObject("btStatus", pedidos.getBtListTrocaLog(pedTroca.getPdtEscolha()));

        return mv;
    }

    @PostMapping("alterarLogisticaTroca")
    public ModelAndView alterarLogisticaTroca (PedidosTrocasModel pedido, RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.atualizarLogisticaTrocas(pedido);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/logistica/listarLogisticaTrocas");
    }
}
