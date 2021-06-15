package com.project.GPrint3D.service;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import com.project.GPrint3D.model.BandeirasModel;
import com.project.GPrint3D.model.CategoriasModel;
import com.project.GPrint3D.model.CuponsPromocoesModel;
import com.project.GPrint3D.model.EntradasModel;
import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.PedidosTrocasModel;
import com.project.GPrint3D.model.PrecificacoesModel;
import com.project.GPrint3D.model.ProdutosJustificativasModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.model.VariaveisModel;

import org.springframework.web.multipart.MultipartFile;

public interface AdminFacadeService
{
    // Bandeiras
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarBandeira (BandeirasModel bandeira);

    public String[] atualizarBandeira (BandeirasModel bandeira);

    public String[] excluirBandeira (Integer id);

    public String[] ativarBandeira (Boolean ativa, Integer id);

    // Categorias
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarCategoria (CategoriasModel categoria);

    public String[] atualizarCategoria (CategoriasModel categoria);

    public String[] excluirCategoria (Integer id);

    public String[] ativarCategoria (Boolean ativa, Integer id);

    // Clientes
    // --------------------------------------------------------------------------------------------------
    public String[] ativarCliente (Boolean ativa, Integer id);

    // Cupons promocionais
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarCupomPromocional (CuponsPromocoesModel cupom);

    public String[] atualizarCupomPromocional (CuponsPromocoesModel cupom);

    public String[] excluirCupomPromocional (Integer id);

    // Entrada
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarEntrada (EntradasModel entrada, Principal principal);

    // Logística de pedidos
    // --------------------------------------------------------------------------------------------------
    public String[] atualizarLogisticaCompras (PedidosComprasModel pedido);

    public String[] atualizarLogisticaTrocas (PedidosTrocasModel pedido);

    // Pedidos
    // --------------------------------------------------------------------------------------------------
    public String[] atualizarPedidoCompras (PedidosComprasModel pedido);

    public String[] atualizarPedidoTrocas (PedidosTrocasModel pedido);

    public String[] cadastrarRetornoPedidoTroca (PedidosTrocasModel pedido, Integer quantidade);

    // Precificações
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarPrecificacao (PrecificacoesModel precificacao);

    public String[] atualizarPrecificacao (PrecificacoesModel precificacao);

    public String[] excluirPrecificacao (Integer id);

    // Produtos
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarProduto (ProdutosModel produto, Integer categoriaId, MultipartFile foto)
            throws IOException;

    public String[] atualizarProduto (ProdutosModel produto);

    public boolean verificaPrecificacao (ProdutosModel produto, EntradasModel entrada);

    public boolean verificaUsuario (String senha, UsuariosModel usuario);

    public String[] ativarProduto (Integer produtoId, ProdutosJustificativasModel produtosJustificativa);

    public String[] cadastrarProdutoFoto (MultipartFile[] fotos, ProdutosModel produto) throws IOException;

    public String[] excluirProdutoFoto (Integer id);

    public String[] cadatrarProdutoCategoria (CategoriasModel categoria, ProdutosModel produto);

    public String[] excluirProdutoCategoria (Integer id);

    // Gráficos
    // --------------------------------------------------------------------------------------------------
    public List<HashMap<String, String>> gerarGrafPedidos ();

    public List<HashMap<String, String>> gerarGrafProdutos ();

    public List<HashMap<String, String>> gerarGrafCategorias ();

    // Variáveis
    // --------------------------------------------------------------------------------------------------
    public String[] atualizarVariaveis (VariaveisModel variavel);
}
