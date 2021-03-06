package com.project.GPrint3D.service;

import com.project.GPrint3D.model.PedProdutosModel;
import com.project.GPrint3D.repository.PedProdutosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
class PedProdutosService
{
    @Autowired
    private PedProdutosRepository pedProdutosRepository;

    public String[] cadastrar (PedProdutosModel pedProduto)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            pedProdutosRepository.save(pedProduto);

            response[0] = msg1;
            response[1] = "Produto de pedido cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Produto de pedido já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o produto de pedido";
        }

        return response;
    }

    public String[] atualizar (PedProdutosModel pedProduto)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            pedProdutosRepository.save(pedProduto);

            response[0] = msg1;
            response[1] = "Cadastro do produto de pedido alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o produto de pedido";
        }

        return response;
    }

    public String[] excluir (Integer id)
    {
        String[] response = new String[2];

        String msg1 = "deleteSuccess";
        String msg2 = "deleteError";

        try
        {
            pedProdutosRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro do produto de pedido deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o produto de pedido";
        }

        return response;
    }
}
