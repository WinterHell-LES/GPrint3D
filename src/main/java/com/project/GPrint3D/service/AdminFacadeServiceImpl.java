package com.project.GPrint3D.service;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;

import com.project.GPrint3D.model.BandeirasModel;
import com.project.GPrint3D.model.CategoriasModel;
import com.project.GPrint3D.model.CategoriasProdutosModel;
import com.project.GPrint3D.model.CuponsPromocoesModel;
import com.project.GPrint3D.model.CuponsTrocasModel;
import com.project.GPrint3D.model.EntradasModel;
import com.project.GPrint3D.model.FotosModel;
import com.project.GPrint3D.model.PedProdutosModel;
import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.PedidosTrocasModel;
import com.project.GPrint3D.model.PrecificacoesModel;
import com.project.GPrint3D.model.ProdutosJustificativasModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.SaidasModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.CategoriasRepository;
import com.project.GPrint3D.repository.PedidosComprasRepository;
import com.project.GPrint3D.repository.PedidosTrocasRepository;
import com.project.GPrint3D.repository.ProdutosRepository;
import com.project.GPrint3D.repository.UsuariosRepository;
import com.project.GPrint3D.repository.VariaveisRepository;
import com.project.GPrint3D.util.GeradorCodigoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AdminFacadeServiceImpl implements AdminFacadeService
{
    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private PedidosComprasRepository pedidosComprasRepository;

    @Autowired
    private PedidosTrocasRepository pedidosTrocasRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private VariaveisRepository variaveisRepository;

    @Autowired
    private BandeirasService bandeirasService;

    @Autowired
    private CategoriasService categoriasService;

    @Autowired
    private CategoriasProdutosService categoriasProdutosService;

    @Autowired
    private CuponsPromocoesService cuponsPromocoesService;

    @Autowired
    private CuponsTrocasService cuponsTrocasService;

    @Autowired
    private EntradasService entradasService;

    @Autowired
    private FotosService fotosService;

    @Autowired
    private PedidosComprasService pedidosComprasService;

    @Autowired
    private PedidosTrocasService pedidosTrocasService;

    @Autowired
    private PrecificacoesService precificacoesService;

    @Autowired
    private ProdutosService produtosService;

    @Autowired
    private ProdutosJustificativasService produtosJustificativasService;

    @Autowired
    private SaidasService saidasService;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private VariaveisService variaveisService;

    // Bandeiras
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarBandeira (BandeirasModel bandeira)
    {
        return bandeirasService.cadastrar(bandeira);
    }

    public String[] atualizarBandeira (BandeirasModel bandeira)
    {
        return bandeirasService.atualizar(bandeira);
    }

    public String[] excluirBandeira (Integer id)
    {
        return bandeirasService.excluir(id);
    }

    public String[] ativarBandeira (Boolean ativa, Integer id)
    {
        return bandeirasService.ativar(ativa, id);
    }

    // Categorias
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarCategoria (CategoriasModel categoria)
    {
        return categoriasService.cadastrar(categoria);
    }

    public String[] atualizarCategoria (CategoriasModel categoria)
    {
        return categoriasService.atualizar(categoria);
    }

    public String[] excluirCategoria (Integer id)
    {
        return categoriasService.excluir(id);
    }

    public String[] ativarCategoria (Boolean ativa, Integer id)
    {
        return categoriasService.ativar(ativa, id);
    }

    // Clientes
    // --------------------------------------------------------------------------------------------------
    public String[] ativarCliente (Boolean ativa, Integer id)
    {
        return usuariosService.ativar(ativa, id);
    }

    // Cupons promocionais
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarCupomPromocional (CuponsPromocoesModel cupom)
    {
        GeradorCodigoUtil codigo = new GeradorCodigoUtil();

        cupom.setCppCodigo(codigo.getGerarCodigoPromocional());

        return cuponsPromocoesService.cadastrar(cupom);
    }

    public String[] atualizarCupomPromocional (CuponsPromocoesModel cupom)
    {
        return cuponsPromocoesService.atualizar(cupom);
    }

    public String[] excluirCupomPromocional (Integer id)
    {
        return cuponsPromocoesService.excluir(id);
    }

    // Entrada
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarEntrada (EntradasModel entrada, Principal principal)
    {
        entrada.setUsuario(usuariosRepository.findByEmail(principal.getName()));

        return entradasService.cadastrar(entrada);
    }

    // Logística de pedidos
    // --------------------------------------------------------------------------------------------------
    public String[] atualizarLogisticaCompras (PedidosComprasModel pedido)
    {
        PedidosComprasModel pedCompra = pedidosComprasRepository.findOneById(pedido.getPdcId());

        pedCompra.setPdcStatusLogistica(pedido.getPdcStatusLogistica());

        if (pedido.getPdcStatusLogistica() > 3)
        {
            pedCompra.setPdcStatusPedido(pedido.getPdcStatusLogistica() - 1);
        }

        if (pedido.getPdcStatusLogistica() == 5)
        {
            java.util.Date dataAtual = new java.util.Date();

            pedCompra.setPdcDataFim(new Date(dataAtual.getTime()));
        }

        return pedidosComprasService.atualizar(pedCompra);
    }

    public String[] atualizarLogisticaTrocas (PedidosTrocasModel pedido)
    {
        PedidosTrocasModel pedTroca = pedidosTrocasRepository.findOneById(pedido.getPdtId());

        pedTroca.setPdtStatusLogistica(pedido.getPdtStatusLogistica());

        if (pedido.getPdtEscolha() == 1)
        {
            if (pedido.getPdtStatusLogistica() > 3)
            {
                pedTroca.setPdtStatusPedido(pedido.getPdtStatusLogistica() - 2);
            }
        }
        else
        {
            if (pedido.getPdtStatusLogistica() > 2)
            {
                pedTroca.setPdtStatusPedido(pedido.getPdtStatusLogistica() - 2);
            }
        }

        System.out.println();

        return pedidosTrocasService.atualizar(pedTroca);
    }

    // Pedidos
    // --------------------------------------------------------------------------------------------------
    public String[] atualizarPedidoCompras (PedidosComprasModel pedido)
    {
        PedidosComprasModel pedCompra = pedidosComprasRepository.findOneById(pedido.getPdcId());

        pedCompra.setPdcStatusPedido(pedido.getPdcStatusPedido());

        if (pedido.getPdcStatusPedido() == 1)
        {
            SaidasModel saida = new SaidasModel();

            saida.setPedidoCompra(pedCompra);

            for (PedProdutosModel aux : pedCompra.getListPedProdutos())
            {
                saida.setProduto(aux.getProduto());
                saida.setSaiQuantidade(aux.getPpdQuantidade());

                saidasService.cadastrar(saida);
            }
        }

        return pedidosComprasService.atualizar(pedCompra);
    }

    public String[] atualizarPedidoTrocas (PedidosTrocasModel pedido)
    {
        PedidosTrocasModel pedTroca = pedidosTrocasRepository.findOneById(pedido.getPdtId());

        pedTroca.setPdtStatusPedido(pedido.getPdtStatusPedido());

        if ((pedTroca.getPdtEscolha() == 2) && (pedTroca.getPdtStatusPedido() == 2))
        {
            cuponsTrocasService.cadastrar(gerarCupom(pedTroca));
        }

        return pedidosTrocasService.atualizar(pedTroca);
    }

    public String[] cadastrarRetornoPedidoTroca (PedidosTrocasModel pedido, Integer quantidade)
    {
        PedidosTrocasModel pedTroca = pedidosTrocasRepository.findOneById(pedido.getPdtId());

        ProdutosModel produto = produtosRepository.findOneById(pedTroca.getPedProduto().getProduto().getPrdId());

        produto.setPrdQuantidade(produto.getPrdQuantidade() + quantidade);

        produtosService.atualizar(produto);

        pedTroca.setPdtRetorno(true);

        return pedidosTrocasService.atualizar(pedido);
    }

    private CuponsTrocasModel gerarCupom (PedidosTrocasModel pedTroca)
    {
        GeradorCodigoUtil codigo = new GeradorCodigoUtil();
        CuponsTrocasModel cupom = new CuponsTrocasModel();

        VariaveisModel variavel = variaveisRepository.findOneById(1);

        double valor = pedTroca.getPedProduto().getProduto().getPrdPreco() * pedTroca.getPdtQuantidade();

        cupom.setCptValor(valor);
        cupom.setCptSaldo(valor);

        cupom.setCptCodigo(codigo.getGerarCodigoTroca());

        cupom.setCliente(pedTroca.getCliente());

        cupom.setCptValidade(Date.valueOf(LocalDate.now().plusDays(variavel.getVarValidCupom())));

        return cupom;
    }

    // Precificações
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarPrecificacao (PrecificacoesModel precificacao)
    {
        return precificacoesService.cadastrar(precificacao);
    }

    public String[] atualizarPrecificacao (PrecificacoesModel precificacao)
    {
        return precificacoesService.atualizar(precificacao);
    }

    public String[] excluirPrecificacao (Integer id)
    {
        return precificacoesService.excluir(id);
    }

    // Produtos
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarProduto (ProdutosModel produto, Integer categoriaId, MultipartFile fotoMultipartFile)
            throws IOException
    {
        String[] mensagem = produtosService.cadastrar(produto);

        ProdutosModel prod = produtosRepository.findOneByNome(produto.getPrdNome());
        CategoriasModel ctg = categoriasRepository.findOneById(categoriaId);

        CategoriasProdutosModel ctgPrd = new CategoriasProdutosModel();

        ctgPrd.setProduto(prod);
        ctgPrd.setCategoria(ctg);

        categoriasProdutosService.cadastrar(ctgPrd);

        String fotoNome = StringUtils.cleanPath(fotoMultipartFile.getOriginalFilename());

        FotosModel foto = new FotosModel();
        foto.setFtoNome(fotoNome);
        foto.setFtoContent(fotoMultipartFile.getBytes());
        foto.setFtoData(new Date(new java.util.Date().getTime()));
        foto.setProduto(prod);

        fotosService.cadastrar(foto);

        return mensagem;
    }

    public String[] atualizarProduto (ProdutosModel produto)
    {
        return produtosService.atualizar(produto);
    }

    public boolean verificaPrecificacao (ProdutosModel produto, EntradasModel entrada)
    {
        return produtosService.verificaPrecificacao(produto, entrada);
    }

    public boolean verificaUsuario (String senha, UsuariosModel usuario)
    {
        return produtosService.verificaUsuario(senha, usuario);
    }

    public String[] ativarProduto (Integer produtoId, ProdutosJustificativasModel produtosJustificativa)
    {
        ProdutosModel prd = produtosRepository.findOneById(produtoId);

        produtosJustificativa.setPjuProduto(prd.getPrdNome());

        for (int i = 0 ; i < prd.getListCategoriasProdutos().size() ; i++)
        {
            produtosJustificativa.setPjuCategorias(prd.getListCategoriasProdutos().get(i).getCategoria().getCtgNome());

            if (i < prd.getListCategoriasProdutos().size() - 1)
            {
                produtosJustificativa.setPjuCategorias(", ");
            }
        }

        produtosJustificativa.setPjuAcao(prd.getPrdAtivo() ? "DESATIVAR" : "ATIVAR");

        produtosJustificativasService.cadastrar(produtosJustificativa);

        return produtosService.ativar(!prd.getPrdAtivo(), produtoId);
    }

    public String[] cadastrarProdutoFoto (MultipartFile[] fotos, ProdutosModel produto) throws IOException
    {
        String[] mensagem = new String[2];

        for (MultipartFile aux : fotos)
        {
            String fotoNome = StringUtils.cleanPath(aux.getOriginalFilename());

            FotosModel foto = new FotosModel();
            foto.setFtoNome(fotoNome);
            foto.setFtoContent(aux.getBytes());
            foto.setFtoData(new Date(new java.util.Date().getTime()));
            foto.setProduto(produto);

            mensagem = fotosService.cadastrar(foto);

            if (mensagem[0].equals("cadastroError"))
            {
                break;
            }
        }

        return mensagem;
    }

    public String[] excluirProdutoFoto (Integer id)
    {
        return fotosService.excluir(id);
    }

    public String[] cadatrarProdutoCategoria (CategoriasModel categoria, ProdutosModel produto)
    {
        CategoriasProdutosModel cpr = new CategoriasProdutosModel();
        cpr.setCategoria(categoria);
        cpr.setProduto(produto);

        return categoriasProdutosService.cadastrar(cpr);
    }

    public String[] excluirProdutoCategoria (Integer id)
    {
        return categoriasProdutosService.excluir(id);
    }

    // Variáveis
    // --------------------------------------------------------------------------------------------------
    public String[] atualizarVariaveis (VariaveisModel variavel)
    {
        return variaveisService.atualizar(variavel);
    }
}
