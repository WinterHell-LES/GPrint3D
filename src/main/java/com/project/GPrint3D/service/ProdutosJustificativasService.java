package com.project.GPrint3D.service;

import com.project.GPrint3D.model.ProdutosJustificativasModel;
import com.project.GPrint3D.repository.ProdutosJustificativasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProdutosJustificativasService
{
    @Autowired
    private ProdutosJustificativasRepository produtosJustificativasRepository;

    public String[] cadastrar (ProdutosJustificativasModel produtosJustificativa)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            produtosJustificativasRepository.save(produtosJustificativa);

            response[0] = msg1;
            response[1] = "Justificativa cadastrada com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Justificativa já cadastrada";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar a justificativa";
        }

        return response;
    }

    public String[] atualizar (ProdutosJustificativasModel produtosJustificativa)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            produtosJustificativasRepository.save(produtosJustificativa);

            response[0] = msg1;
            response[1] = "Cadastro de justificativa alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a justificativa";
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
            produtosJustificativasRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de justificativa deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar a justificativa";
        }

        return response;
    }
}
