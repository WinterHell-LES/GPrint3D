package com.project.GPrint3D.service;

import com.project.GPrint3D.model.EnderecosModel;
import com.project.GPrint3D.repository.EnderecosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
class EnderecosService
{
    @Autowired
    private EnderecosRepository enderecosRepository;

    public String[] cadastrar (EnderecosModel endereco)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            enderecosRepository.save(endereco);

            response[0] = msg1;
            response[1] = "Endereço cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Endereço já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o endereço";
        }

        return response;
    }

    public String[] atualizar (EnderecosModel endereco)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            enderecosRepository.save(endereco);

            response[0] = msg1;
            response[1] = "Cadastro de endereço alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o endereço";
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
            enderecosRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de endereco deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o endereco";
        }

        return response;
    }
}
