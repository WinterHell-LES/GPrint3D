package com.project.GPrint3D.controller.produtos;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.repository.VariaveisRepository;
import com.project.GPrint3D.service.PrdCarrinhosService;
import com.project.GPrint3D.util.CarrinhoUtil;
import com.project.GPrint3D.util.CorreiosUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProdutoController extends CarrinhoUtil
{
    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private VariaveisRepository variaveisRepository;

    @Autowired
    private PrdCarrinhosService prdCarrinhosService;

    @GetMapping("/c/{categoria}/p/{produto}")
    public ModelAndView listagemProduto(@PathVariable(value = "categoria") String categoria, @PathVariable(value = "produto") String produto, @CookieValue(value = "cliCEP", defaultValue = "") String clienteCEP)
    {
        ModelAndView mv = new ModelAndView("/produtos/detalhesProduto");

        mv.addObject("produto", produtosRepository.findOneByNome(produto.replaceAll("_", " ")));
        mv.addObject("clienteCEP", clienteCEP);

        return mv;
    }

    @PostMapping("/incluirNoCarrinho")
    public ModelAndView incluirNoCarrinho(@RequestParam(name = "id") Integer prdId, @RequestParam(name = "url") String url, @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal, RedirectAttributes attributes)
    {
        String[] mensagem = new String[2];

        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        //Busca o produtoModel com base no ID selecionado no detalhe do produto
        ProdutosModel produto = produtosRepository.findOneById(prdId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);

        carrinho = carrinhoAtivo(principal, tempCliId, JSESSIONID, response);

        prdCarrinhoModelExistente = localizaProduto(carrinho, prdCarrinhoModelNovo);

        if (prdCarrinhoModelExistente != null)
        {
            prdCarrinhoModelExistente.aumentarProduto();

            if (prdCarrinhoModelExistente.getPcrQuantidade() <= prdCarrinhoModelExistente.getProduto().getPrdQuantidade())
            {
                mensagem = prdCarrinhosService.atualizar(prdCarrinhoModelExistente);

                if (mensagem[0].equals("alteracaoSuccess"))
                {
                    attributes.addFlashAttribute("success", "Produto adicionado com sucesso");
                }
                else
                {
                    attributes.addFlashAttribute("error", "Erro ao adicionar o produto");
                }
            }
            else
            {
                attributes.addFlashAttribute("error", "Quantidade de estoque atingida");
            }
        }
        else
        {
            prdCarrinhoModelNovo.setCarrinho(carrinho);
            
            mensagem = prdCarrinhosService.cadastrar(prdCarrinhoModelNovo);

            if (mensagem[0].equals("cadastroSuccess"))
            {
                attributes.addFlashAttribute("success", "Produto adicionado com sucesso");
            }
            else
            {
                attributes.addFlashAttribute("error", "Erro ao adicionar o produto");
            }
        }

        return new ModelAndView("redirect:" + url);
    }

    @PostMapping("/incluirCarrinhoComprar")
    public ModelAndView incluirCarrinhoComprar(@RequestParam(name = "prdId") Integer prdId, @RequestParam(name = "quantidade") Integer pcrQuantidade, @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal, RedirectAttributes attributes)
    {
        String[] mensagem = new String[2];

        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        //Busca o produtoModel com base no ID selecionado no detalhe do produto
        ProdutosModel produto = produtosRepository.findOneById(prdId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);
        prdCarrinhoModelNovo.setPcrQuantidade(pcrQuantidade);

        carrinho = carrinhoAtivo(principal, tempCliId, JSESSIONID, response);

        prdCarrinhoModelExistente = localizaProduto(carrinho, prdCarrinhoModelNovo);

        if (prdCarrinhoModelExistente != null)
        {
            prdCarrinhoModelExistente.atualizarQtdProduto(prdCarrinhoModelExistente.getPcrQuantidade() + pcrQuantidade);

            if (prdCarrinhoModelExistente.getPcrQuantidade() <= prdCarrinhoModelExistente.getProduto().getPrdQuantidade())
            {
                mensagem = prdCarrinhosService.atualizar(prdCarrinhoModelExistente);

                if (mensagem[0].equals("alteracaoSuccess"))
                {
                    attributes.addFlashAttribute("success", "Produto adicionado com sucesso");
                }
                else
                {
                    attributes.addFlashAttribute("error", "Erro ao adicionar o produto");
                }
            }
            else
            {
                attributes.addFlashAttribute("error", "Quantidade de estoque atingida");
            }
        }
        else
        {
            prdCarrinhoModelNovo.setCarrinho(carrinho);
            
            mensagem = prdCarrinhosService.cadastrar(prdCarrinhoModelNovo);

            if (mensagem[0].equals("cadastroSuccess"))
            {
                attributes.addFlashAttribute("success", "Produto adicionado com sucesso");
            }
            else
            {
                attributes.addFlashAttribute("error", "Erro ao adicionar o produto");
            }
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

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
        ProdutosModel produto = produtosRepository.findOneById(id);
        VariaveisModel variaveis = variaveisRepository.findOneById(1);
        
        String variavelCEP = variaveis.getVarCep();

        CorreiosUtil calculo = new CorreiosUtil();

        List<HashMap<String, String>> response = calculo.getValorPrazo(variavelCEP.replaceAll("-", ""), cepDestinatario, produto, variaveis);

        return response;
    }
}
