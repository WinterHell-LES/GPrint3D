package com.project.GPrint3D.service;

import com.project.GPrint3D.model.VariaveisModel;
import com.project.GPrint3D.repository.VariaveisRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class VariaveisService 
{
    @Autowired
    private VariaveisRepository variaveisRepository;

    public String[] cadastrar(VariaveisModel variavel)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            variaveisRepository.save(variavel);

            response[0] = msg1;
            response[1] = "Variáveis cadastradas com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Variáveis já cadastradas";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao Variáveis as variáveis";
        }

        return response;
    }

    public String[] atualizar(VariaveisModel variavel)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            variaveisRepository.save(variavel);

            response[0] = msg1;
            response[1] = "Cadastro de variáveis alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar as variáveis";
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
            variaveisRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de variáveis deletadas com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar as variáveis";
        }
        
        return response;
    }
}
