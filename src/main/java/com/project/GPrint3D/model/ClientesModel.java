package com.project.GPrint3D.model;

import java.util.ArrayList;
import java.util.List;

public class ClientesModel extends Pessoa 
{
	private static final long serialVersionUID = 1L;

	private String nome;

	private TipoCliente tipoCliente;

	private List<Endereco> enderecos = new ArrayList<Endereco>(0);

	public ClientesModel() 
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

	public TipoCliente getTipoCliente() 
	{
		return this.tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) 
	{
		this.tipoCliente = tipoCliente;
	}

	public List<Endereco> getEnderecos() 
	{
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) 
	{
		this.enderecos = enderecos;
	}
}
