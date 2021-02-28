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
	private Integer docId;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "doc_nome")
	private String docNome;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "doc_numero")
	private String docNumero;

	@Column(name = "doc_validade")
	private Date docValidade;

	@ManyToOne
    @JoinColumn(name = "doc_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

	public DocumentosModel() 
	{
		super();
		
		this.docId = 0;
		this.docNome = "";
		this.docNumero = "";
		this.docValidade = null;
	}

	public DocumentosModel(Integer docId, String docNome, String docNumero, Date docValidade) 
	{
		super( );

		this.docId = docId;
		this.docNome = docNome;
		this.docNumero = docNumero;
		this.docValidade = docValidade;
	}

	public Integer getDocId() 
	{
		return this.docId;
	}

	public void setDocId(Integer docId) 
	{
		this.docId = docId;
	}

	public String getDocNome() 
	{
		return this.docNome;
	}

	public void setDocNome(String docNome) 
	{
		this.docNome = docNome;
	}

	public String getDocNumero() 
	{
		return this.docNumero;
	}

	public void setDocNumero(String docNumero) 
	{
		this.docNumero = docNumero;
	}

	public Date getDocValidade() 
	{
		return this.docValidade;
	}

	public void setDocValidade(Date docValidade) 
	{
		this.docValidade = docValidade;
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
		return Objects.equals(docId, documentosModel.docId);
	}

	@Override
	public int hashCode() 
	{
		return Objects.hashCode(docId);
	}
}
