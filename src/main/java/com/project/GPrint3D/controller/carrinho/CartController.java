package com.project.GPrint3D.controller.carrinho;

import com.project.GPrint3D.util.CarrinhoUtil;
import com.project.GPrint3D.util.CorreiosUtil;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.repository.VariaveisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
public class CartController extends CarrinhoUtil
{
    //private int mesclagemCarrinho = 0;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private VariaveisRepository variaveisRepository;

    //Tela de alteração de dados do cartão do cliente
    @RequestMapping("/meuCarrinho")
    public ModelAndView meuCarrinho( @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/carrinho/meuCarrinho");
        
        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        carrinho = carrinhoAtivo(principal, tempCliId, JSESSIONID, response);

        mv.addObject("totalCarrinho", valorTotalCarrinho(carrinho));
        mv.addObject("produtosCarrinho", carrinho.getListProdutos());

        return mv;
    }

    @PostMapping("/incluirNoCarrinho")
    public ModelAndView adicionaNoCarrinho(@RequestParam(name = "id") Integer produtoId, @RequestParam(name = "url") String url, @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal)
    {
        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        //Busca o produtoModel com base no ID selecionado no detalhe do produto
        ProdutosModel produto = produtosRepository.findOneById(produtoId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);
        prdCarrinhoModelNovo.setPcrQuantidade(1);

        carrinho = carrinhoAtivo(principal, tempCliId, JSESSIONID, response);

        prdCarrinhoModelExistente = localizaProduto(carrinho, prdCarrinhoModelNovo);

        if (prdCarrinhoModelExistente != null)
        {
            aumentarProduto(prdCarrinhoModelExistente);
        }
        else
        {
            incluirProduto(carrinho, prdCarrinhoModelNovo);
        }

        return new ModelAndView("redirect:" + url);
    }

    @PostMapping("/aumentarProduto")
    public ModelAndView aumentaProduto(@RequestParam(name = "id") Integer produtoId, @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal)
    {
        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        //Busca o produtoModel com base no ID selecionado no detalhe do produto
        ProdutosModel produto = produtosRepository.findOneById(produtoId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);

        carrinho = carrinhoAtivo(principal, tempCliId, JSESSIONID, response);

        prdCarrinhoModelExistente = localizaProduto(carrinho, prdCarrinhoModelNovo);

        if (prdCarrinhoModelExistente != null)
        {
            aumentarProduto(prdCarrinhoModelExistente);
        }
        else
        {
            incluirProduto(carrinho, prdCarrinhoModelNovo);
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/diminuirProduto")
    public ModelAndView diminuiProduto(@RequestParam(name = "id") Integer produtoId, @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal)
    {
        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        //Busca o produtoModel com base no ID selecionado no detalhe do produto
        ProdutosModel produto = produtosRepository.findOneById(produtoId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);

        carrinho = carrinhoAtivo(principal, tempCliId, JSESSIONID, response);

        prdCarrinhoModelExistente = localizaProduto(carrinho, prdCarrinhoModelNovo);

        if (prdCarrinhoModelExistente != null)
        {
            diminuirProduto(prdCarrinhoModelExistente);
            
            if (prdCarrinhoModelExistente.getPcrQuantidade() < 1)
            {
                removerProduto(prdCarrinhoModelExistente);
            }
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/atualizarProduto")
    public ModelAndView atualizaProduto(@RequestParam(name = "id") Integer produtoId, @RequestParam(name = "qtd") Integer produtoQtd, @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal)
    {
        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        //Busca o produtoModel com base no ID selecionado no detalhe do produto
        ProdutosModel produto = produtosRepository.findOneById(produtoId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);

        carrinho = carrinhoAtivo(principal, tempCliId, JSESSIONID, response);

        prdCarrinhoModelExistente = localizaProduto(carrinho, prdCarrinhoModelNovo);

        if (prdCarrinhoModelExistente != null)
        {
            if (produtoQtd < 1)
            {
                removerProduto(prdCarrinhoModelExistente);
            }
            else
            {
                atualizarQtdProduto(prdCarrinhoModelExistente, produtoQtd);
            }
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/removerProduto")
    public ModelAndView removeProduto(@RequestParam(name = "id") Integer produtoId, @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal)
    {
        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        //Busca o produtoModel com base no ID selecionado no detalhe do produto
        ProdutosModel produto = produtosRepository.findOneById(produtoId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);

        carrinho = carrinhoAtivo(principal, tempCliId, JSESSIONID, response);

        prdCarrinhoModelExistente = localizaProduto(carrinho, prdCarrinhoModelNovo);

        if (prdCarrinhoModelExistente != null)
        {
            removerProduto(prdCarrinhoModelExistente);
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/removerTodosProdutos")
    public ModelAndView removeTodosProdutos(@CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal)
    {
        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        carrinho = carrinhoAtivo(principal, tempCliId, JSESSIONID, response);

        removerTodosProdutos(carrinho);
            
        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }
    
    @GetMapping("/calcularFreteLista/{cep}")
    @ResponseBody
    public List<HashMap<String, String>> calcularFreteLista(@PathVariable(value = "cep") String cep, @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal)
    {
        CarrinhosModel carrinho = new CarrinhosModel();
        String variavel = variaveisRepository.findOneById(1).getVarCep();

        carrinho = carrinhoAtivo(principal, tempCliId, JSESSIONID, response);

        CorreiosUtil calculo = new CorreiosUtil();

        List<HashMap<String, String>> responseFrete = calculo.getValorPrazoLista(variavel.replaceAll("-", ""), cep, carrinho.getListProdutos());

        return responseFrete;
    }
}