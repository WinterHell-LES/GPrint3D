package com.project.GPrint3D.service;

import java.sql.Date;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.CartoesPadroesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.model.EndCobrancasPadroesModel;
import com.project.GPrint3D.model.EndEntregasPadroesModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.TelefonesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CartoesRepository;
import com.project.GPrint3D.repository.ClientesRepository;
import com.project.GPrint3D.repository.EnderecosRepository;
import com.project.GPrint3D.repository.PrdCarrinhosRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.util.CarrinhoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultFacadeServiceImpl implements DefaultFacadeService
{
    @Autowired
    private CartoesRepository cartoesRepository;

    @Autowired
    private CarrinhosService carrinhosService;

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private EnderecosRepository enderecosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PrdCarrinhosRepository prdCarrinhosRepository;

    @Autowired
    private CartoesService cartoesService;

    @Autowired
    private CartoesPadroesService cartoesPadroesService;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private EndCobrancasPadroesService endCobrancasPadroesService;

    @Autowired
    private EndEntregasPadroesService endEntregasPadroesService;

    @Autowired
    private DocumentosService documentosService;

    @Autowired
    private PrdCarrinhosService prdCarrinhosService;

    @Autowired
    private TelefonesService telefonesService;

    @Autowired
    private UsuariosService usuariosService;

    // Cadastro cliente
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarCliente (UsuariosModel usuario, ClientesModel cliente, TelefonesModel telefone,
            DocumentosModel documento, EnderecosModel endereco, CartoesModel cartao)
    {
        usuariosService.cadastrar(usuario);

        UsuariosModel usu = usuariosRepository.findByEmail(usuario.getUsuEmail());

        cliente.setUsuario(usu);
        String[] mensagem = clientesService.cadastrar(cliente);

        ClientesModel cli = clientesRepository.findByUsuarioId(usu.getUsuId());

        telefone.setCliente(cli);
        documento.setCliente(cli);
        endereco.setCliente(cli);
        cartao.setCliente(cli);

        telefonesService.cadastrar(telefone);
        enderecosService.cadastrar(endereco);
        documentosService.cadastrar(documento);
        cartoesService.cadastrar(cartao);

        EnderecosModel end = enderecosRepository.findByClienteId(cli.getCliId());

        EndCobrancasPadroesModel endCobrancasPadrao = new EndCobrancasPadroesModel();
        EndEntregasPadroesModel endEntregasPadrao = new EndEntregasPadroesModel();

        if (end.isEndCobranca())
        {
            endCobrancasPadrao.setCliente(cli);
            endCobrancasPadrao.setEndereco(end);
            endCobrancasPadroesService.cadastrar(endCobrancasPadrao);
        }

        if (end.isEndEntrega())
        {
            endEntregasPadrao.setCliente(cli);
            endEntregasPadrao.setEndereco(end);
            endEntregasPadroesService.cadastrar(endEntregasPadrao);
        }

        CartoesModel crt = cartoesRepository.findByClienteId(cli.getCliId());

        CartoesPadroesModel cartaoPadrao = new CartoesPadroesModel();

        cartaoPadrao.setCartao(crt);
        cartaoPadrao.setCliente(cli);
        cartoesPadroesService.cadastrar(cartaoPadrao);

        return mensagem;
    }

    // Carrinho
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarCarrinho (CarrinhosModel carrinho)
    {
        return carrinhosService.cadastrar(carrinho);
    }

    public String[] aumentarProduto (Integer pcrId)
    {
        PrdCarrinhosModel prdCarrinho = prdCarrinhosRepository.findOneById(pcrId);

        prdCarrinho.aumentarProduto();

        return prdCarrinhosService.aumentarQuantidadeProduto(prdCarrinho);
    }

    public String[] diminuiProduto (Integer pcrId)
    {
        PrdCarrinhosModel prdCarrinho = prdCarrinhosRepository.findOneById(pcrId);

        prdCarrinho.diminuirProduto();

        return prdCarrinhosService.diminuirQuantidadeProduto(prdCarrinho);
    }

    public String[] atualizaProduto (Integer pcrId, Integer prdQuantidade)
    {
        PrdCarrinhosModel prdCarrinho = prdCarrinhosRepository.findOneById(pcrId);

        prdCarrinho.atualizarQtdProduto(prdQuantidade);

        if (prdQuantidade > 0)
        {
            return prdCarrinhosService.atualizarQuantidadeProduto(prdCarrinho);
        }
        else
        {
            return prdCarrinhosService.excluir(prdCarrinho.getPcrId());
        }
    }

    public String[] atualizaPrdCarrinho (PrdCarrinhosModel prdCarrinho)
    {
        return prdCarrinhosService.atualizar(prdCarrinho);
    }

    public String[] removeProduto (Integer pcrId)
    {
        return prdCarrinhosService.excluir(pcrId);
    }

    public String[] removeTodosProdutos (Integer carId)
    {
        return prdCarrinhosService.excluirAll(carId);
    }

    public String[] ativarProduto (Integer pcrId)
    {
        PrdCarrinhosModel prdCarrinho = prdCarrinhosRepository.findOneById(pcrId);

        if (prdCarrinho.getPcrQuantidade() <= prdCarrinho.getProduto().getPrdQuantidade())
        {
            java.util.Date dataAtual = new java.util.Date();

            return prdCarrinhosService.atualizarStatusAtivaPrdCarrinhos(prdCarrinho.getPcrQuantidade(), true,
                    new Date(dataAtual.getTime()), pcrId);
        }
        else
        {
            return prdCarrinhosService.atualizarStatusPrdCarrinhos(prdCarrinho.getProduto().getPrdQuantidade(), true,
                    pcrId);
        }
    }

    public String[] atualizarStatusProduto (Integer quantidade, boolean status, Integer pcrId)
    {
        return prdCarrinhosService.atualizarStatusPrdCarrinhos(quantidade, status, pcrId);
    }

    // Produtos
    // --------------------------------------------------------------------------------------------------
    public String[] incluirNoCarrinho (CarrinhosModel carrinho, ProdutosModel produto, Integer quantidade)
    {
        CarrinhoUtil carrinhoUtil = new CarrinhoUtil();

        PrdCarrinhosModel prdCarrinhoNovo = new PrdCarrinhosModel();

        prdCarrinhoNovo.setProduto(produto);
        prdCarrinhoNovo.setPcrQuantidade(quantidade);
        prdCarrinhoNovo.setCarrinho(carrinho);

        PrdCarrinhosModel prdCarrinhoExistente = carrinhoUtil.localizaProduto(carrinho, prdCarrinhoNovo);

        if (prdCarrinhoExistente != null)
        {
            prdCarrinhoExistente.atualizarQtdProduto(prdCarrinhoExistente.getPcrQuantidade() + quantidade);

            return prdCarrinhosService.atualizarQuantidadeProduto(prdCarrinhoExistente);
        }
        else
        {
            return prdCarrinhosService.cadastrar(prdCarrinhoNovo);
        }
    }
}
