package com.project.GPrint3D.controller.cliente.carrinho;

import com.project.GPrint3D.util.CarrinhoUtil;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CarrinhosRepository;
import com.project.GPrint3D.repository.CartoesPadroesRepository;
import com.project.GPrint3D.repository.CartoesRepository;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.EndEntregasPadroesRepository;
import com.project.GPrint3D.repository.EnderecosRepository;
import com.project.GPrint3D.repository.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cliente/carrinho")
public class CartCliController extends CarrinhoUtil
{
    private UsuariosModel usuarioMod = new UsuariosModel();
    private ClientesModel clienteMod = new ClientesModel();
    private CarrinhosModel carrinhoMod = new CarrinhosModel();
    private EnderecosModel enderecoMod = new EnderecosModel();
    private List<CartoesModel> listaCartoesPagMod = new ArrayList<CartoesModel>();

    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private EnderecosRepository enderecos;

    @Autowired
    private EndEntregasPadroesRepository enderecosEntregaPadroes;

    @Autowired
    private CartoesPadroesRepository cartoesPadroes;

    @Autowired
    private CartoesRepository cartoes;
    
    @Autowired
    private CarrinhosRepository carrinhos;
    
    //Tela dos enderços do clientes
    @RequestMapping("/escolherEndereco")
    public ModelAndView escolherEndereco(Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/escolherEndereco");
        
        usuarioMod = usuarios.findByEmail(principal.getName());
        clienteMod = clientes.findByUsuarioId(usuarioMod.getUsuId());

        if (enderecoMod.getEndId() == 0)
        {
            enderecoMod = enderecosEntregaPadroes.findByClienteId(clienteMod.getCliId()).getEndereco();
        }

        mv.addObject("cliente", clienteMod);
        mv.addObject("endereco", enderecoMod);

        return mv;
    }

    //Alterar endereco de entrega
    @PostMapping("/alterarEndereco")
    public ModelAndView alterarEnderecoEntrega(@RequestParam(name = "id") Integer enderecoId)
    {
        enderecoMod = enderecos.findOneById(enderecoId);

        return new ModelAndView("redirect:/cliente/carrinho/escolherEndereco");
    }
    
    //Tela dos enderços do clientes
    @RequestMapping("/escolherPagamento")
    public ModelAndView escolherPagamento(Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/escolherPagamento");

        usuarioMod = usuarios.findByEmail(principal.getName());
        clienteMod = clientes.findByUsuarioId(usuarioMod.getUsuId());
        carrinhoMod = carrinhos.findByClienteId(clienteMod.getCliId());

        if (listaCartoesPagMod.size() == 0)
        {
            listaCartoesPagMod.add(cartoesPadroes.findByClienteId(clienteMod.getCliId()).getCartao());
        }

        mv.addObject("listaCartoesPagamento", listaCartoesPagMod);
        mv.addObject("totalCarrinho", valorTotalCarrinho(carrinhoMod));
        mv.addObject("produtosCarrinho", carrinhoMod.getListProdutos());
        mv.addObject("cliente", clienteMod);

        return mv;
    }

    //Aplica um cupom de troca
    @PostMapping("/aplicaCupomTroca")
    public ModelAndView aplicarCupomTroca()
    {
        
        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Aplica um cupom promocional
    @PostMapping("/aplicaCupomPromocional")
    public ModelAndView aplicarCupomPromocional()
    {
        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Aplica um cupom de troca
    @PostMapping("/removeCupom")
    public ModelAndView removerCupom()
    {
        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Inclui cartão para pagamento
    @PostMapping("/inlcuiCartao")
    public ModelAndView incluirCartao(@RequestParam(name = "id") Integer cartaoId)
    {
        CartoesModel cartaoIncluir = cartoes.findOneById(cartaoId);

        for (CartoesModel cartao : listaCartoesPagMod)
        {
            if (cartao.getCrtId() == cartaoIncluir.getCrtId())
            {
                String response = "Cartão já incluso, inclua outro cartão.";
                return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
            }
        }
        
        listaCartoesPagMod.add(cartaoIncluir);

        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Remove cartão para pagamento
    @PostMapping("/removeCartao")
    public ModelAndView removeCartao(@RequestParam(name = "index") int cartaoIndex)
    {
        listaCartoesPagMod.remove(cartaoIndex);
        
        return new ModelAndView("redirect:/cliente/carrinho/escolherPagamento");
    }

    //Tela de confirmação do pedido
    @RequestMapping("confirmarPedido")
    public ModelAndView confirmarPedido(Principal principal)
    {
        ModelAndView mv = new ModelAndView("/cliente/carrinho/confirmarPedido");

        return mv;
    }
    
}