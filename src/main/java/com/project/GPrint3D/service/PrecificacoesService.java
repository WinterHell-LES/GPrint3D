package com.project.GPrint3D.service;

import com.project.GPrint3D.model.PrecificacoesModel;
import com.project.GPrint3D.repository.PrecificacoesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PrecificacoesService 
{
    @Autowired
    private PrecificacoesRepository precificacoes;

    public String[] cadastrar(PrecificacoesModel precificacao)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            precificacoes.save(precificacao);

            response[0] = msg1;
            response[1] = "Precificação cadastrada com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Precificação já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar a precificação";
        }

        return response;
    }

    public String[] atualizar(PrecificacoesModel precificacao)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            precificacoes.save(precificacao);

            response[0] = msg1;
            response[1] = "Cadastro de precificação alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a precificação";
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
            precificacoes.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de precificação deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar a precificação";
        }
        
        return response;
    }
}
