package com.project.GPrint3D.service;

import com.project.GPrint3D.model.CarrinhosModel;
import com.project.GPrint3D.repository.CarrinhosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CarrinhosService 
{
    @Autowired
    private CarrinhosRepository carrinhos;

    public String[] cadastrar(CarrinhosModel carrinho)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            carrinhos.saveAndFlush(carrinho);

            response[0] = msg1;
            response[1] = "Carrinho cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Carrinho já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o carrinho";
        }

        return response;
    }

    public String[] atualizar(CarrinhosModel carrinho)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            carrinhos.saveAndFlush(carrinho);

            response[0] = msg1;
            response[1] = "Cadastro de carrinho alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o carrinho";
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
            carrinhos.deleteById(id);
            
            response[0] = msg1;
            response[1] = "Cadastro de carrinho deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o carrinho";
        }
        
        return response;
    }
}
