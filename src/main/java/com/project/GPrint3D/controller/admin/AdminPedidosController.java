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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/pedido/")
public class AdminPedidosController
{
    @Autowired
    private PedidosComprasRepository pedidosComprasRepository;

    @Autowired
    private PedidosTrocasRepository pedidosTrocasRepository;

    @Autowired
    private AdminFacadeService adminFacadeService;


    // Controle de compras
    @RequestMapping("listarPedidosCompras")
    public ModelAndView listarPedidosCompras ()
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosCompras/listarPedidosCompras");

        mv.addObject("pedidos", pedidosComprasRepository.findAll());

        return mv;
    }

    @GetMapping("/infoCompras/{id}/dados")
    public ModelAndView listagemDadosCompras (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosCompras/infoCompras/infoPedidosComprasDados");

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));

        return mv;
    }

    @GetMapping("/infoCompras/{id}/produtos")
    public ModelAndView listagemProdutosCompras (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosCompras/infoCompras/infoPedidosComprasProdutos");

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));

        return mv;
    }

    @GetMapping("/infoCompras/{id}/cliente")
    public ModelAndView listagemClienteCompras (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosCompras/infoCompras/infoPedidosComprasCliente");

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));

        return mv;
    }

    @GetMapping("/infoCompras/{id}/enderecos")
    public ModelAndView listagemEnderecosCompras (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosCompras/infoCompras/infoPedidosComprasEnderecos");

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));

        return mv;
    }

    @GetMapping("/infoCompras/{id}/cartoes")
    public ModelAndView listagemCartoesCompras (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosCompras/infoCompras/infoPedidosComprasCartoes");

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));

        return mv;
    }

    @PostMapping("alterarPedidosCompras")
    public ModelAndView alterarPedidosCompras (@RequestParam(name = "id") Integer id, PedidosComprasModel pedidosCompra)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosCompras/alterarPedidosCompras");
        PedidosComprasListUtil pedidos = new PedidosComprasListUtil();

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));
        mv.addObject("allStatus", pedidos.getListCompraPedidosCli());
        mv.addObject("btStatus", pedidos.getBtListCompraPedidos());

        return mv;
    }

    @PostMapping("alterarPedidoCompra")
    public ModelAndView alterarPedidoCompra (PedidosComprasModel pedido, RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.atualizarPedidoCompras(pedido);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/pedido/listarPedidosCompras");
    }

    // Controle de trocas
    @RequestMapping("listarPedidosTrocas")
    public ModelAndView listarPedidosTrocas (PedidosTrocasModel pedidosTrocas)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosTrocas/listarPedidosTrocas");

        mv.addObject("pedidos", pedidosTrocasRepository.findAll());

        return mv;
    }

    @GetMapping("/infoTrocas/{id}/dados")
    public ModelAndView listagemDadosTrocas (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosTrocas/infoTrocas/infoPedidosTrocasDados");

        mv.addObject("pedido", pedidosTrocasRepository.findOneById(id));

        return mv;
    }

    @GetMapping("/infoTrocas/{id}/produtos")
    public ModelAndView listagemProdutosTrocas (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosTrocas/infoTrocas/infoPedidosTrocasProdutos");

        mv.addObject("pedido", pedidosTrocasRepository.findOneById(id));

        return mv;
    }

    @GetMapping("/infoTrocas/{id}/cliente")
    public ModelAndView listagemClienteTrocas (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosTrocas/infoTrocas/infoPedidosTrocasCliente");

        mv.addObject("pedido", pedidosTrocasRepository.findOneById(id));

        return mv;
    }

    @GetMapping("/infoTrocas/{id}/enderecos")
    public ModelAndView listagemEnderecosTrocas (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosTrocas/infoTrocas/infoPedidosTrocasEnderecos");

        mv.addObject("pedido", pedidosTrocasRepository.findOneById(id));

        return mv;
    }

    @GetMapping("/infoTrocas/{id}/cartoes")
    public ModelAndView listagemCartoesTrocas (@PathVariable("id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosTrocas/infoTrocas/infoPedidosTrocasCartoes");

        mv.addObject("pedido", pedidosTrocasRepository.findOneById(id));

        return mv;
    }

    @PostMapping("alterarPedidosTrocas")
    public ModelAndView alterarPedidosTrocas (@RequestParam(name = "id") Integer id)
    {
        ModelAndView mv = new ModelAndView("/admin/pedidos/pedidosTrocas/alterarPedidosTrocas");
        PedidosTrocasListUtil pedidos = new PedidosTrocasListUtil();

        PedidosTrocasModel pedTrocas = pedidosTrocasRepository.findOneById(id);

        mv.addObject("pedido", pedTrocas);
        mv.addObject("allStatus", pedidos.getListTrocaEscolha(pedTrocas.getPdtEscolha()));
        mv.addObject("btStatus", pedidos.getBtListTrocaEscolha(pedTrocas.getPdtEscolha()));

        return mv;
    }

    @PostMapping("alterarPedidoTroca")
    public ModelAndView alterarPedidoTroca (PedidosTrocasModel pedido, RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.atualizarPedidoTrocas(pedido);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/pedido/listarPedidosTrocas");
    }

    @PostMapping("retornoProdutoTroca")
    public ModelAndView retornoProdutoTroca (PedidosTrocasModel pedido,
            @RequestParam(name = "quantidade") Integer quantidade, RedirectAttributes attributes)
    {
        String[] mensagem = adminFacadeService.cadastrarRetornoPedidoTroca(pedido, quantidade);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/admin/pedido/listarPedidosTrocas");
    }
}
