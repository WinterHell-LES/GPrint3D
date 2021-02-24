package com.project.GPrint3D.model;

import java.util.Date;

public class Documento extends EntidadeDominio 
{
	private static final long serialVersionUID = 1L;

	private String codigo;

	private Date validade;

	private TipoDocumento tipoDocumento;

	public Documento() 
	{
	}

	public String getCodigo() 
	{
		return this.codigo;
	}

	public void setCodigo(String codigo) 
	{
		this.codigo = codigo;
	}

	public Date getValidade() 
	{
		return this.validade;
	}

	public void setValidade(Date validade) 
	{
		this.validade = validade;
	}

	public TipoDocumento getTipoDocumento() 
	{
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) 
	{
		this.tipoDocumento = tipoDocumento;
	}
}
