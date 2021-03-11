package com.project.GPrint3D.service;

import com.project.GPrint3D.model.CategoriasModel;
import com.project.GPrint3D.repository.CategoriasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriasService 
{
    @Autowired
    private CategoriasRepository categorias;

    public String[] cadastrar(CategoriasModel categoria)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            categorias.save(categoria);

            response[0] = msg1;
            response[1] = "categoria cadastrada com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Categoria já cadastrada";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o categoria";
        }

        return response;
    }

    public String[] atualizar(CategoriasModel categoria)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            categorias.save(categoria);

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
            categorias.deleteById(id);

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
