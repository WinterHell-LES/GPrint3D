package com.project.GPrint3D.model;

import java.io.Serializable;
import java.util.Date;

public class EntidadeDominio implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int id;

	private Date dtCadastro;

	public int getId() 
	{
		return this.id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public Date getDtCadastro() 
	{
		return this.dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) 
	{
		this.dtCadastro = dtCadastro;
	}
}
