package com.project.GPrint3D.service;

import java.security.Principal;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.CartoesPadroesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.model.EndCobrancasPadroesModel;
import com.project.GPrint3D.model.EndEntregasPadroesModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.PedCartoesModel;
import com.project.GPrint3D.model.PedComFretesModel;
import com.project.GPrint3D.model.PedCuponsPromocoesModel;
import com.project.GPrint3D.model.PedCuponsTrocasModel;
import com.project.GPrint3D.model.PedProdutosModel;
import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.PedidosTrocasModel;
import com.project.GPrint3D.model.TelefonesModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.CartoesPadroesRepository;
import com.project.GPrint3D.repository.CartoesRepository;
import com.project.GPrint3D.repository.EndCobrancasPadroesRepository;
import com.project.GPrint3D.repository.EndEntregasPadroesRepository;
import com.project.GPrint3D.repository.PedidosComprasRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.util.GeradorCodigoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CliFacadeServiceImpl implements CliFacadeService
{
    @Autowired
    private CartoesRepository cartoesRepository;

    @Autowired
    private CartoesPadroesRepository cartoesPadroesRepository;

    @Autowired
    private EndCobrancasPadroesRepository endCobrancasPadroesRepository;

    @Autowired
    private EndEntregasPadroesRepository endEntregasPadroesRepository;

    @Autowired
    private PedidosComprasRepository pedidosComprasRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private CarrinhosService carrinhosService;

    @Autowired
    private CartoesService cartoesService;

    @Autowired
    private CartoesPadroesService cartoesPadroesService;

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private CuponsTrocasService cuponsTrocasService;

    @Autowired
    private DocumentosService documentosService;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private EndCobrancasPadroesService endCobrancasPadroesService;

    @Autowired
    private EndEntregasPadroesService endEntregasPadroesService;

    @Autowired
    private PedCartoesService pedCartoesService;

    @Autowired
    private PedComFretesService pedComFretesService;

    @Autowired
    private PedCuponsTrocasService pedCuponsTrocasService;

    @Autowired
    private PedCuponsPromocoesService pedCuponsPromocoesService;

    @Autowired
    private PedidosComprasService pedidosComprasService;

    @Autowired
    private PedidosTrocasService pedidosTrocasService;

    @Autowired
    private PedProdutosService pedProdutosService;

    @Autowired
    private TelefonesService telefonesService;

    @Autowired
    private UsuariosService usuariosService;

    // Cadastro
    // --------------------------------------------------------------------------------------------------
    public String[] atualizarCadastroCliente (ClientesModel cliente, TelefonesModel telefone, DocumentosModel documento)
    {
        String[] mensagem = clientesService.atualizar(cliente);
        telefonesService.atualizar(telefone);
        documentosService.atualizar(documento);

        return mensagem;
    }

    // Pedido compra
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarPedidoCompra (PedidosComprasModel pedidoCompra, PedComFretesModel frete,
            CarrinhosModel carrinho)
    {
        GeradorCodigoUtil codigo = new GeradorCodigoUtil();

        // A ideia é garantir que o número do pedido gerado seja único.
        // Deve validar a informação via BD com a Constraint Unique.
        String pedidoCompraNumero = codigo.getGerarNumeroPedido();

        // Seta o frete como nulo para não ocorre o erro do OneToOne onde exige o id do
        // Frete
        pedidoCompra.setFrete(null);

        pedidoCompra.setPdcNumero(pedidoCompraNumero);
        String[] mensagem = pedidosComprasService.cadastrar(pedidoCompra);

        PedidosComprasModel pedidoCompraRegistrado = pedidosComprasRepository.findByNumeroPedido(pedidoCompraNumero);

        frete.setPedidoCompra(pedidoCompraRegistrado);
        pedComFretesService.cadastrar(frete);

        // Insere o frete para ser utilizado em formulas mais a frente
        pedidoCompra.setFrete(frete);

        for (PedCartoesModel pedCartoes : pedidoCompra.getListPedCartoes())
        {
            pedCartoes.setPedidoCompra(pedidoCompraRegistrado);
            pedCartoesService.cadastrar(pedCartoes);
        }

        for (PedCuponsPromocoesModel cupomPromocoes : pedidoCompra.getListPedCuponsPromocoes())
        {
            cupomPromocoes.setPedidoCompra(pedidoCompraRegistrado);
            pedCuponsPromocoesService.cadastrar(cupomPromocoes);
        }

        boolean validadorSaldo = true;

        for (PedCuponsTrocasModel cupomTroca : pedidoCompra.getListPedCuponsTrocas())
        {
            if (validadorSaldo && pedidoCompra.getValorPendenteTotal() < 0)
            {
                cupomTroca.getCupom().setCptSaldo(Math.abs(pedidoCompra.getValorPendenteTotal()));
                cuponsTrocasService.atualizar(cupomTroca.getCupom());
                validadorSaldo = false;
            }
            else
            {
                cupomTroca.getCupom().setCptSaldo(0.0);
                cupomTroca.getCupom().setCptStatus(false);
                cuponsTrocasService.atualizar(cupomTroca.getCupom());
            }

            cupomTroca.setPedidoCompra(pedidoCompraRegistrado);
            pedCuponsTrocasService.cadastrar(cupomTroca);
        }

        for (PedProdutosModel produto : pedidoCompra.getListPedProdutos())
        {
            produto.setPedidoCompra(pedidoCompraRegistrado);
            pedProdutosService.cadastrar(produto);
        }

        carrinhosService.excluir(carrinho.getCarId());

        return mensagem;
    }

    public boolean validarCvv (String cvv, PedCartoesModel pedCartao)
    {
        return pedidosComprasService.verificaCvv(cvv, pedCartao);
    }

    public String[] cancelarPedidoCompra (Integer id)
    {
        PedidosComprasModel pedido = pedidosComprasRepository.findOneById(id);

        pedido.setPdcStatusPedido(11);
        pedido.setPdcStatusLogistica(11);

        return pedidosComprasService.atualizar(pedido);
    }

    public String[] solicitarTroca (PedidosTrocasModel pedidoTroca)
    {
        GeradorCodigoUtil codigo = new GeradorCodigoUtil();

        pedidoTroca.setPdtNumero(codigo.getGerarCodigoTroca());

        return pedidosTrocasService.cadastrar(pedidoTroca);
    }

    public String[] escolherTroca (PedidosTrocasModel pedidoTroca)
    {
        pedidoTroca.setPdtStatusPedido(0);

        return pedidosTrocasService.atualizar(pedidoTroca);
    }

    // Cartões
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarCartao (CartoesModel cartao, boolean crtPadrao, Principal principal)
    {
        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());

        cartao.setCliente(usu.getCliente());

        String[] mensagem = cartoesService.cadastrar(cartao);

        if (crtPadrao)
        {
            CartoesPadroesModel cartaoPadrao = cartoesPadroesRepository.findByClienteId(usu.getCliente().getCliId());

            cartaoPadrao
                    .setCartao(cartoesRepository.findByCrtNumero(cartao.getCrtNumero(), usu.getCliente().getCliId()));

            if (cartaoPadrao.getCtpId() != null)
            {
                cartoesPadroesService.atualizar(cartaoPadrao);
            }
            else
            {
                cartoesPadroesService.cadastrar(cartaoPadrao);
            }
        }

        return mensagem;
    }

    public String[] atualizarCartao (CartoesModel cartao, boolean crtPadrao, String cvv)
    {
        if (!cartoesService.verificaCvv(cvv, cartao))
        {
            cartao.setCrtCvv(cartoesService.gerarCvv(cvv));
        }

        if (crtPadrao)
        {
            CartoesPadroesModel cartaoPadrao = cartoesPadroesRepository.findByClienteId(cartao.getCliente().getCliId());

            cartaoPadrao.setCartao(cartao);

            cartoesPadroesService.atualizar(cartaoPadrao);
        }

        return cartoesService.atualizar(cartao);
    }

    public String[] excluirCartao (Integer id)
    {
        return cartoesService.excluir(id);
    }

    // Endereços
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarEndereco (EnderecosModel endereco, boolean endPadrao, Principal principal)
    {
        UsuariosModel usu = usuariosRepository.findByEmail(principal.getName());
        EndCobrancasPadroesModel endCobrancaPadrao = endCobrancasPadroesRepository
                .findByClienteId(endereco.getCliente().getCliId());
        EndEntregasPadroesModel endEntregaPadrao = endEntregasPadroesRepository
                .findByClienteId(endereco.getCliente().getCliId());

        endereco.setCliente(usu.getCliente());

        String[] mensagem = enderecosService.cadastrar(endereco);

        if (endPadrao)
        {
            if (endereco.isEndCobranca())
            {
                endCobrancaPadrao.setEndereco(endereco);

                endCobrancasPadroesService.atualizar(endCobrancaPadrao);
            }

            if (endereco.isEndEntrega())
            {
                endEntregaPadrao.setEndereco(endereco);

                endEntregasPadroesService.atualizar(endEntregaPadrao);
            }
        }

        return mensagem;
    }

    public String[] atualizarEndereco (EnderecosModel endereco, boolean endPadrao)
    {
        EndCobrancasPadroesModel endCobrancaPadrao = endCobrancasPadroesRepository
                .findByClienteId(endereco.getCliente().getCliId());
        EndEntregasPadroesModel endEntregaPadrao = endEntregasPadroesRepository
                .findByClienteId(endereco.getCliente().getCliId());

        if (endPadrao)
        {
            if (endereco.isEndCobranca())
            {
                endCobrancaPadrao.setEndereco(endereco);

                endCobrancasPadroesService.atualizar(endCobrancaPadrao);
            }

            if (endereco.isEndEntrega())
            {
                endEntregaPadrao.setEndereco(endereco);

                endEntregasPadroesService.atualizar(endEntregaPadrao);
            }
        }

        return enderecosService.atualizar(endereco);
    }

    public String[] excluirEndereco (Integer id)
    {
        return enderecosService.excluir(id);
    }

    // Senha
    // --------------------------------------------------------------------------------------------------
    public String[] alterarSenha (Integer usuarioId, String oldPassword, String newPassword, String confirmNewPassword)
    {
        UsuariosModel usuario = usuariosRepository.findOneById(usuarioId);

        usuario.setUsuSenha(newPassword);

        return usuariosService.atualizarPass(oldPassword, newPassword, confirmNewPassword, usuario);
    }
}
