package com.project.GPrint3D.controller.carrinho;

import com.project.GPrint3D.util.CarrinhoUtil;
import com.project.GPrint3D.util.CorreiosUtil;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.VariaveisRepository;
import com.project.GPrint3D.service.DefaultFacadeService;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/carrinho")
public class CartController extends CarrinhoUtil
{
    @Autowired
    private VariaveisRepository variaveisRepository;

    @Autowired
    private DefaultFacadeService defaultFacadeService;

    // Tela de alteração de dados do cartão do cliente
    @RequestMapping("/meuCarrinho")
    public ModelAndView meuCarrinho (@CookieValue(value = "tempCliId", defaultValue = "") String tempCliId,
            @CookieValue(value = "JSESSIONID", defaultValue = "") String jsessionid,
            @CookieValue(value = "cliCEP", defaultValue = "") String clienteCEP, HttpServletResponse response,
            Principal principal)
    {
        ModelAndView mv = new ModelAndView("/carrinho/meuCarrinho");

        CarrinhosModel carrinho = carrinhoAtivo(principal, tempCliId, jsessionid, response);
        VariaveisModel variaveis = variaveisRepository.findOneById(1);

        verifPrdCarrinho(carrinho, variaveis.getVarTempCarrinho());

        mv.addObject("carrinho", carrinho);
        mv.addObject("tempCarrinho", variaveis.getVarTempCarrinho());
        mv.addObject("clienteCEP", clienteCEP);

        return mv;
    }

    @PostMapping("/aumentarProduto")
    public ModelAndView aumentaProduto (@RequestParam(name = "id") Integer pcrId, RedirectAttributes attributes)
    {
        String[] mensagem = defaultFacadeService.aumentarProduto(pcrId);
        
        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/diminuirProduto")
    public ModelAndView diminuiProduto (@RequestParam(name = "id") Integer pcrId, RedirectAttributes attributes)
    {
        String[] mensagem = defaultFacadeService.diminuiProduto(pcrId);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/atualizarProduto")
    public ModelAndView atualizaProduto (@RequestParam(name = "id") Integer pcrId,
            @RequestParam(name = "qtd") Integer prdQuantidade, RedirectAttributes attributes)
    {
        String[] mensagem = defaultFacadeService.atualizaProduto(pcrId, prdQuantidade);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/removerProduto")
    public ModelAndView removeProduto (@RequestParam(name = "id") Integer pcrId, RedirectAttributes attributes)
    {
        String[] mensagem = defaultFacadeService.removeProduto(pcrId);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/removerTodosProdutos")
    public ModelAndView removeTodosProdutos (@RequestParam(name = "id") Integer carId,
            RedirectAttributes attributes)
    {
        String[] mensagem = defaultFacadeService.removeTodosProdutos(carId);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/ativarProduto")
    public ModelAndView ativarProduto (@RequestParam(name = "id") Integer pcrId, RedirectAttributes attributes)
    {
        String[] mensagem = defaultFacadeService.removeTodosProdutos(pcrId);

        attributes.addFlashAttribute(mensagem[0], mensagem[1]);

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @GetMapping("/calcularFreteLista/{cep}")
    @ResponseBody
    public List<HashMap<String, String>> calcularFreteLista (@PathVariable(value = "cep") String cep,
            @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId,
            @CookieValue(value = "JSESSIONID", defaultValue = "") String jsessionid, HttpServletResponse response,
            Principal principal)
    {
        CarrinhosModel carrinho = carrinhoAtivo(principal, tempCliId, jsessionid, response);
        VariaveisModel variaveis = variaveisRepository.findOneById(1);
        String variavelCEP = variaveis.getVarCep();

        CorreiosUtil calculo = new CorreiosUtil();

        try
        {
            return calculo.getValorPrazoLista(variavelCEP.replace("-", ""), cep, carrinho.getListProdutosAtivo(),
                    variaveis);
        }
        catch (Exception e)
        {
            return new ArrayList<>();
        }
    }

    private void verifPrdCarrinho (CarrinhosModel carrinho, Integer tempCarrinho)
    {
        List<PrdCarrinhosModel> prdAtivos = carrinho.getListProdutosAtivo();
        List<PrdCarrinhosModel> prdInativos = carrinho.getListProdutosInativo();

        for (PrdCarrinhosModel aux : prdAtivos)
        {
            Date dataCarrinhoP = Date.valueOf(aux.getPcrData().toLocalDate().plusDays(tempCarrinho));
            Date dataAtual = new Date(new java.util.Date().getTime());

            if ((aux.getPcrQuantidade() > aux.getProduto().getPrdQuantidade()) || dataAtual.after(dataCarrinhoP))
            {
                defaultFacadeService.atualizarStatusProduto(aux.getPcrQuantidade(), false, aux.getPcrId());
                aux.setPcrAtivo(false);
            }
        }

        for (PrdCarrinhosModel aux : prdInativos)
        {
            Date dataCarrinhoP = Date.valueOf(aux.getPcrData().toLocalDate().plusDays(tempCarrinho));
            Date dataAtual = new Date(new java.util.Date().getTime());

            if ((aux.getPcrQuantidade() <= aux.getProduto().getPrdQuantidade()) && dataAtual.before(dataCarrinhoP))
            {
                defaultFacadeService.atualizarStatusProduto(aux.getPcrQuantidade(), true, aux.getPcrId());
                aux.setPcrAtivo(true);
            }
        }
    }
}