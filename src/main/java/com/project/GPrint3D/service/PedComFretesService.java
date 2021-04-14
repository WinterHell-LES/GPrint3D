package com.project.GPrint3D.service;

import com.project.GPrint3D.model.PedComFretesModel;
import com.project.GPrint3D.repository.PedComFretesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PedComFretesService
{
    @Autowired
    private PedComFretesRepository fretes;

    public String[] cadastrar(PedComFretesModel frete)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            fretes.save(frete);

            response[0] = msg1;
            response[1] = "Frete cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Frete já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o frete";
        }

        return response;
    }

    public String[] atualizar(PedComFretesModel frete)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            fretes.save(frete);

            response[0] = msg1;
            response[1] = "Cadastro de frete alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o frete";
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
            fretes.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de frete deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o frete";
        }
        
        return response;
    }
}
