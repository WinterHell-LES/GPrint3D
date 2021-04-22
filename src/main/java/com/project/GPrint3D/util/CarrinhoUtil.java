package com.project.GPrint3D.util;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.CategoriasProdutosModel;
import com.project.GPrint3D.model.CuponsPromocoesModel;
import com.project.GPrint3D.model.CuponsTrocasModel;
import com.project.GPrint3D.model.PedCartoesModel;
import com.project.GPrint3D.model.PedProdutosModel;
import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CarrinhosRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.CarrinhosService;
import com.project.GPrint3D.service.PedProdutosService;
import com.project.GPrint3D.service.PrdCarrinhosService;

import org.springframework.beans.factory.annotation.Autowired;

public class CarrinhoUtil
{
    @Autowired
    private CarrinhosRepository carrinhoRepository;
    
    @Autowired
    private CarrinhosService carrinhoService;

    @Autowired
    private PrdCarrinhosService prdCarrinhosService;

    @Autowired
    private PedProdutosService pedProdutosService;

    @Autowired
    private UsuariosRepository usuarios;

    public boolean estaLogado(Principal principal)
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

    public boolean temCookieId(String valorCookie)
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

    public void registrarCookie(String nomeCookie, String valorCookie, HttpServletResponse response)
    {
        Cookie cookie = new Cookie(nomeCookie, valorCookie);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public PrdCarrinhosModel localizaProduto(CarrinhosModel carrinho, PrdCarrinhosModel prdCarrinhoModel)
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

    public void criarCarrinho(UsuariosModel usuario, String valorCookie)
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

    public CarrinhosModel carrinhoAtivo(Principal principal, String valorCookie, String JSESSIONID, HttpServletResponse response)
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

    public CarrinhosModel localizarCarrinho(UsuariosModel usuario, String valorCookie)
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

    public CarrinhosModel validarListaProdutosCarrinho(CarrinhosModel carrinho)
    {
        if (carrinho.getListProdutos() == null)
        {
            List<PrdCarrinhosModel> carrinhoProdutos = new ArrayList<PrdCarrinhosModel>();
            carrinho.setListProdutos(carrinhoProdutos);
        }

        return carrinho;
    }

    public CarrinhosModel mesclarCarrinhos(CarrinhosModel carrinhoLogado, CarrinhosModel carrinhoCookie)
    {
        for (PrdCarrinhosModel produto : carrinhoCookie.getListProdutos())
        {
            produto.setCarrinho(carrinhoLogado);
            prdCarrinhosService.atualizar(produto);
        }

        carrinhoLogado.setListProdutos(excluirProdutosDuplicados(carrinhoLogado.getListProdutos()));

        return carrinhoLogado;
    }

    public List<PrdCarrinhosModel> excluirProdutosDuplicados(List<PrdCarrinhosModel> carrinhoProdutos)
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

    public double valorTotalCartoes(List<PedCartoesModel> listaPedCartoes)
    {
        Double total = 0D;

        if (listaPedCartoes.size() == 0 || listaPedCartoes == null)
        {
            return total;
        }

        for (PedCartoesModel pedCartoes : listaPedCartoes)
        {
            BigDecimal totalBD = BigDecimal.valueOf(total).add(BigDecimal.valueOf(pedCartoes.getPctValor()));
            total = totalBD.doubleValue();
        }

        return total;
    }

    public boolean converterProdutosCarrinhoParaPedidos(CarrinhosModel carrinho, PedidosComprasModel pedidoCompra)
    {
        List<PrdCarrinhosModel> listaProdutosCarrinho = carrinho.getListProdutos();

        if (listaProdutosCarrinho == null || pedidoCompra == null)
        {
            return false;
        }
        
        for (PrdCarrinhosModel carProduto : listaProdutosCarrinho)
        {
            PedProdutosModel pedProduto = new PedProdutosModel();

            pedProduto.setProduto(carProduto.getProduto());
            pedProduto.setPpdQuantidade(carProduto.getPcrQuantidade());
            pedProduto.setPedidoCompra(pedidoCompra);

            pedProdutosService.cadastrar(pedProduto);
        }

        return true;
    }

    public double valorTotalCupons(List<CuponsTrocasModel> listaCuponsTroca)
    {
        Double total = 0D;

        if (listaCuponsTroca.size() == 0 || listaCuponsTroca == null)
        {
            return total;
        }

        for (CuponsTrocasModel cupomTroca : listaCuponsTroca)
        {
            BigDecimal totalBD = BigDecimal.valueOf(total).add(BigDecimal.valueOf(cupomTroca.getCptSaldo()));
            total = totalBD.doubleValue();
        }

        return total;
    }

    public double valorDescontoCupomPromocional(Integer carrinhoId, CuponsPromocoesModel cupomPromocao)
    {
        Double total = 0D;

        CarrinhosModel carrinho = carrinhoRepository.findOneById(carrinhoId);

        for (PrdCarrinhosModel prdCarrinho : carrinho.getListProdutos())
        {

           for (CategoriasProdutosModel categoria : prdCarrinho.getProduto().getListCategoriasProdutos())
           {
                if (categoria.getCategoria() == cupomPromocao.getCategoria())
                {
                    BigDecimal totalBD = BigDecimal.valueOf(total).add((BigDecimal.valueOf(prdCarrinho.getProduto().getPrdPreco())).multiply(BigDecimal.valueOf(prdCarrinho.getPcrQuantidade())));
                    total = totalBD.doubleValue();
                }
           }
        }

        BigDecimal totalBD = BigDecimal.valueOf(total).multiply((BigDecimal.valueOf(cupomPromocao.getCppDesconto()).divide(new BigDecimal("100"))));
        total = totalBD.doubleValue();

        return total;
    }

    public void validarCupons(List<CuponsTrocasModel> listaCuponsTrocaDisponiveis, List<CuponsTrocasModel> listaCuponsTrocaUtilizados, Double valorPendente)
    {
        for (CuponsTrocasModel cupom : listaCuponsTrocaUtilizados)
        {
            if (valorPendente < 0 && cupom.getCptValor() < Math.abs(valorPendente))
            {
                listaCuponsTrocaUtilizados.remove(cupom);
                listaCuponsTrocaDisponiveis.add(cupom);

                //Necessário o formato recursivo com o break, pois é removido um item da própria iteração.
                validarCupons(listaCuponsTrocaDisponiveis, listaCuponsTrocaUtilizados, valorPendente);
                break;
            }
        }

        return;
    }

}