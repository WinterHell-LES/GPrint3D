package com.project.GPrint3D.service;

import java.sql.Date;

import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.repository.PrdCarrinhosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PrdCarrinhosService
{
    @Autowired
    private PrdCarrinhosRepository prdCarrinhosRepository;

    public String[] cadastrar (PrdCarrinhosModel prdCarrinho)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            prdCarrinhosRepository.saveAndFlush(prdCarrinho);

            response[0] = msg1;
            response[1] = "Produto do carrinho cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Produto do carrinho já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o produto do carrinho";
        }

        return response;
    }

    public String[] atualizar (PrdCarrinhosModel prdCarrinho)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            prdCarrinhosRepository.saveAndFlush(prdCarrinho);

            response[0] = msg1;
            response[1] = "Cadastro do produto do carrinho alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o produto do carrinho";
        }

        return response;
    }

    public String[] atualizarStatusPrdCarrinhos (Integer quantidade, boolean status, Integer id)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            prdCarrinhosRepository.updateStatusPrdCarrinho(quantidade, status, id);

            response[0] = msg1;
            response[1] = "Cadastro do produto do carrinho alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o produto do carrinho";
        }

        return response;
    }

    public String[] atualizarStatusAtivaPrdCarrinhos (Integer quantidade, boolean status, Date data, Integer id)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            prdCarrinhosRepository.updateStatusAtivaPrdCarrinho(quantidade, status, data, id);

            response[0] = msg1;
            response[1] = "Cadastro do produto do carrinho alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o produto do carrinho";
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
            prdCarrinhosRepository.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro do produto do carrinho deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o produto do carrinho";
        }

        return response;
    }
}
