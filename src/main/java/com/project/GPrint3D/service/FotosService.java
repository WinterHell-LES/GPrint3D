package com.project.GPrint3D.service;

import com.project.GPrint3D.model.FotosModel;
import com.project.GPrint3D.repository.FotosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
class FotosService
{
    @Autowired
    private FotosRepository fotosRepository;

    public String[] cadastrar (FotosModel foto)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            fotosRepository.save(foto);

            response[0] = msg1;
            response[1] = "Foto cadastrada com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Foto já cadastrada";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar a foto";
        }

        return response;
    }

    public String[] atualizar (FotosModel foto)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            fotosRepository.save(foto);

            response[0] = msg1;
            response[1] = "Cadastro de foto alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a foto";
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
            fotosRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de foto deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar a foto";
        }

        return response;
    }
}
