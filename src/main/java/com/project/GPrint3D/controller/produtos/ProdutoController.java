package com.project.GPrint3D.controller.produtos;

import java.util.HashMap;
import java.util.List;

import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.repository.VariaveisRepository;
import com.project.GPrint3D.util.CorreiosUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProdutoController 
{
    @Autowired
    private ProdutosRepository produtos;

    @Autowired
    private VariaveisRepository variaveisRepository;

    @GetMapping("/c/{categoria}/p/{produto}")
    public ModelAndView listagemProduto(@PathVariable(value = "categoria") String categoria, @PathVariable(value = "produto") String produto)
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

    @GetMapping("/validarCEP/{cep}")
    @ResponseBody
    public HashMap<String, String> validarCEP(@PathVariable(value = "cep") String cep)
    {
        CorreiosUtil calculo = new CorreiosUtil();

        HashMap<String, String> response = calculo.getValidarCEP(cep);

        return response;
    }
    
    @GetMapping("/calcularFrete/{id}/{cep}")
    @ResponseBody
    public List<HashMap<String, String>> calcularFrete(@PathVariable(value = "id") Integer id, @PathVariable(value = "cep") String cepDestinatario)
    {
        ProdutosModel produto = produtos.findOneById(id);
        String variavel = variaveisRepository.findOneById(1).getVarCep();

        CorreiosUtil calculo = new CorreiosUtil();

        List<HashMap<String, String>> response = calculo.getValorPrazo(variavel.replaceAll("-", ""), cepDestinatario, produto);

        return response;
    }
}
