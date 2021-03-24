package com.project.GPrint3D.service;

import com.project.GPrint3D.model.CategoriasProdutosModel;
import com.project.GPrint3D.repository.CategoriasProdutosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriasProdutosService 
{
    @Autowired
    private CategoriasProdutosRepository categoriasProdutos;

    public String[] cadastrar(CategoriasProdutosModel categoriaProduto)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            categoriasProdutos.save(categoriaProduto);

            response[0] = msg1;
            response[1] = "Categoria cadastrada com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Categoria já cadastrada";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar a categoria";
        }

        return response;
    }

    public String[] atualizar(CategoriasProdutosModel categoriaProduto)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            categoriasProdutos.save(categoriaProduto);

            response[0] = msg1;
            response[1] = "Cadastro de categoria alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a categoria";
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
            categoriasProdutos.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de categoria deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar a categoria";
        }
        
        return response;
    }
}
