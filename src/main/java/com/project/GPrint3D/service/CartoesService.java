package com.project.GPrint3D.service;

import com.project.GPrint3D.model.CartoesModel;
import com.project.GPrint3D.repository.CartoesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CartoesService
{
    @Autowired
    private CartoesRepository cartoes;

    public String[] cadastrar(CartoesModel cartao)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            cartoes.save(cartao);

            response[0] = msg1;
            response[1] = "Cartão cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Cartão já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o cartão";
        }

        return response;
    }

    public String[] atualizar(CartoesModel cartao)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            cartoes.save(cartao);

            response[0] = msg1;
            response[1] = "Cadastro de cartão alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o cartão";
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
            cartoes.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de cartão deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o cartão";
        }
        
        return response;
    }
}