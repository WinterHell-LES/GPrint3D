package com.project.GPrint3D.service;

import com.project.GPrint3D.model.EntradasModel;
import com.project.GPrint3D.repository.EntradasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EntradasService 
{
    @Autowired
    private EntradasRepository entradas;

    public String[] cadastrar(EntradasModel entrada)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            entradas.save(entrada);

            response[0] = msg1;
            response[1] = "Entrada cadastrada com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Entrada já cadastrada";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar a entrada";
        }

        return response;
    }

    public String[] atualizar(EntradasModel entrada)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            entradas.save(entrada);

            response[0] = msg1;
            response[1] = "Cadastro de entrada alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a entrada";
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
            entradas.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de entrada deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar a entrada";
        }
        
        return response;
    }
}
