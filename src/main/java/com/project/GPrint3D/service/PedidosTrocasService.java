package com.project.GPrint3D.service;

import com.project.GPrint3D.model.PedidosTrocasModel;
import com.project.GPrint3D.repository.PedidosTrocasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PedidosTrocasService 
{
    @Autowired
    private PedidosTrocasRepository pedidos;

    public String[] cadastrar(PedidosTrocasModel pedido)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            pedidos.save(pedido);

            response[0] = msg1;
            response[1] = "Pedido cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Pedido já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o pedido";
        }

        return response;
    }

    public String[] atualizar(PedidosTrocasModel pedido)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            pedidos.save(pedido);

            response[0] = msg1;
            response[1] = "Cadastro de pedido alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o pedido";
        }
        
        return response;
    }

    public String[] atualizarEscolha(Integer escolha, Integer id)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            pedidos.updateEscolha(escolha, id);

            response[0] = msg1;
            response[1] = "Cadastro de escolha alterada com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a escolha";
        }
        
        return response;
    }

    public String[] atualizarPedido(Integer status, Integer id)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            pedidos.updateStatusPedido(status, id);

            response[0] = msg1;
            response[1] = "Cadastro de pedido alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o pedido";
        }
        
        return response;
    }

    public String[] atualizarLogistica(Integer status, Integer id)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            pedidos.updateStatusLogistica(status, id);

            response[0] = msg1;
            response[1] = "Cadastro de pedido alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o pedido";
        }
        
        return response;
    }

    public String[] excluir(Integer id)
    {
        String[] response = new String[2];

        String msg1 = "deleteSuccess";
        String msg2 = "deleteError";

        try 
        {
            pedidos.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de pedido deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o pedido";
        }
        
        return response;
    }    
}
