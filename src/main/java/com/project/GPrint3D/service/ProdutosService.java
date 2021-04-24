package com.project.GPrint3D.service;

import com.project.GPrint3D.model.ProdutosModel;
import com.project.GPrint3D.repository.ProdutosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProdutosService
{
    @Autowired
    private ProdutosRepository produtosRepository;

    public String[] cadastrar (ProdutosModel produto)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            produtosRepository.save(produto);

            response[0] = msg1;
            response[1] = "Produto cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Produto já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o produto";
        }

        return response;
    }

    public String[] atualizar (ProdutosModel produto)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            produtosRepository.save(produto);

            response[0] = msg1;
            response[1] = "Cadastro do produto alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o produto";
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
            produtosRepository.updadeAtiva(ativa, id);

            response[0] = msg1;
            response[1] = "Cadastro de produto alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a produto";
        }

        return response;
    }

    public String[] atualizarQuantidade (Integer quantidade, Integer id)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            produtosRepository.updadeQuantidade(quantidade, id);

            response[0] = msg1;
            response[1] = "Cadastro de produto alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a produto";
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
            produtosRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro do produto deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o produto";
        }

        return response;
    }
}