package com.project.GPrint3D.model;

public class TipoCliente extends EntidadeDominio 
{
	private static final long serialVersionUID = 1L;

	private String nome;

	private String descricao;

	public TipoCliente() 
	{
	}

	public String getNome() 
	{
		return this.nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public String getDescricao() 
	{
		return this.descricao;
	}

	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
}
