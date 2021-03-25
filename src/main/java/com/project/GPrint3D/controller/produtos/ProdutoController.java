package com.project.GPrint3D.controller.produtos;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CarrinhosRepository;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CarrinhosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bytebuddy.asm.Advice.This;

@Controller
public class ProdutoController 
{
    @Autowired
    private CarrinhosRepository carrinhos;

    @Autowired
    private ClientesRepository clientes;

    @Autowired
    private ProdutosRepository produtos;

    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private CarrinhosService carrinhosService;

    @GetMapping("/c/{categoria}/p/{produto}")
    public ModelAndView listagemProduro(@PathVariable(value = "categoria") String categoria, @PathVariable(value = "produto") String produto)
    {
        ModelAndView mv = new ModelAndView("/produtos/detalhesProduto");

        mv.addObject("produto", produtos.findOneByNome(produto.replaceAll("_", " ")));

        return mv;
    }

    //Inclui o produto no carrinho do cliente
    /*@PostMapping("/c/{categoria}/p/{produto}")
    public ModelAndView adicionarAoCarrinho(@PathVariable(value = "categoria") String categoria, @PathVariable(value = "produto") String produtoS, @Valid ProdutosModel produto, BindingResult result, RedirectAttributes attributes, HttpServletRequest auth, Principal principal)
    {
        if (result.hasErrors())
        {
            return new ModelAndView("redirect://c/{categoria}/p/{produto}");
        }

        UsuariosModel usuario = usuarios.findByEmail(principal.getName());
        ClientesModel cliente = clientes.findByUsuarioId(usuario.getUsuId());
        CarrinhosModel carrinho = carrinhos.findByClienteId(cliente.getCliId());

        if (carrinho == null)
        {
            carrinho.setCliente(cliente);
            carrinhosService.atualizar(carrinho);
            carrinho = carrinhos.findByClienteId(cliente.getCliId());
        }

        

        if (crtPadrao == true)
        {
            cartaoPadrao.setCartao(cartao);
            
            cartoesPadroesService.atualizar(cartaoPadrao);
        }
        
        cartoesService.atualizar(cartao);

        return new ModelAndView("redirect:/cliente/meusCartoes");
    }*/
}
