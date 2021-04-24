package com.project.GPrint3D.service;

import com.project.GPrint3D.model.CartoesPadroesModel;
import com.project.GPrint3D.repository.CartoesPadroesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CartoesPadroesService
{
    @Autowired
    private CartoesPadroesRepository cartoesPadroesRepository;

    public String[] cadastrar (CartoesPadroesModel cartaoPadrao)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            cartoesPadroesRepository.save(cartaoPadrao);

            response[0] = msg1;
            response[1] = "Cartão padrão cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Cartão padrão já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o cartão padrão";
        }

        return response;
    }

    public String[] atualizar (CartoesPadroesModel cartaoPadrao)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            cartoesPadroesRepository.save(cartaoPadrao);

            response[0] = msg1;
            response[1] = "Cadastro de cartão padrão alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o cartão padrão";
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
            cartoesPadroesRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de cartão padrão deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o cartão padrão";
        }

        return response;
    }
}
