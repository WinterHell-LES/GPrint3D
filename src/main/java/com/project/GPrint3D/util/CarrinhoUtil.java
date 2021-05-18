package com.project.GPrint3D.util;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.CategoriasProdutosModel;
import com.project.GPrint3D.model.CuponsTrocasModel;
import com.project.GPrint3D.model.PedProdutosModel;
import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CarrinhosRepository;
import com.project.GPrint3D.repository.CategoriasProdutosRepository;
import com.project.GPrint3D.repository.PedidosComprasRepository;
import com.project.GPrint3D.repository.PrdCarrinhosRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.service.DefaultFacadeService;

import org.springframework.beans.factory.annotation.Autowired;

public class CarrinhoUtil
{
    @Autowired
    private CarrinhosRepository carrinhoRepository;

    @Autowired
    private CategoriasProdutosRepository categoriasProdutosRepository;

    @Autowired
    private PedidosComprasRepository pedidosComprasRepository;

    @Autowired
    private PrdCarrinhosRepository prdCarrinhosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private DefaultFacadeService defaultFacadeService;

    public void registrarCookie (String nomeCookie, String valorCookie, HttpServletResponse response)
    {
        Cookie cookie = new Cookie(nomeCookie, valorCookie);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public PrdCarrinhosModel localizaProduto (CarrinhosModel carrinho, PrdCarrinhosModel prdCarrinhoModel)
    {
        /**
         * Recebe o carrinho, o produto carrinho model e atualiza no banco se já houver
         * esse produto, em caso negativo ele inclui uma nova entrada do produto para o
         * carrinho selecionado.
         */

        if (carrinho.getListProdutos() == null)
        {
            return null;
        }

        // Cria lista de referência
        List<PrdCarrinhosModel> carrinhoProdutos;

        // Recebe a listagem dos produtos do carrinho e valida
        carrinho = validarListaProdutosCarrinho(carrinho);
        carrinhoProdutos = carrinho.getListProdutos();

        // Tenta atualizar o produto na listagem, se conseguir finalizar o método.
        for (int i = 0 ; i < carrinhoProdutos.size() ; i++)
        {
            if (carrinhoProdutos.get(i).getProduto().getPrdId() == prdCarrinhoModel.getProduto().getPrdId())
            {
                // Caso possua o item especificado no carrinho, adiciona uma unidade do item ao
                // invés de criar um novo item
                return carrinhoProdutos.get(i);
            }
        }

        return null;
    }

    public void criarCarrinho (UsuariosModel usuario, String valorCookie)
    {
        CarrinhosModel carrinho = new CarrinhosModel();

        if (usuario != null)
        {
            carrinho.setCliente(usuario.getCliente());
        }
        else
        {
            carrinho.setTemporaryCliId(valorCookie);
        }

        carrinho = validarListaProdutosCarrinho(carrinho);

        defaultFacadeService.cadastrarCarrinho(carrinho);
    }

    public CarrinhosModel carrinhoAtivo (Principal principal, String valorCookie, String jsessionid,
            HttpServletResponse response)
    {
        /**
         * Busca o carrinho ativo pelo usuario logado e em caso negativo busca pelo
         * cookie do cliente e em caso negativo cria um novo carrinho e atribui o cookie
         * do cliente no carrinho e no browser
         */

        // Instancia um novo carrinho
        CarrinhosModel carrinho;

        if (principal != null)
        {
            // Define o usuário pelo principal
            UsuariosModel usuario = usuariosRepository.findByEmail(principal.getName());

            if (usuario.getUsuRegra().equals("ROLE_CLI"))
            {
                if (!valorCookie.equals(""))
                {
                    // Recupera o carrinho do cookie e verifica no BD
                    carrinho = mesclarCarrinhos(localizarCarrinho(usuario, ""), localizarCarrinho(null, valorCookie));

                    return carrinho;
                }
                // Verifica se o cliente possui algum carrinho
                carrinho = localizarCarrinho(usuario, "");

                return carrinho;
            }
        }

        if (!valorCookie.equals(""))
        {
            // Recupera o carrinho do cookie e verifica no BD
            carrinho = localizarCarrinho(null, valorCookie);
        }
        else
        {
            registrarCookie("tempCliId", jsessionid, response);

            criarCarrinho(null, jsessionid);

            carrinho = carrinhoRepository.findByClienteTempId(jsessionid);
        }

        return carrinho;
    }

    public CarrinhosModel localizarCarrinho (UsuariosModel usuario, String valorCookie)
    {
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

        if (!valorCookie.equals(""))
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

    public CarrinhosModel validarListaProdutosCarrinho (CarrinhosModel carrinho)
    {
        if (carrinho.getListProdutos() == null)
        {
            carrinho.setListProdutos(new ArrayList<>());
        }

        return carrinho;
    }

    public CarrinhosModel mesclarCarrinhos (CarrinhosModel carrinhoLogado, CarrinhosModel carrinhoCookie)
    {
        for (PrdCarrinhosModel produto : carrinhoCookie.getListProdutos())
        {
            produto.setCarrinho(carrinhoLogado);
            defaultFacadeService.atualizaPrdCarrinho(produto);
        }

        carrinhoLogado.setListProdutos(excluirProdutosDuplicados(carrinhoLogado.getListProdutos()));

        return carrinhoLogado;
    }

    public List<PrdCarrinhosModel> excluirProdutosDuplicados (List<PrdCarrinhosModel> carrinhoProdutos)
    {
        List<Integer> idsUnico = new ArrayList<>();
        List<PrdCarrinhosModel> carrinhoProdutoAux = new ArrayList<>();

        carrinhoProdutoAux.addAll(carrinhoProdutos);

        for (PrdCarrinhosModel produto : carrinhoProdutos)
        {
            if (idsUnico.contains(produto.getProduto().getPrdId()))
            {
                carrinhoProdutoAux.remove(produto);
                defaultFacadeService.removeProduto(produto.getPcrId());
            }
            else
            {
                idsUnico.add(produto.getProduto().getPrdId());
            }
        }

        return carrinhoProdutoAux;
    }

    public void converterProdutosCarrinhoParaPedidos (CarrinhosModel carrinho, PedidosComprasModel pedidoCompra)
    {
        for (PrdCarrinhosModel carProduto : carrinho.getListProdutosAtivo())
        {
            PedProdutosModel pedProduto = new PedProdutosModel();
            List<CategoriasProdutosModel> listaCategorias = categoriasProdutosRepository
                    .findAllByProdutosId(carProduto.getProduto().getPrdId());

            pedProduto.setProduto(carProduto.getProduto());
            pedProduto.setPpdQuantidade(carProduto.getPcrQuantidade());
            pedProduto.getProduto().setListCategoriasProdutos(listaCategorias);

            pedidoCompra.getListPedProdutos().add(pedProduto);
        }
    }

    public void validarCupons (PedidosComprasModel pedidoCompra, List<CuponsTrocasModel> listaCuponsTrocasCliente)
    {
        // Pelo for ternario (each) o objeto cupom não era reconhecido, fazendo com que
        // fosse excluido o primeiro cupom da lista, acredito que o problema ocorra
        // devido ao caminho CupomTrocasModel > PedCuponsTrocas > PedidoCompras.
        for (int index = 0 ; index < pedidoCompra.getListPedCuponsTrocas().size() ; index++)
        {
            if (pedidoCompra.getValorTotalPedido() < 0 && pedidoCompra.getListPedCuponsTrocas().get(index).getCupom()
                    .getCptSaldo() < Math.abs(pedidoCompra.getValorTotalPedido()))
            {
                listaCuponsTrocasCliente.add(pedidoCompra.getListPedCuponsTrocas().get(index).getCupom());
                pedidoCompra.getListPedCuponsTrocas().remove(index);

                // Necessário o formato recursivo com o break, pois é removido um item da
                // própria iteração.
                validarCupons(pedidoCompra, listaCuponsTrocasCliente);
                break;
            }
        }
    }

    public void validarCartoes (PedidosComprasModel pedidoCompra)
    {
        if (pedidoCompra.getValorTotalPedido() > 0
                && (int) (pedidoCompra.getValorTotalPedido() / (pedidoCompra.getListPedCartoes().size()) * 10) == 0)
        {
            pedidoCompra.getListPedCartoes().remove(pedidoCompra.getListPedCartoes().size() - 1);

            validarCartoes(pedidoCompra);
        }

        if (pedidoCompra.getValorTotalPedido() <= 0)
        {
            pedidoCompra.getListPedCartoes().clear();
        }

        for (int index = pedidoCompra.getListPedCartoes().size() - 1 ; index >= 0 ; index--)
        {
            if (pedidoCompra.getValorTotalCartoes() > pedidoCompra.getValorTotalPedido())
            {
                pedidoCompra.getListPedCartoes().get(index).setPctValor(0.0);
            }
            else
            {
                break;
            }
        }

        for (int index = pedidoCompra.getListPedCartoes().size() - 1 ; index >= 0 ; index--)
        {
            if (pedidoCompra.getValorPendenteTotal() < 10
                    && pedidoCompra.getListPedCartoes().get(index).getPctValor() == 0.0)
            {
                pedidoCompra.getListPedCartoes().remove(index);
            }
        }
    }

    // Código quebrado
    public int qntPrdCarrinhoPedido (Integer id)
    {
        int quantidade = 0;

        for (PrdCarrinhosModel aux : prdCarrinhosRepository.findAllByProdutoId(id))
        {
            quantidade += aux.getPcrQuantidade();
        }

        return quantidade;
    }
}