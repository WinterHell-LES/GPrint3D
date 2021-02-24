package com.project.GPrint3D.model;

public class Endereco extends EntidadeDominio 
{
	private static final long serialVersionUID = 1L;

	private String logradouro;

	private String numero;

	private String cep;

	private String complemento;

	private Cidade cidade;

	private TipoEndereco tipoEndereco;

	public Endereco() 
	{
	}

	public String getLogradouro() 
	{
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) 
	{
		this.logradouro = logradouro;
	}

	public String getNumero() 
	{
		return this.numero;
	}

	public void setNumero(String numero) 
	{
		this.numero = numero;
	}

	public String getCep() 
	{
		return this.cep;
	}

	public void setCep(String cep) 
	{
		this.cep = cep;
	}

	public String getComplemento() 
	{
		return this.complemento;
	}

	public void setComplemento(String complemento) 
	{
		this.complemento = complemento;
	}

	public Cidade getCidade() 
	{
		return this.cidade;
	}

	public void setCidade(Cidade cidade) 
	{
		this.cidade = cidade;
	}

	public TipoEndereco getTipoEndereco() 
	{
		return this.tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) 
	{
		this.tipoEndereco = tipoEndereco;
	}
}
