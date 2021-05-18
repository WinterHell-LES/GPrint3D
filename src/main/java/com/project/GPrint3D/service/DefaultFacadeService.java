package com.project.GPrint3D.service;

import java.util.List;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.model.TelefonesModel;
import com.project.GPrint3D.model.UsuariosModel;

public interface DefaultFacadeService
{
    // Cadastro cliente
    // --------------------------------------------------------------------------------------------------
    public List<String> validarSenhaNova (String newPassword, String confirmNewPassword);

    public String[] cadastrarCliente (UsuariosModel usuario, ClientesModel cliente, TelefonesModel telefone,
            DocumentosModel documento, EnderecosModel endereco, CartoesModel cartao);

    // Carrinho
    // --------------------------------------------------------------------------------------------------
    public String[] cadastrarCarrinho (CarrinhosModel carrinho);

    public String[] aumentarProduto (Integer pcrId);

    public String[] diminuiProduto (Integer pcrId);

    public String[] atualizaProduto (Integer pcrId, Integer prdQuantidade);

    public String[] atualizaPrdCarrinho (PrdCarrinhosModel prdCarrinho);

    public String[] removeProduto (Integer pcrId);

    public String[] removeTodosProdutos (Integer carId);

    public String[] ativarProduto (Integer pcrId);

    public String[] atualizarStatusProduto (Integer quantidade, boolean status, Integer pcrId);

    // Produtos
    // --------------------------------------------------------------------------------------------------
    public String[] incluirNoCarrinho (CarrinhosModel carrinho, ProdutosModel produto, Integer quantidade);
}
