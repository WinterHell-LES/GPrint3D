package com.project.GPrint3D.service;

import com.project.GPrint3D.model.TelefonesModel;
import com.project.GPrint3D.repository.TelefonesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
class TelefonesService
{
    @Autowired
    private TelefonesRepository telefones;

    public String[] cadastrar (TelefonesModel telefone)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            telefones.save(telefone);

            response[0] = msg1;
            response[1] = "Telefone cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Telefone já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o telefone";
        }

        return response;
    }

    public String[] atualizar (TelefonesModel telefone)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            telefones.save(telefone);

            response[0] = msg1;
            response[1] = "Cadastro de telefone alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o telefone";
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
            telefones.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de telefone deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o telefone";
        }

        return response;
    }
}