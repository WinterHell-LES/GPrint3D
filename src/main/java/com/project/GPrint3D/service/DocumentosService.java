package com.project.GPrint3D.service;

import com.project.GPrint3D.model.DocumentosModel;
import com.project.GPrint3D.repository.DocumentosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class DocumentosService
{
    @Autowired
    private DocumentosRepository documentos;

    public String[] cadastrar(DocumentosModel documento)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            documentos.save(documento);

            response[0] = msg1;
            response[1] = "Documento cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Documento já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o documento";
        }

        return response;
    }

    public String[] atualizar(DocumentosModel documento)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            documentos.save(documento);

            response[0] = msg1;
            response[1] = "Cadastro de documento alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o documento";
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
            documentos.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de documento deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o documento";
        }
        
        return response;
    }
}