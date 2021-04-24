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
import com.project.GPrint3D.repository.CarrinhosRepository;
import com.project.GPrint3D.repository.PrdCarrinhosRepository;
import com.project.GPrint3D.repository.VariaveisRepository;
import com.project.GPrint3D.service.PrdCarrinhosService;

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
    private PrdCarrinhosRepository prdCarrinhosRepository;

    @Autowired
    private CarrinhosRepository carrinhosRepository;

    @Autowired
    private VariaveisRepository variaveisRepository;

    @Autowired
    private PrdCarrinhosService prdCarrinhosService;

    // Tela de alteração de dados do cartão do cliente
    @RequestMapping("/meuCarrinho")
    public ModelAndView meuCarrinho (@CookieValue(value = "tempCliId", defaultValue = "") String tempCliId,
            @CookieValue(value = "JSESSIONID", defaultValue = "") String jsessionid,
            @CookieValue(value = "cliCEP", defaultValue = "") String clienteCEP, HttpServletResponse response,
            Principal principal)
    {
        ModelAndView mv = new ModelAndView("/carrinho/meuCarrinho");

        // Instancia um novo carrinho
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
        PrdCarrinhosModel prdCarrinho = prdCarrinhosRepository.findOneById(pcrId);

        prdCarrinho.aumentarProduto();

        if (prdCarrinho.getPcrQuantidade() <= prdCarrinho.getProduto().getPrdQuantidade())
        {
            String[] mensagem = prdCarrinhosService.atualizar(prdCarrinho);

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

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/diminuirProduto")
    public ModelAndView diminuiProduto (@RequestParam(name = "id") Integer pcrId, RedirectAttributes attributes)
    {
        PrdCarrinhosModel prdCarrinho = prdCarrinhosRepository.findOneById(pcrId);
        String[] mensagem;

        prdCarrinho.diminuirProduto();

        if (prdCarrinho.getPcrQuantidade() > 0)
        {
            mensagem = prdCarrinhosService.atualizar(prdCarrinho);
        }
        else
        {
            mensagem = prdCarrinhosService.excluir(pcrId);
        }

        if (mensagem[0].equals("alteracaoSuccess") || mensagem[0].equals("deleteSuccess"))
        {
            attributes.addFlashAttribute("success", "Produto removido com sucesso");
        }
        else
        {
            attributes.addFlashAttribute("error", "Erro ao remover o produto");
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/atualizarProduto")
    public ModelAndView atualizaProduto (@RequestParam(name = "id") Integer pcrId,
            @RequestParam(name = "qtd") Integer prdQuantidade, RedirectAttributes attributes)
    {
        PrdCarrinhosModel prdCarrinho = prdCarrinhosRepository.findOneById(pcrId);

        prdCarrinho.atualizarQtdProduto(prdQuantidade);

        if (prdQuantidade > 0)
        {
            if (prdQuantidade <= prdCarrinho.getProduto().getPrdQuantidade())
            {
                String[] mensagem = prdCarrinhosService.atualizar(prdCarrinho);

                if (mensagem[0].equals("alteracaoSuccess"))
                {
                    attributes.addFlashAttribute("success", "Quantidade do produto alterada com sucesso");
                }
                else
                {
                    attributes.addFlashAttribute("error", "Erro ao alterar a quantidade do produto");
                }
            }
            else
            {
                attributes.addFlashAttribute("error", "Quantidade de estoque atingida");
            }
        }
        else
        {
            String[] mensagem = prdCarrinhosService.excluir(pcrId);

            if (mensagem[0].equals("deleteSuccess"))
            {
                attributes.addFlashAttribute("success", "Produto removido com sucesso");
            }
            else
            {
                attributes.addFlashAttribute("error", "Erro ao remover o produto");
            }
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/removerProduto")
    public ModelAndView removeProduto (@RequestParam(name = "id") Integer produtoId, RedirectAttributes attributes)
    {
        String[] mensagem = prdCarrinhosService.excluir(produtoId);

        if (mensagem[0].equals("deleteSuccess"))
        {
            attributes.addFlashAttribute("success", "Produto removido com sucesso");
        }
        else
        {
            attributes.addFlashAttribute("error", "Erro ao remover o produto");
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/removerTodosProdutos")
    public ModelAndView removeTodosProdutos (@RequestParam(name = "id") Integer carrinhoId,
            RedirectAttributes attributes)
    {
        CarrinhosModel carrinho = carrinhosRepository.findOneById(carrinhoId);
        String[] mensagem = new String[2];

        for (PrdCarrinhosModel aux : carrinho.getListProdutos())
        {
            mensagem = prdCarrinhosService.excluir(aux.getPcrId());

            if (!mensagem[0].equals("deleteSuccess"))
            {
                break;
            }
        }

        if (mensagem[0].equals("deleteSuccess"))
        {
            attributes.addFlashAttribute("success", "Todos os produtos removidos com sucesso");
        }
        else
        {
            attributes.addFlashAttribute("error", "Erro ao remover todos os produtos o produto");
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/ativarProduto")
    public ModelAndView ativarProduto (@RequestParam(name = "id") Integer produtoId, RedirectAttributes attributes)
    {
        PrdCarrinhosModel prdCarrinho = prdCarrinhosRepository.findOneById(produtoId);
        String[] mensagem;

        if (prdCarrinho.getPcrQuantidade() <= prdCarrinho.getProduto().getPrdQuantidade())
        {
            mensagem = prdCarrinhosService.atualizarStatusPrdCarrinhos(prdCarrinho.getPcrQuantidade(), true, produtoId);
            mensagem[1] = "Produto reativado com sucesso";
        }
        else
        {
            mensagem = prdCarrinhosService.atualizarStatusPrdCarrinhos(prdCarrinho.getProduto().getPrdQuantidade(),
                    true, produtoId);
            mensagem[1] = "Produto reativado com sucesso, e ajustado para a quantidade do estoque";
        }

        if (mensagem[0].equals("alteracaoSuccess"))
        {
            attributes.addFlashAttribute("success", mensagem[1]);
        }
        else
        {
            attributes.addFlashAttribute("error", "Erro ao reativar o produto");
        }

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
            return calculo.getValorPrazoLista(variavelCEP.replace("-", ""),
                    cep, carrinho.getListProdutosAtivo(), variaveis);
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
                prdCarrinhosService.atualizarStatusPrdCarrinhos(aux.getPcrQuantidade(), false, aux.getPcrId());
            }
        }

        for (PrdCarrinhosModel aux : prdInativos)
        {
            Date dataCarrinhoP = Date.valueOf(aux.getPcrData().toLocalDate().plusDays(tempCarrinho));
            Date dataAtual = new Date(new java.util.Date().getTime());

            if ((aux.getPcrQuantidade() <= aux.getProduto().getPrdQuantidade()) && dataAtual.before(dataCarrinhoP))
            {
                prdCarrinhosService.atualizarStatusPrdCarrinhos(aux.getPcrQuantidade(), false, aux.getPcrId());
            }
        }
    }
}