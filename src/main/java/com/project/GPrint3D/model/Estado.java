package com.project.GPrint3D.model;

public class Estado extends EntidadeDominio 
{
	private static final long serialVersionUID = 1L;
	
	private String descricao;

	public Estado() 
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
}
