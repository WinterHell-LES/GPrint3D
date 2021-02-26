package com.project.GPrint3D.service;

import com.project.GPrint3D.model.ClientesModel;
import com.project.GPrint3D.repository.ClientesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ClientesService
{
    @Autowired
    private ClientesRepository clientes;

    public String[] cadastrar(ClientesModel cliente)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        if (cliente.getUsuario().getId().equals(0))
        {
            cliente.setUsuario(null);
        }

        try 
        {
            clientes.save(cliente);

            response[0] = msg1;
            response[1] = "Cliente cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Cliente já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o cliente";
        }

        return response;
    }

    public String[] atualizar(ClientesModel cliente)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        if (cliente.getUsuario().getId().equals(0))
        {
            cliente.setUsuario(null);
        }

        try 
        {
            clientes.save(cliente);

            response[0] = msg1;
            response[1] = "Cadastro de cliente alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o cliente";
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
            clientes.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de cliente deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o cliente";
        }
        
        return response;
    }
}