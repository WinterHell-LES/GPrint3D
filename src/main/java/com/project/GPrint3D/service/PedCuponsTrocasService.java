package com.project.GPrint3D.service;

import com.project.GPrint3D.model.PedCuponsTrocasModel;
import com.project.GPrint3D.repository.PedCuponsTrocasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PedCuponsTrocasService
{
    @Autowired
    private PedCuponsTrocasRepository pedCuponsTrocasRepository;

    public String[] cadastrar (PedCuponsTrocasModel pedCupom)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            pedCuponsTrocasRepository.save(pedCupom);

            response[0] = msg1;
            response[1] = "Cupom do pedido cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Cupom do pedido já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o cupom do pedido";
        }

        return response;
    }

    public String[] atualizar (PedCuponsTrocasModel pedCupom)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            pedCuponsTrocasRepository.save(pedCupom);

            response[0] = msg1;
            response[1] = "Cadastro do cupom do pedido alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o cupom do pedido";
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
            pedCuponsTrocasRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro do cupom do pedido deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o cupom do pedido";
        }

        return response;
    }
}
