package com.project.GPrint3D.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "DOCUMENTOS")
public class DocumentosModel
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id", insertable = false, updatable = false)
	private Integer id;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "doc_nome")
	private String nome;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "doc_numero")
	private String numero;

	@Column(name = "doc_validade")
	private Date validade;

	@ManyToOne
    @JoinColumn(name = "doc_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

	public DocumentosModel() 
	{
		this.id = 0;
		this.nome = "";
		this.numero = "";
		this.validade = null;
	}

	public DocumentosModel(Integer id, String nome, String numero, Date validade) 
	{
		this.id = id;
		this.nome = nome;
		this.numero = numero;
		this.validade = validade;
	}

	public Integer getId() 
	{
		return this.id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getNome() 
	{
		return this.nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public String getNumero() 
	{
		return this.numero;
	}

	public void setNumero(String numero) 
	{
		this.numero = numero;
	}

	public Date getValidade() 
	{
		return this.validade;
	}

	public void setValidade(Date validade) 
	{
		this.validade = validade;
	}

	public ClientesModel getCliente() 
	{
		return this.cliente;
	}

	public void setCliente(ClientesModel cliente) 
	{
		this.cliente = cliente;
	}

	@Override
	public boolean equals(Object o) 
	{
		if (o == this)
		{
			return true;
		}
			
		if (!(o instanceof DocumentosModel)) 
		{
			return false;
		}
		DocumentosModel documentosModel = (DocumentosModel) o;
		return Objects.equals(id, documentosModel.id);
	}

	@Override
	public int hashCode() 
	{
		return Objects.hashCode(id);
	}
}
