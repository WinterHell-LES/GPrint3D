package com.project.GPrint3D.service;

import java.util.ArrayList;
import java.util.List;

import com.project.GPrint3D.configuration.SecurityConfig;
import com.project.GPrint3D.model.UsuariosModel;
import com.project.GPrint3D.repository.UsuariosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
class UsuariosService
{
    @Autowired
    private UsuariosRepository usuarios;

    @Autowired
    private SecurityConfig securityConfig;

    public String[] cadastrar (UsuariosModel usuario)
    {
        String[] response = new String[2];

        String msg1 = "cadastroSuccess";
        String msg2 = "cadastroError";

        try
        {
            usuario.setUsuSenha(securityConfig.passwordEncoder().encode(usuario.getUsuSenha()));

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

    public String[] atualizar (UsuariosModel usuario)
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

    public String[] atualizarPass (String newPassword, UsuariosModel usuario)
    {
        String[] response = new String[2];

        String msg1 = "alteracaoSuccess";
        String msg2 = "alteracaoError";

        usuario.setUsuSenha(newPassword);

        try
        {
            usuario.setUsuSenha(securityConfig.passwordEncoder().encode(usuario.getUsuSenha()));

            usuarios.updateSenha(usuario.getUsuSenha(), usuario.getUsuId());

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

    public String[] ativar (Boolean ativa, Integer id)
    {
        String[] response = new String[2];

        String msg1 = "ativaSuccess";
        String msg2 = "ativaError";

        try
        {
            usuarios.updadeAtiva(ativa, id);

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

    public String[] excluir (Integer id)
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

    public List<String> validarSenhaAtualizacao (String oldPassword, String newPassword,
            String confirmNewPassword, UsuariosModel usuario)
    {
        List<String> list = new ArrayList<>();

        list.add("alteracaoError");

        if (!securityConfig.passwordEncoder().matches(oldPassword, usuario.getUsuSenha()))
        {
            list.add("Senha antiga não confere, favor digitar corretamente");

            return list;
        }

        if (!newPassword.equals(confirmNewPassword))
        {
            list.add("Senha nova diferente da confirmação de senha, favor digitar corretamente");

            return list;
        }

        if (!newPassword.matches(".*\\d.*"))
        {
            list.add("A senha deve conter ao menos um número");
        }

        if (!newPassword.matches(".*[a-z].*"))
        {
            list.add("A senha deve conter ao menos uma letra minúscula");
        }

        if (!newPassword.matches(".*[A-Z].*"))
        {
            list.add("A senha deve conter ao menos uma letra maiúscula");
        }

        if (!newPassword.matches(".*[.!$*&@#].*"))
        {
            list.add("A senha deve conter ao menos um caracter especial");
        }

        if (!newPassword.matches("[0-9a-zA-Z.!$*&@#]{8,}"))
        {
            list.add("A senha deve conter ao menos 8 dígitos");
        }

        return list;
    }

    public List<String> validarSenhaNova (String newPassword, String confirmNewPassword)
    {
        List<String> list = new ArrayList<>();

        list.add("alteracaoError");

        if (!newPassword.equals(confirmNewPassword))
        {
            list.add("Senha nova diferente da confirmação de senha, favor digitar corretamente");

            return list;
        }

        if (!newPassword.matches(".*\\d.*"))
        {
            list.add("A senha deve conter ao menos um número");
        }

        if (!newPassword.matches(".*[a-z].*"))
        {
            list.add("A senha deve conter ao menos uma letra minúscula");
        }

        if (!newPassword.matches(".*[A-Z].*"))
        {
            list.add("A senha deve conter ao menos uma letra maiúscula");
        }

        if (!newPassword.matches(".*[.!$*&@#].*"))
        {
            list.add("A senha deve conter ao menos um caracter especial");
        }

        if (!newPassword.matches("[0-9a-zA-Z.!$*&@#]{8,}"))
        {
            list.add("A senha deve conter ao menos 8 dígitos");
        }

        return list;
    }
}
