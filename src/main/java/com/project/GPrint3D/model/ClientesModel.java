package com.project.GPrint3D.model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "CLIENTES")
public class ClientesModel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id", insertable = false, updatable = false)
	private Integer cliId;

	@NotEmpty(message = "Raking é obrigatório")
    @Column(name = "cli_ranking")
	private String cliRanking;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "cli_nome")
	private String cliNome;

	@NotEmpty(message = "Sexo é obrigatório")
    @Column(name = "cli_sexo")
	private String cliSexo;

    @Column(name = "cli_dtnasc")
	private Date cliDtNasc;

	@OneToOne
    @JoinColumn(name = "cli_usu_id", referencedColumnName = "usu_id")
    private UsuariosModel usuario;

	@OneToMany(mappedBy = "cliente")
    private List<DocumentosModel> listDocumento;

	@OneToMany(mappedBy = "cliente")
    private List<EnderecosModel> listEndereco;

	@OneToMany(mappedBy = "cliente")
    private List<CartoesModel> listCartao;

	@OneToMany(mappedBy = "cliente")
    private List<TelefonesModel> listTelefone;

	@OneToOne(mappedBy = "cliente")
	private CartoesPadroesModel cartaoPadrao;

	@OneToOne(mappedBy = "cliente")
	private EndEntregasPadroesModel endEntregaPadrao;

	@OneToOne(mappedBy = "cliente")
	private EndCobrancasPadroesModel endCobrancaPadrao;

	public ClientesModel() 
	{
		super();

		this.cliId = 0;
		this.cliRanking = "";
		this.cliNome = "";
		this.cliSexo = "";
		this.cliDtNasc = null;
	}

	public ClientesModel(Integer cliId, String cliRanking, String cliNome, String cliSexo, Date cliDtNasc) 
	{
		super( );

		this.cliId = cliId;
		this.cliRanking = cliRanking;
		this.cliNome = cliNome;
		this.cliSexo = cliSexo;
		this.cliDtNasc = cliDtNasc;
	}

	public Integer getCliId() 
	{
		return this.cliId;
	}

	public void setCliId(Integer cliId) 
	{
		this.cliId = cliId;
	}

	public String getCliRanking() 
	{
		return this.cliRanking;
	}

	public void setCliRanking(String cliRanking) 
	{
		this.cliRanking = cliRanking;
	}

	public String getCliNome() 
	{
		return this.cliNome;
	}

	public void setCliNome(String cliNome) 
	{
		this.cliNome = cliNome;
	}

	public String getCliSexo() 
	{
		return this.cliSexo;
	}

	public void setCliSexo(String cliSexo) 
	{
		this.cliSexo = cliSexo;
	}

	public Date getCliDtNasc() 
	{
		return this.cliDtNasc;
	}

	public void setCliDtNasc(Date cliDtNasc) 
	{
		this.cliDtNasc = cliDtNasc;
	}

	public UsuariosModel getUsuario() 
	{
		return this.usuario;
	}

	public void setUsuario(UsuariosModel usuario) 
	{
		this.usuario = usuario;
	}

	public List<DocumentosModel> getListDocumento() 
	{
		return this.listDocumento;
	}

	public void setListDocumento(List<DocumentosModel> listDocumento) 
	{
		this.listDocumento = listDocumento;
	}

	public List<EnderecosModel> getListEndereco() 
	{
		return this.listEndereco;
	}

	public void setListEndereco(List<EnderecosModel> listEndereco) 
	{
		this.listEndereco = listEndereco;
	}

	public List<CartoesModel> getListCartao() 
	{
		return this.listCartao;
	}

	public void setListCartao(List<CartoesModel> listCartao) 
	{
		this.listCartao = listCartao;
	}

	public List<TelefonesModel> getListTelefone() 
	{
		return this.listTelefone;
	}

	public void setListTelefone(List<TelefonesModel> listTelefone) 
	{
		this.listTelefone = listTelefone;
	}

	public CartoesPadroesModel getCartaoPadrao() 
	{
		return this.cartaoPadrao;
	}

	public void setCartaoPadrao(CartoesPadroesModel cartaoPadrao) 
	{
		this.cartaoPadrao = cartaoPadrao;
	}

	public EndEntregasPadroesModel getEndEntregaPadrao() {
		return this.endEntregaPadrao;
	}

	public void setEndEntregaPadrao(EndEntregasPadroesModel endEntregaPadrao) {
		this.endEntregaPadrao = endEntregaPadrao;
	}

	public EndCobrancasPadroesModel getEndCobrancaPadrao() {
		return this.endCobrancaPadrao;
	}

	public void setEndCobrancaPadrao(EndCobrancasPadroesModel endCobrancaPadrao) {
		this.endCobrancaPadrao = endCobrancaPadrao;
	}

	@Override
	public boolean equals(Object o) 
	{
		if (o == this) 
		{
			return true;
		}

		if (!(o instanceof ClientesModel)) 
		{
			return false;
		}
		ClientesModel clientesModel = (ClientesModel) o;
		return cliId == clientesModel.cliId;
	}

	@Override
	public int hashCode() 
	{
		return Objects.hashCode(cliId);
	}
}
