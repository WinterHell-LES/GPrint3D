package com.project.GPrint3D.controller.produtos;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.repository.VariaveisRepository;
import com.project.GPrint3D.service.DefaultFacadeService;
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
    private DefaultFacadeService defaultFacadeService;

    @GetMapping("/c/{categoria}/p/{produto}")
    public ModelAndView listagemProduto (@PathVariable(value = "categoria") String categoria,
            @PathVariable(value = "produto") String produto,
            @CookieValue(value = "cliCEP", defaultValue = "") String clienteCEP)
    {
        ModelAndView mv = new ModelAndView("/produtos/detalhesProduto");

        mv.addObject("produto", produtosRepository.findOneByNome(produto.replace("_", " ")));
        mv.addObject("clienteCEP", clienteCEP);

        return mv;
    }

    @PostMapping("/incluirNoCarrinho")
    public ModelAndView incluirNoCarrinho (@RequestParam(name = "id") Integer prdId,
            @RequestParam(name = "url") String url,
            @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId,
            @CookieValue(value = "JSESSIONID", defaultValue = "") String jsessionid, HttpServletResponse response,
            Principal principal, RedirectAttributes attributes)
    {
        CarrinhosModel carrinho = carrinhoAtivo(principal, tempCliId, jsessionid, response);
        ProdutosModel produto = produtosRepository.findOneById(prdId);

        String[] mensagem = defaultFacadeService.incluirNoCarrinho(carrinho, produto, 1);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);        

        return new ModelAndView("redirect:" + url);
    }

    @PostMapping("/incluirCarrinhoComprar")
    public ModelAndView incluirCarrinhoComprar (@RequestParam(name = "prdId") Integer prdId,
            @RequestParam(name = "quantidade") Integer pcrQuantidade,
            @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId,
            @CookieValue(value = "JSESSIONID", defaultValue = "") String jsessionid, HttpServletResponse response,
            Principal principal, RedirectAttributes attributes)
    {
        CarrinhosModel carrinho = carrinhoAtivo(principal, tempCliId, jsessionid, response);
        ProdutosModel produto = produtosRepository.findOneById(prdId);

        String[] mensagem = defaultFacadeService.incluirNoCarrinho(carrinho, produto, pcrQuantidade);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @GetMapping("/validarCEP/{cep}")
    @ResponseBody
    public HashMap<String, String> validarCEP (@PathVariable(value = "cep") String cep)
    {
        CorreiosUtil calculo = new CorreiosUtil();

        return calculo.getValidarCEP(cep);
    }

    @GetMapping("/calcularFrete/{id}/{cep}")
    @ResponseBody
    public List<HashMap<String, String>> calcularFrete (@PathVariable(value = "id") Integer id,
            @PathVariable(value = "cep") String cepDestinatario)
    {
        ProdutosModel produto = produtosRepository.findOneById(id);
        VariaveisModel variaveis = variaveisRepository.findOneById(1);

        String variavelCEP = variaveis.getVarCep();

        CorreiosUtil calculo = new CorreiosUtil();

        return calculo.getValorPrazo(variavelCEP.replace("-", ""), cepDestinatario, produto, variaveis);
    }
}
