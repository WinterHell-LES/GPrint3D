package com.project.GPrint3D.service;

import java.sql.Date;

import com.project.GPrint3D.model.PrdCarrinhosModel;
import com.project.GPrint3D.repository.PrdCarrinhosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
class PrdCarrinhosService
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
            response[1] = "Produto adicionado com sucesso";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Produto do carrinho já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao adicionar o produto";
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

    public String[] aumentarQuantidadeProduto (PrdCarrinhosModel prdCarrinho)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        if (prdCarrinho.getPcrQuantidade() > prdCarrinho.getProduto().getPrdQuantidade())
        {
            response[0] = msg2;
            response[1] = "Quantidade de estoque atingida";

            return response;
        }

        try
        {
            prdCarrinhosRepository.saveAndFlush(prdCarrinho);

            response[0] = msg1;
            response[1] = "Produto adicionado com sucesso";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao adicionar o produto";
        }

        return response;
    }

    public String[] diminuirQuantidadeProduto (PrdCarrinhosModel prdCarrinho)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try
        {
            if (prdCarrinho.getPcrQuantidade() > 0)
            {
                prdCarrinhosRepository.saveAndFlush(prdCarrinho);
            }
            else
            {
                prdCarrinhosRepository.deleteByPcrId(prdCarrinho.getPcrId());
            }

            response[0] = msg1;
            response[1] = "Produto removido com sucesso";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao remover o produto";
        }

        return response;
    }

    public String[] atualizarQuantidadeProduto (PrdCarrinhosModel prdCarrinho)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        if (prdCarrinho.getPcrQuantidade() > prdCarrinho.getProduto().getPrdQuantidade())
        {
            response[0] = msg2;
            response[1] = "Quantidade de estoque atingida";

            return response;
        }

        try
        {
            prdCarrinhosRepository.saveAndFlush(prdCarrinho);

            response[0] = msg1;
            response[1] = "Quantidade do produto alterada com sucesso";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a quantidade do produto";
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

            if (status)
            {
                response[1] = "Produto reativado com sucesso, e ajustado para a quantidade do estoque";
            }
            else
            {
                response[1] = "Produto desativado com sucesso";
            }
        }
        catch (Exception e)
        {
            response[0] = msg2;

            if (status)
            {
                response[1] = "Erro ao reativar o produto";
            }
            else
            {
                response[1] = "Erro ao desativar o produto";
            }
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
            response[1] = "Produto reativado com sucesso";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao reativar o produto";
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
            prdCarrinhosRepository.deleteByPcrId(id);

            response[0] = msg1;
            response[1] = "Produto removido com sucesso";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao remover o produto";
        }

        return response;
    }

    public String[] excluirAll (Integer carId)
    {
        String[] response = new String[2];

        String msg1 = "deleteSuccess";
        String msg2 = "deleteError";

        try
        {
            prdCarrinhosRepository.deleteAllByCarId(carId);

            response[0] = msg1;
            response[1] = "Todos os produtos removidos com sucesso";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao remover todos os produtos do carrinho";
        }

        return response;
    }

    public int qntPrdCarrinho (Integer id)
    {
        int quantidade = 0;

        for (PrdCarrinhosModel aux : prdCarrinhosRepository.findAllByProdutoId(id))
        {
            quantidade += aux.getPcrQuantidade();
        }

        return quantidade;
    }
}
