package com.project.GPrint3D.model;

public class TipoDocumento extends EntidadeDominio 
{
	private static final long serialVersionUID = 1L;

	private String descricao;

	private String nome;

	public TipoDocumento() 
	{
	}

	public String getDescricao() 
	{
		return this.descricao;
	}

	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}

	public String getNome() 
	{
		return this.nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}
}
