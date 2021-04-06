package com.project.GPrint3D.controller.carrinho;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CarrinhosRepository;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CarrinhosService;
import com.project.GPrint3D.service.PrdCarrinhosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
public class CartCliController
{
    private int mesclagemCarrinho = 0;

    @Autowired
    private ProdutosRepository produtos;
    
    @Autowired
    private CarrinhosRepository carrinhoRepository;
    
    @Autowired
    private CarrinhosService carrinhoService;

    @Autowired
    private PrdCarrinhosService prdCarrinhosService;

    @Autowired
    private UsuariosRepository usuarios;

    //Tela de alteração de dados do cartão do cliente
    @RequestMapping("/meuCarrinho")
    public ModelAndView meuCarrinho( @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/carrinho/meuCarrinho");

        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        carrinho = carrinhoAtivo (principal, tempCliId, JSESSIONID, response);

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
        ProdutosModel produto = produtos.findOneById(produtoId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);
        prdCarrinhoModelNovo.setPcrQuantidade(1);

        carrinho = carrinhoAtivo (principal, tempCliId, JSESSIONID, response);

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
        ProdutosModel produto = produtos.findOneById(produtoId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);

        carrinho = carrinhoAtivo (principal, tempCliId, JSESSIONID, response);

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
        ProdutosModel produto = produtos.findOneById(produtoId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);

        carrinho = carrinhoAtivo (principal, tempCliId, JSESSIONID, response);

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

    @PostMapping("/removerProduto")
    public ModelAndView removeProduto(@RequestParam(name = "id") Integer produtoId, @CookieValue(value = "tempCliId", defaultValue = "") String tempCliId, @CookieValue(value = "JSESSIONID", defaultValue = "") String JSESSIONID, HttpServletResponse response, Principal principal)
    {
        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        //Busca o produtoModel com base no ID selecionado no detalhe do produto
        ProdutosModel produto = produtos.findOneById(produtoId);

        //Cria um Produto de Carrinho Model para definir a quantidade e poder registrar no carrinho
        PrdCarrinhosModel prdCarrinhoModelNovo = new PrdCarrinhosModel();
        PrdCarrinhosModel prdCarrinhoModelExistente = new PrdCarrinhosModel();

        prdCarrinhoModelNovo.setProduto(produto);

        carrinho = carrinhoAtivo (principal, tempCliId, JSESSIONID, response);

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

        carrinho = carrinhoAtivo (principal, tempCliId, JSESSIONID, response);

        removerTodosProdutos(carrinho);
            
        return new ModelAndView("redirect:/carrinho/meuCarrinho");
    }

    private boolean estaLogado(Principal principal)
    {
        if (principal != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean temCookieId(String valorCookie)
    {
        if (valorCookie.equals(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private void registrarCookie(String nomeCookie, String valorCookie, HttpServletResponse response)
    {
        Cookie cookie = new Cookie(nomeCookie, valorCookie);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    private PrdCarrinhosModel localizaProduto(CarrinhosModel carrinho, PrdCarrinhosModel prdCarrinhoModel)
    {
        /**
         * Recebe o carrinho, o produto carrinho model e atualiza no banco se já houver esse produto,
         * em caso negativo ele inclui uma nova entrada do produto para o carrinho selecionado.
         */

        if (carrinho.getListProdutos() == null)
        {
            return null;
        }

        // Cria lista de referência
        List<PrdCarrinhosModel> carrinhoProdutos = new ArrayList<PrdCarrinhosModel>();

        //Recebe a listagem dos produtos do carrinho e valida
        carrinho = validarListaProdutosCarrinho(carrinho);
        carrinhoProdutos = carrinho.getListProdutos();

        //Tenta atualizar o produto na listagem, se conseguir finalizar o método.
        for(int i = 0; i < carrinhoProdutos.size(); i++)
        {
            if (carrinhoProdutos.get(i).getProduto().getPrdId() ==  prdCarrinhoModel.getProduto().getPrdId())
            {
                //Caso possua o item especificado no carrinho, adiciona uma unidade do item ao invés de criar um novo item
                PrdCarrinhosModel prdCarPrdModel = carrinhoProdutos.get(i);

                return prdCarPrdModel;
            }
        }

        return null;
    }

    private void aumentarProduto(PrdCarrinhosModel prdCarrinhoModel)
    {
        int CarProdQnt = prdCarrinhoModel.getPcrQuantidade();

        prdCarrinhoModel.setPcrQuantidade(CarProdQnt + 1);
        prdCarrinhoModel.setPcrDate(getDate());

        prdCarrinhosService.atualizar(prdCarrinhoModel);
    }

    private void diminuirProduto(PrdCarrinhosModel prdCarrinhoModel)
    {
        int CarProdQnt = prdCarrinhoModel.getPcrQuantidade();

        prdCarrinhoModel.setPcrQuantidade(CarProdQnt - 1);
        prdCarrinhoModel.setPcrDate(getDate());
        
        prdCarrinhosService.atualizar(prdCarrinhoModel);
    }

    private void incluirProduto(CarrinhosModel carrinho, PrdCarrinhosModel prdCarrinhoModel)
    {
        prdCarrinhoModel.setCarrinho(carrinho);
        prdCarrinhoModel.setPcrQuantidade(1);
        prdCarrinhoModel.setPcrAtivo(true);
        prdCarrinhoModel.setPcrDate(getDate());

        prdCarrinhosService.cadastrar(prdCarrinhoModel);
    }

    private void removerProduto(PrdCarrinhosModel prdCarrinhoModel)
    {
        prdCarrinhosService.excluir(prdCarrinhoModel.getPcrId());
    }

    private void removerTodosProdutos(CarrinhosModel carrinho)
    {
        List<PrdCarrinhosModel> carrinhoProdutos = new ArrayList<PrdCarrinhosModel>();

        //Recebe a listagem dos produtos do carrinho e valida
        carrinho = validarListaProdutosCarrinho(carrinho);
        carrinhoProdutos = carrinho.getListProdutos();

        //Tenta atualizar o produto na listagem, se conseguir finalizar o método.
        for(int i = 0; i < carrinhoProdutos.size(); i++)
        {
            prdCarrinhosService.excluir(carrinhoProdutos.get(i).getPcrId());
        }
    }

    private void criarCarrinho(UsuariosModel usuario, String valorCookie)
    {
        CarrinhosModel carrinho = new CarrinhosModel();
        //Fornecer ou um parâmetro ou o outro, nunca os dois.
        if (usuario != null)
        {
            carrinho.setCliente(usuario.getCliente());
        }
        else
        {
            carrinho.setTemporaryCliId(valorCookie);
        }

        carrinho = validarListaProdutosCarrinho(carrinho);

        carrinhoService.cadastrar(carrinho);
    }

    private CarrinhosModel carrinhoAtivo (Principal principal, String valorCookie, String JSESSIONID, HttpServletResponse response)
    {
        /**
         * Busca o carrinho ativo pelo usuario logado e em caso negativo busca pelo cookie do cliente
         * e em caso negativo cria um novo carrinho e atribui o cookie do cliente no carrinho e no browser
         */

        //Instancia um novo carrinho
        CarrinhosModel carrinho = new CarrinhosModel();

        if (estaLogado(principal))
        {
            //Define o usuário pelo principal
            UsuariosModel usuario = usuarios.findByEmail(principal.getName());
            
            if (usuario.getUsuRegra().equals("ROLE_CLI"))
            {
                if (temCookieId(valorCookie))
                {
                    //Recupera o carrinho do cookie e verifica no BD
                    carrinho = mesclarCarrinhos(localizarCarrinho(usuario, ""), localizarCarrinho(null, valorCookie));

                    for (int i = 0; i < carrinho.getListProdutos().size(); i++)
                    {
                        System.out.println("Id do produto no carrinho mesclado: " + carrinho.getListProdutos().get(i).getPcrId());
                    }

                    return carrinho;
                }
                //Verifica se o cliente possui algum carrinho
                carrinho = localizarCarrinho(usuario, "");

                return carrinho;
            }
        }
        
        if (temCookieId(valorCookie))
        {
            //Recupera o carrinho do cookie e verifica no BD
            carrinho = localizarCarrinho(null, valorCookie);
        }
        else
        {
            registrarCookie("tempCliId", JSESSIONID, response);

            criarCarrinho(null, JSESSIONID);

            carrinho = carrinhoRepository.findByClienteTempId(JSESSIONID);
        }

        return carrinho;
    }

    private java.sql.Date getDate()
    {
        long ms = System.currentTimeMillis();  
        java.sql.Date date = new java.sql.Date(ms);

        return date;
    }

    private double valorTotalCarrinho (CarrinhosModel carrinho)
    {
        Double total = 0D;

        // Cria lista de referência
        List<PrdCarrinhosModel> carrinhoProdutos = new ArrayList<PrdCarrinhosModel>();

        //Recebe a listagem dos produtos do carrinho e valida
        carrinho = validarListaProdutosCarrinho(carrinho);
        carrinhoProdutos = carrinho.getListProdutos();

        //Tenta atualizar o produto na listagem, se conseguir finalizar o método.
        for(int i = 0; i < carrinhoProdutos.size(); i++)
        {
            total += carrinhoProdutos.get(i).getPcrQuantidade() * carrinhoProdutos.get(i).getProduto().getPrdPreco();
        }

        return total;
    }

    private CarrinhosModel localizarCarrinho(UsuariosModel usuario, String valorCookie)
    {
        carrinhoRepository.flush();

        CarrinhosModel carrinho = new CarrinhosModel();

        if (usuario != null)
        {
            carrinho = carrinhoRepository.findByClienteId(usuario.getCliente().getCliId());
                
            if (carrinho == null)
            {
                criarCarrinho(usuario, "");

                carrinho = carrinhoRepository.findByClienteId(usuario.getCliente().getCliId());
            }
        }

        if (valorCookie != "")
        {
            carrinho = carrinhoRepository.findByClienteTempId(valorCookie);

            if (carrinho == null)
            {
                criarCarrinho(null, valorCookie);

                carrinho = carrinhoRepository.findByClienteTempId(valorCookie);
            }
        }

        carrinho = validarListaProdutosCarrinho(carrinho);

        return carrinho;
    }

    private CarrinhosModel validarListaProdutosCarrinho(CarrinhosModel carrinho)
    {
        if (carrinho.getListProdutos() == null)
        {
            List<PrdCarrinhosModel> carrinhoProdutos = new ArrayList<PrdCarrinhosModel>();
            carrinho.setListProdutos(carrinhoProdutos);
        }

        return carrinho;
    }

    private CarrinhosModel mesclarCarrinhos (CarrinhosModel carrinhoLogado, CarrinhosModel carrinhoCookie)
    {
        System.out.println("Carrinho já mesclado nessa sessão? " + mesclagemCarrinho);

        for (PrdCarrinhosModel produto : carrinhoCookie.getListProdutos())
        {
            produto.setCarrinho(carrinhoLogado);
            prdCarrinhosService.atualizar(produto);
        }

        carrinhoLogado.setListProdutos(excluirProdutosDuplicados(carrinhoLogado.getListProdutos()));

        return carrinhoLogado;
    }

    private List<PrdCarrinhosModel> excluirProdutosDuplicados(List<PrdCarrinhosModel> carrinhoProdutos)
    {
        List<Integer> idsUnico = new ArrayList<Integer>();
        List<PrdCarrinhosModel> carrinhoProdutoAux = new ArrayList<PrdCarrinhosModel>();

        carrinhoProdutoAux.addAll(carrinhoProdutos);

        for (PrdCarrinhosModel produto : carrinhoProdutos)
        {
            if (idsUnico.contains(produto.getProduto().getPrdId()))
            {
                carrinhoProdutoAux.remove(produto);
                prdCarrinhosService.excluir(produto.getPcrId());
            }
            else
            {
                idsUnico.add(produto.getProduto().getPrdId());
            }
        }

        return carrinhoProdutoAux;
    }
}