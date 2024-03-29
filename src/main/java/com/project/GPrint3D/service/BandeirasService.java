package com.project.GPrint3D.service;

import com.project.GPrint3D.model.BandeirasModel;
import com.project.GPrint3D.repository.BandeirasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
class BandeirasService
{
    @Autowired
    private BandeirasRepository bandeirasRepository;

    public String[] cadastrar (BandeirasModel bandeira)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            bandeirasRepository.save(bandeira);

            response[0] = msg1;
            response[1] = "Bandeira cadastrada com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Bandeira já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar a bandeira";
        }

        return response;
    }

    public String[] atualizar (BandeirasModel bandeira)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            bandeirasRepository.save(bandeira);

            response[0] = msg1;
            response[1] = "Cadastro de bandeira alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a bandeira";
        }

        return response;
    }

    public String[] ativar (Boolean ativa, Integer id)
    {
        String[] response = new String[2];

        String msg1 = "ativaSuccess";
        String msg2 = "ativaError";

        try
        {
            bandeirasRepository.updadeAtiva(ativa, id);

            response[0] = msg1;
            response[1] = "Cadastro de bandeira alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a bandeira";
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
            bandeirasRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de bandeira deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar a bandeira";
        }

        return response;
    }
}
