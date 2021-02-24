package com.project.GPrint3D.model;

public class Cidade extends EntidadeDominio 
{
	private static final long serialVersionUID = 1L;

	private String descricao;

	private Estado estado;

	public Cidade() 
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

	public Estado getEstado() 
	{
		return this.estado;
	}

	public void setEstado(Estado estado) 
	{
		this.estado = estado;
	}
}
