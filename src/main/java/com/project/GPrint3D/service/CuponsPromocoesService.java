package com.project.GPrint3D.service;

import com.project.GPrint3D.model.CuponsPromocoesModel;
import com.project.GPrint3D.repository.CuponsPromocoesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CuponsPromocoesService 
{
    @Autowired
    private CuponsPromocoesRepository cupons;

    public String[] cadastrar(CuponsPromocoesModel cupom)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            cupons.save(cupom);

            response[0] = msg1;
            response[1] = "Cupom cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Cupom já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o cupom";
        }

        return response;
    }

    public String[] atualizar(CuponsPromocoesModel cupom)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            cupons.save(cupom);

            response[0] = msg1;
            response[1] = "Cadastro de cupom alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o cupom";
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
            cupons.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de cupom deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o cupom";
        }
        
        return response;
    }    
}
