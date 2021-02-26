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
@Table(name = "ENDERECOS")
public class EnderecosModel
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id", insertable = false, updatable = false)
	private Integer id;

    @Column(name = "end_entrega")
	private boolean entrega;

    @Column(name = "end_cobranca")
	private boolean cobranca;

	@NotEmpty(message = "Descrição é obrigatória")
    @Column(name = "end_descricao")
	private String descricao;

	@NotEmpty(message = "Tipo é obrigatório")
    @Column(name = "end_tipolog")
	private String tipo;

	@NotEmpty(message = "Logradouro é obrigatório")
    @Column(name = "end_logradouro")
	private String logradouro;

	@NotEmpty(message = "Número é obrigatório")
    @Column(name = "end_numero")
	private String numero;

	@NotEmpty(message = "CEP é obrigatório")
    @Column(name = "end_cep")
	private String cep;

	@Column(name = "end_complemento")
	private String complemento;

	@NotEmpty(message = "Cidade é obrigatória")
    @Column(name = "end_cidade")
	private String cidade;

	@NotEmpty(message = "Estado é obrigatório")
    @Column(name = "end_estado")
	private String estado;

	@NotEmpty(message = "País é obrigatório")
    @Column(name = "end_pais")
	private String pais;

    @Column(name = "end_observacao")
	private String observacao;

	@ManyToOne
    @JoinColumn(name = "end_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

	public EnderecosModel() 
	{
		this.id = 0;
		this.entrega = false;
		this.cobranca = false;
		this.descricao = "";
		this.tipo = "";
		this.logradouro = "";
		this.numero = "";
		this.cep = "";
		this.complemento = "";
		this.cidade = "";
		this.estado = "";
		this.pais = "";
		this.observacao = "";
	}

	public EnderecosModel(Integer id, boolean entrega, boolean cobranca, String descricao, String tipo, String logradouro, String numero, String cep, String complemento, String cidade, String estado, String pais, String observacao) 
	{
		this.id = id;
		this.entrega = entrega;
		this.cobranca = cobranca;
		this.descricao = descricao;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
		this.complemento = complemento;
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
		this.observacao = observacao;
	}

	public Integer getId() 
	{
		return this.id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public boolean isEntrega() 
	{
		return this.entrega;
	}

	public boolean getEntrega() 
	{
		return this.entrega;
	}

	public void setEntrega(boolean entrega) 
	{
		this.entrega = entrega;
	}

	public boolean isCobranca() 
	{
		return this.cobranca;
	}

	public boolean getCobranca() 
	{
		return this.cobranca;
	}

	public void setCobranca(boolean cobranca) 
	{
		this.cobranca = cobranca;
	}

	public String getDescricao() 
	{
		return this.descricao;
	}

	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}

	public String getTipo() 
	{
		return this.tipo;
	}

	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
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

	public String getCidade() 
	{
		return this.cidade;
	}

	public void setCidade(String cidade) 
	{
		this.cidade = cidade;
	}

	public String getEstado() 
	{
		return this.estado;
	}

	public void setEstado(String estado) 
	{
		this.estado = estado;
	}

	public String getPais() 
	{
		return this.pais;
	}

	public void setPais(String pais) 
	{
		this.pais = pais;
	}

	public String getObservacao() 
	{
		return this.observacao;
	}

	public void setObservacao(String observacao) 
	{
		this.observacao = observacao;
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
			
		if (!(o instanceof EnderecosModel)) 
		{
			return false;
		}
		EnderecosModel enderecosModel = (EnderecosModel) o;
		return id == enderecosModel.id;
	}

	@Override
	public int hashCode() 
	{
		return Objects.hashCode(id);
	}

}
