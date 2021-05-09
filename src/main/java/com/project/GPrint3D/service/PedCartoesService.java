package com.project.GPrint3D.service;

import com.project.GPrint3D.model.PedCartoesModel;
import com.project.GPrint3D.repository.PedCartoesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
class PedCartoesService
{
    @Autowired
    private PedCartoesRepository pedCartoesRepository;

    public String[] cadastrar (PedCartoesModel pedCartao)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            pedCartoesRepository.save(pedCartao);

            response[0] = msg1;
            response[1] = "Cartão do pedido cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Cartão do pedido já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o cartão do pedido";
        }

        return response;
    }

    public String[] atualizar (PedCartoesModel pedCartao)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            pedCartoesRepository.save(pedCartao);

            response[0] = msg1;
            response[1] = "Cadastro de cartão do pedido alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o cartão do pedido";
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
            pedCartoesRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de cartão do pedido deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o cartão do pedido";
        }

        return response;
    }
}
