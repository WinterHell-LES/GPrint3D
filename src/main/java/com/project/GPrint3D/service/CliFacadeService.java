package com.project.GPrint3D.service;

import java.security.Principal;
import java.util.List;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.model.PedCartoesModel;
import com.project.GPrint3D.model.PedComFretesModel;
import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.model.PedidosTrocasModel;
import com.project.GPrint3D.model.TelefonesModel;

public interface CliFacadeService
{
        // Cadastro
        // --------------------------------------------------------------------------------------------------
        public String[] atualizarCadastroCliente (ClientesModel cliente, TelefonesModel telefone,
                        DocumentosModel documento);

        // Pedido compra
        // --------------------------------------------------------------------------------------------------
        public String[] cadastrarPedidoCompra (PedidosComprasModel pedidoCompra, PedComFretesModel frete,
                        CarrinhosModel carrinho);

        public boolean validarCvv (String cvv, PedCartoesModel pedCartao);

        public String[] cancelarPedidoCompra (Integer id);

        public String[] solicitarTroca (PedidosTrocasModel pedidoTroca);

        public String[] escolherTroca (PedidosTrocasModel pedidoTroca);

        // Cartões
        // --------------------------------------------------------------------------------------------------
        public String[] cadastrarCartao (CartoesModel cartao, boolean crtPadrao, Principal principal);

        public String[] atualizarCartao (CartoesModel cartao, boolean crtPadrao, String cvv);

        public String[] excluirCartao (Integer id);

        // Endereços
        // --------------------------------------------------------------------------------------------------
        public String[] cadastrarEndereco (EnderecosModel endereco, boolean endPadrao, Principal principal);

        public String[] atualizarEndereco (EnderecosModel endereco, boolean endPadrao);

        public String[] excluirEndereco (Integer id);

        // Senha
        // --------------------------------------------------------------------------------------------------
        public String[] alterarSenha (Integer usuarioId, String newPassword);

        public List<String> validarSenhaAtualizacao (Integer usuarioId, String oldPassword, String newPassword,
                        String confirmNewPassword);
}
