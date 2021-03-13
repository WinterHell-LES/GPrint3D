package com.project.GPrint3D.service;

import com.project.GPrint3D.model.EndCobrancasPadroesModel;
import com.project.GPrint3D.repository.EndCobrancasPadroesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class EndCobrancasPadroesService
{
    @Autowired
    private EndCobrancasPadroesRepository endCobrancasPadroes;

    public String[] cadastrar(EndCobrancasPadroesModel endCobrancaPadrao)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            endCobrancasPadroes.save(endCobrancaPadrao);

            response[0] = msg1;
            response[1] = "Endereço de cobrança padrão cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Endereço de cobrança padrão já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o endereço de cobrança padrão";
        }

        return response;
    }

    public String[] atualizar(EndCobrancasPadroesModel endCobrancaPadrao)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            endCobrancasPadroes.save(endCobrancaPadrao);

            response[0] = msg1;
            response[1] = "Cadastro de endereço de cobrança padrão alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o endereço de cobrança padrão";
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
            endCobrancasPadroes.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de endereço de cobrança padrão deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o endereço de cobrança padrão";
        }
        
        return response;
    }
}
