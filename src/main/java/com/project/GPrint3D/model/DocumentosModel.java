package com.project.GPrint3D.model;

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
    @Column(name = "doc_tipo")
	private String docTipo;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "doc_numero")
	private String docNumero;

	@ManyToOne
    @JoinColumn(name = "doc_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

	public DocumentosModel() 
	{
		super();
		
		this.docId = 0;
		this.docTipo = "";
		this.docNumero = "";
	}

	public DocumentosModel(Integer docId, String docNome, String docNumero) {
		super();

		this.docId = docId;
		this.docTipo = docNome;
		this.docNumero = docNumero;
	}

	public Integer getDocId() 
	{
		return this.docId;
	}

	public void setDocId(Integer docId) 
	{
		this.docId = docId;
	}

	public String getDocTipo() 
	{
		return this.docTipo;
	}

	public void setDocTipo(String docTipo) 
	{
		this.docTipo = docTipo;
	}

	public String getDocNumero() 
	{
		return this.docNumero;
	}

	public void setDocNumero(String docNumero) 
	{
		this.docNumero = docNumero;
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
