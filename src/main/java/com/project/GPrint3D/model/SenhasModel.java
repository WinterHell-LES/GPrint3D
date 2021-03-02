package com.project.GPrint3D.model;

public class SenhasModel
{
    private String senha;

    public SenhasModel()
    {
        super();

        this.senha = "";
    }

    public SenhasModel(String senha) 
    {
        super();

        this.senha = senha;
    }

    public String getSenha() 
    {
        return this.senha;
    }

    public void setSenha(String senha) 
    {
        this.senha = senha;
    }
}
