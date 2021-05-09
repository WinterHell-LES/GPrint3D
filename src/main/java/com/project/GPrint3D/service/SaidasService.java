package com.project.GPrint3D.service;

import com.project.GPrint3D.model.SaidasModel;
import com.project.GPrint3D.repository.SaidasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
class SaidasService
{
    @Autowired
    private SaidasRepository saidasRepository;

    public String[] cadastrar (SaidasModel saida)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            saidasRepository.save(saida);

            response[0] = msg1;
            response[1] = "Saída cadastrada com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Saída já cadastrada";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar a saída";
        }

        return response;
    }

    public String[] atualizar (SaidasModel saida)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            saidasRepository.save(saida);

            response[0] = msg1;
            response[1] = "Cadastro de saída alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a saída";
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
            saidasRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de saída deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar a saída";
        }

        return response;
    }
}