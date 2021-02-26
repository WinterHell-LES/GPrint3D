package com.project.GPrint3D.service;

import com.project.GPrint3D.configuration.SecurityConfig;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService 
{
    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private SecurityConfig securityConfig;

    public String[] cadastrar(UsuariosModel usuario)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try 
        {
            usuario.setSenha(securityConfig.passwordEncoder().encode(usuario.getSenha()));

            usuarios.save(usuario);

            response[0] = msg1;
            response[1] = "Usuário cadastrado com sucesso!";
        }
        catch (DataIntegrityViolationException e)
        {
            response[0] = msg2;
            response[1] = "Usuário já cadastrado";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao cadastrar o usuário";
        }

        return response;
    }

    public String[] atualizar(UsuariosModel usuario)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            usuarios.save(usuario);

            response[0] = msg1;
            response[1] = "Cadastro de usuário alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar o usuário";
        }
        
        return response;
    }

    public String[] atualizarPass(UsuariosModel usuario)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        try 
        {
            usuario.setSenha(securityConfig.passwordEncoder().encode(usuario.getSenha()));

            usuarios.updatePass(usuario.getSenha(), usuario.getId());

            response[0] = msg1;
            response[1] = "Cadastro de senha alterado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao alterar a senha";
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
            usuarios.deleteById(id);

            response[0] = msg1;
            response[1] = "Cadastro de usuário deletado com sucesso!";
        }
        catch (Exception e)
        {
            response[0] = msg2;
            response[1] = "Erro ao deletar o usuário";
        }
        
        return response;
    }
}