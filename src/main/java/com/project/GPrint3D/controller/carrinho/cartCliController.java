package com.project.GPrint3D.controller.carrinho;

import java.util.ArrayList;
import java.util.List;

import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.repository.ProdutosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
public class CartCliController
{   
    private List<PrdCarrinhosModel> carrinhoProdutos = new ArrayList<>();
    
    @Autowired
    private ProdutosRepository produtos;

    //Tela de alteração de dados do cartão do cliente
    @RequestMapping("/meuCarrinho")
    public ModelAndView meuCarrinho()
    {
        ModelAndView mv = new ModelAndView("/carrinho/meuCarrinho");

        mv.addObject("produtosCarrinho", carrinhoProdutos);

        return mv;
    }

    @PostMapping("/incluirNoCarrinho")
    public ModelAndView adicionarNoCarrinho(@RequestParam(name = "id") String produtoId, @RequestParam(name = "url") String url)
    {
        ProdutosModel produto = produtos.findOneById(Integer.parseInt(produtoId));
        PrdCarrinhosModel prdCarrinhoModel = new PrdCarrinhosModel();

        prdCarrinhoModel.setProduto(produto);
        prdCarrinhoModel.setPcrQuantidade(1);

        for(int i = 0; i < carrinhoProdutos.size(); i++)
        {
            if (carrinhoProdutos.get(i).getProduto().getPrdId() ==  prdCarrinhoModel.getProduto().getPrdId())
            {
                PrdCarrinhosModel prdCarPrdModel = carrinhoProdutos.get(i);
                Integer CarProdQnt = prdCarPrdModel.getPcrQuantidade();

                prdCarPrdModel.setPcrQuantidade(CarProdQnt + 1);

                return new ModelAndView("redirect:" + url);
            }
        }
        
        carrinhoProdutos.add(prdCarrinhoModel);

        return new ModelAndView("redirect:" + url);
    }

    @PostMapping("/aumentarProduto")
    public ModelAndView aumentarProduto(@RequestParam(name = "id") Integer produtoId)
    {
        for(int i = 0; i < carrinhoProdutos.size(); i++)
        {
            if (carrinhoProdutos.get(i).getProduto().getPrdId() ==  produtoId)
            {
                PrdCarrinhosModel prdCarPrdModel = carrinhoProdutos.get(i);
                Integer CarProdQnt = prdCarPrdModel.getPcrQuantidade();

                prdCarPrdModel.setPcrQuantidade(CarProdQnt + 1);

                return new ModelAndView("redirect:/carrinho/meuCarrinho");
            }
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/diminuirProduto")
    public ModelAndView diminuirProduto(@RequestParam(name = "id") String produtoId)
    {
        for(int i = 0; i < carrinhoProdutos.size(); i++)
        {
            if (carrinhoProdutos.get(i).getProduto().getPrdId() ==  Integer.parseInt(produtoId))
            {
                PrdCarrinhosModel prdCarPrdModel = carrinhoProdutos.get(i);
                Integer CarProdQnt = prdCarPrdModel.getPcrQuantidade();

                prdCarPrdModel.setPcrQuantidade(CarProdQnt - 1);

                if (prdCarPrdModel.getPcrQuantidade() < 1)
                {
                    carrinhoProdutos.remove(prdCarPrdModel);
                }

                return new ModelAndView("redirect:/carrinho/meuCarrinho");
            }
        }

        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/removerProduto")
    public ModelAndView removerProduto(@RequestParam(name = "id") String produtoId)
    {
        for(int i = 0; i < carrinhoProdutos.size(); i++)
        {
            if (carrinhoProdutos.get(i).getProduto().getPrdId() ==  Integer.parseInt(produtoId))
            {
                PrdCarrinhosModel prdCarPrdModel = carrinhoProdutos.get(i);
                carrinhoProdutos.remove(prdCarPrdModel);

                return new ModelAndView("redirect:/carrinho/meuCarrinho");
            }
        }
            
        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    @PostMapping("/removerProdutos")
    public ModelAndView removerProdutos()
    {
        carrinhoProdutos.removeAll(carrinhoProdutos);
            
        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

}
