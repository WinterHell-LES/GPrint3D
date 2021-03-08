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
	private Integer endId;

    @Column(name = "end_entrega")
	private boolean endEntrega;

    @Column(name = "end_ent_padrao")
	private boolean endEntregaPadrao;

    @Column(name = "end_cobranca")
	private boolean endCobranca;

    @Column(name = "end_cob_padrao")
	private boolean endCobrancaPadrao;

	@NotEmpty(message = "Descrição é obrigatória")
    @Column(name = "end_descricao")
	private String endDescricao;

	@NotEmpty(message = "Tipo é obrigatório")
    @Column(name = "end_tipolog")
	private String endTipo;

	@NotEmpty(message = "Logradouro é obrigatório")
    @Column(name = "end_logradouro")
	private String endLogradouro;

	@NotEmpty(message = "Número é obrigatório")
    @Column(name = "end_numero")
	private String endNumero;

	@NotEmpty(message = "CEP é obrigatório")
    @Column(name = "end_cep")
	private String endCep;

	@Column(name = "end_complemento")
	private String endComplemento;

	@NotEmpty(message = "Cidade é obrigatória")
    @Column(name = "end_cidade")
	private String endCidade;

	@NotEmpty(message = "Estado é obrigatório")
    @Column(name = "end_estado")
	private String endEstado;

	@NotEmpty(message = "País é obrigatório")
    @Column(name = "end_pais")
	private String endPais;

    @Column(name = "end_observacao")
	private String endObservacao;

	@ManyToOne
    @JoinColumn(name = "end_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

	public EnderecosModel() 
	{
		super();
		
		this.endId = 0;
		this.endEntrega = false;
		this.endEntregaPadrao = false;
		this.endCobranca = false;
		this.endCobrancaPadrao = false;
		this.endDescricao = "";
		this.endTipo = "";
		this.endLogradouro = "";
		this.endNumero = "";
		this.endCep = "";
		this.endComplemento = "";
		this.endCidade = "";
		this.endEstado = "";
		this.endPais = "";
		this.endObservacao = "";
	}

	public EnderecosModel(Integer endId, boolean endEntrega, boolean endEntregaPadrao, boolean endCobranca, boolean endCobrancaPadrao, String endDescricao, String endTipo,
			String endLogradouro, String endNumero, String endCep, String endComplemento, String endCidade, String endEstado, String endPais,
			String endObservacao) 
	{
		super( );

		this.endId = endId;
		this.endEntrega = endEntrega;
		this.endEntregaPadrao = endEntregaPadrao;
		this.endCobranca = endCobranca;
		this.endCobrancaPadrao = endCobrancaPadrao;
		this.endDescricao = endDescricao;
		this.endTipo = endTipo;
		this.endLogradouro = endLogradouro;
		this.endNumero = endNumero;
		this.endCep = endCep;
		this.endComplemento = endComplemento;
		this.endCidade = endCidade;
		this.endEstado = endEstado;
		this.endPais = endPais;
		this.endObservacao = endObservacao;
	}

	public Integer getEndId() 
	{
		return this.endId;
	}

	public void setEndId(Integer endId) 
	{
		this.endId = endId;
	}

	public boolean isEndEntrega() 
	{
		return this.endEntrega;
	}

	public boolean getEndEntrega() 
	{
		return this.endEntrega;
	}

	public void setEndEntrega(boolean endEntrega) 
	{
		this.endEntrega = endEntrega;
	}

	public boolean isEndEntregaPadrao() 
	{
		return this.endEntregaPadrao;
	}

	public boolean getEndEntregaPadrao() 
	{
		return this.endEntregaPadrao;
	}

	public void setEndEntregaPadrao(boolean endEntregaPadrao) 
	{
		this.endEntregaPadrao = endEntregaPadrao;
	}

	public boolean isEndCobranca() 
	{
		return this.endCobranca;
	}

	public boolean getEndCobranca() 
	{
		return this.endCobranca;
	}

	public void setEndCobranca(boolean endCobranca) 
	{
		this.endCobranca = endCobranca;
	}

	public boolean isEndCobrancaPadrao() 
	{
		return this.endCobrancaPadrao;
	}

	public boolean getEndCobrancaPadrao() 
	{
		return this.endCobrancaPadrao;
	}

	public void setEndCobrancaPadrao(boolean endCobrancaPadrao) 
	{
		this.endCobrancaPadrao = endCobrancaPadrao;
	}

	public String getEndDescricao() 
	{
		return this.endDescricao;
	}

	public void setEndDescricao(String endDescricao) 
	{
		this.endDescricao = endDescricao;
	}

	public String getEndTipo() 
	{
		return this.endTipo;
	}

	public void setEndTipo(String endTipo) 
	{
		this.endTipo = endTipo;
	}

	public String getEndLogradouro() 
	{
		return this.endLogradouro;
	}

	public void setEndLogradouro(String endLogradouro) 
	{
		this.endLogradouro = endLogradouro;
	}

	public String getEndNumero() 
	{
		return this.endNumero;
	}

	public void setEndNumero(String endNumero) 
	{
		this.endNumero = endNumero;
	}

	public String getEndCep() 
	{
		return this.endCep;
	}

	public void setEndCep(String endCep) 
	{
		this.endCep = endCep;
	}

	public String getEndComplemento() 
	{
		return this.endComplemento;
	}

	public void setEndComplemento(String endComplemento) 
	{
		this.endComplemento = endComplemento;
	}

	public String getEndCidade() 
	{
		return this.endCidade;
	}

	public void setEndCidade(String endCidade) 
	{
		this.endCidade = endCidade;
	}

	public String getEndEstado() 
	{
		return this.endEstado;
	}

	public void setEndEstado(String endEstado) 
	{
		this.endEstado = endEstado;
	}

	public String getEndPais() 
	{
		return this.endPais;
	}

	public void setEndPais(String endPais) 
	{
		this.endPais = endPais;
	}

	public String getEndObservacao() 
	{
		return this.endObservacao;
	}

	public void setEndObservacao(String endObservacao) 
	{
		this.endObservacao = endObservacao;
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
		return endId == enderecosModel.endId;
	}

	@Override
	public int hashCode() 
	{
		return Objects.hashCode(endId);
	}
}
