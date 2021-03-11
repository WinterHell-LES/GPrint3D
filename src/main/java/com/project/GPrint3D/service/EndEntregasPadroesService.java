package com.project.GPrint3D.service;

import com.project.GPrint3D.model.EndEntregasPadroesModel;
import com.project.GPrint3D.repository.EndEntregasPadroesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EndEntregasPadroesService 
{
    @Autowired
    private EndEntregasPadroesRepository endEntregasPadroes;

    public String[] cadastrar(EndEntregasPadroesModel endEntregasPadrao)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            endEntregasPadroes.save(endEntregasPadrao);

            response[0] = msg1;
            response[1] = "Endereço de entragas padrão cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Endereço de entragas padrão já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o endereço de entragas padrão";
        }

        return response;
    }

    public String[] atualizar(EndEntregasPadroesModel endEntregasPadrao)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            endEntregasPadroes.save(endEntregasPadrao);

            response[0] = msg1;
            response[1] = "Cadastro de endereço de entragas padrão alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o endereço de entragas padrão";
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
            endEntregasPadroes.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de endereço de entragas padrão deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o endereço de entragas padrão";
        }
        
        return response;
    }
}
