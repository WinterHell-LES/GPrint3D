package com.project.GPrint3D.service;

import com.project.GPrint3D.model.PedidosComprasModel;
import com.project.GPrint3D.repository.PedidosComprasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PedidosComprasService 
{
    @Autowired
    private PedidosComprasRepository pedidos;

    public String[] cadastrar(PedidosComprasModel pedido)
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

    public String[] atualizar(PedidosComprasModel pedido)
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
