package com.project.GPrint3D.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "USUARIOS")
public class UsuariosModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id", insertable = false, updatable = false)
    private Integer id;

    @NotEmpty(message = "Email é obrigatório")
    @Column(name = "usu_email", unique = true)
    private String email;

    @NotEmpty(message = "Senha é obrigatória")
    @Column(name = "usu_senha")
    private String senha;

    @NotEmpty(message = "Confirmação de senha é obrigatória")
    private String confirmSenha;

    @NotEmpty(message = "Nível de acesso é obrigatório")
    @Column(name = "usu_regra")
    private String regra;

    @Column(name = "usu_ativo")
    private boolean ativo;

    public UsuariosModel()
    {
        this.id = 0;
        this.email = "";
        this.senha = "";
        this.confirmSenha = "";
        this.regra = "";
        this.ativo = false;
    }

    public UsuariosModel(Integer id, String email, String senha, String confirmSenha, String regra, boolean ativo)
    {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.confirmSenha = confirmSenha;
        this.regra = regra;
        this.ativo = ativo;
    }

    public Integer getId() 
    {
        return this.id;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public String getEmail() 
    {
        return this.email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getSenha() 
    {
        return this.senha;
    }

    public void setSenha(String senha) 
    {
        this.senha = senha;
    }

    public String getConfirmSenha() 
    {
        return this.confirmSenha;
    }

    public void setConfirmSenha(String confirmSenha) 
    {
        this.confirmSenha = confirmSenha;
    }

    public String getRegra() 
    {
        return this.regra;
    }

    public void setRegra(String regra) 
    {
        this.regra = regra;
    }

    public boolean isAtivo() 
    {
        return this.ativo;
    }

    public boolean getAtivo() 
    {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) 
    {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }

        if (!(o instanceof UsuariosModel)) 
        {
            return false;
        }

        UsuariosModel usuariosModel = (UsuariosModel) o;
        return Objects.equals(id, usuariosModel.id);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(id);
    }
}
