package com.project.GPrint3D.controller.cliente;

import java.security.Principal;

import javax.validation.Valid;

import com.project.GPrint3D.model.PedidosTrocasModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.PedidosComprasRepository;
import com.project.GPrint3D.repository.PedidosTrocasRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.repository.VariaveisRepository;
import com.project.GPrint3D.service.CliFacadeService;
import com.project.GPrint3D.util.Listas.PedidosComprasListUtil;
import com.project.GPrint3D.util.Listas.PedidosTrocasListUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente/meusPedidos/")
public class CliPedidosController
{
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PedidosComprasRepository pedidosComprasRepository;

    @Autowired
    private PedidosTrocasRepository pedidosTrocasRepository;

    @Autowired
    private VariaveisRepository variaveisRepository;

    @Autowired
    private CliFacadeService cliFacadeService;

    // Controle de compras
    @RequestMapping("/meusPedidosCompras")
    public ModelAndView meusPedidosCompras (Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/Pedidos/meusPedidosCompras");

        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());

        mv.addObject("pedidos", pedidosComprasRepository.findAllByCliente(usu.getCliente().getCliId()));

        return mv;
    }

    @PostMapping("/detalhesPedidoCompras")
    public ModelAndView detalhesPedidoCompras (@RequestParam(name = "id") Integer id, PedidosTrocasModel pedidosTroca)
    {
        ModelAndView mv = new ModelAndView("/cliente/pedidos/detalhesPedidoCompras");

        PedidosComprasListUtil listUtil = new PedidosComprasListUtil();
        VariaveisModel variaveis = variaveisRepository.findOneById(1);

        mv.addObject("pedido", pedidosComprasRepository.findOneById(id));
        mv.addObject("allStatusCli", listUtil.getListCompraPedidosCli());
        mv.addObject("allStatus", listUtil.getListCompraPedidos());
        mv.addObject("tempTroca", variaveis.getVarTempTroca());

        return mv;
    }

    @PostMapping("/cancelarPedido")
    public ModelAndView cancelarPedido (@RequestParam(name = "id") Integer id, RedirectAttributes attributes)
    {
        String[] mensagem = cliFacadeService.cancelarPedidoCompra(id);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/cliente/meusPedidos/meusPedidosCompras");
    }

    @PostMapping("/solicitacaoTroca")
    public ModelAndView solicitacaoTroca (@Valid PedidosTrocasModel pedidoTroca, BindingResult result,
            RedirectAttributes attributes)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/meusPedidos/meusPedidosCompras");
        }

        String[] mensagem = cliFacadeService.solicitarTroca(pedidoTroca);       

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/cliente/meusPedidos/meusPedidosCompras");
    }

    // Controle de trocas
    @RequestMapping("/meusPedidosTrocas")
    public ModelAndView meusPedidosTrocas (Principal principal, PedidosTrocasModel pedidosTroca)
    {
        ModelAndView mv = new ModelAndView("/cliente/Pedidos/meusPedidosTrocas");

        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());

        mv.addObject("pedidos", pedidosTrocasRepository.findAllByCliente(usu.getCliente().getCliId()));

        return mv;
    }

    @PostMapping("/escolherTroca")
    public ModelAndView escolherTroca (@RequestParam(name = "inputEscolher") String escolher,
            @Valid PedidosTrocasModel pedidoTroca, BindingResult result, RedirectAttributes attributes)
    {
        if (!escolher.equalsIgnoreCase("escolher") || result.hasErrors())
        {
            return new ModelAndView("redirect:/cliente/meusPedidos/meusPedidosTrocas");
        }

        String[] mensagem = cliFacadeService.escolherTroca(pedidoTroca);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/cliente/meusPedidos/meusPedidosTrocas");
    }

    @PostMapping("/detalhesPedidoTrocas")
    public ModelAndView detalhesPedidoTrocas (@RequestParam(name = "id") Integer id, PedidosTrocasModel pedidosTroca)
    {
        ModelAndView mv = new ModelAndView("/cliente/pedidos/detalhesPedidoTrocas");

        PedidosTrocasModel pedTrocas = pedidosTrocasRepository.findOneById(id);
        PedidosTrocasListUtil listUtil = new PedidosTrocasListUtil();

        mv.addObject("pedido", pedTrocas);
        mv.addObject("allStatus", listUtil.getListTrocaEscolha(pedTrocas.getPdtEscolha()));

        return mv;
    }
}
