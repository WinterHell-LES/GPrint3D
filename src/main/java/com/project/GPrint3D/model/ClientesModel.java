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
	private Integer id;

	@NotEmpty(message = "Tipo é obrigatório")
    @Column(name = "cli_tipo")
	private String tipo;

	@NotEmpty(message = "Nome é obrigatório")
    @Column(name = "cli_nome")
	private String nome;

	@NotEmpty(message = "Sobrenome é obrigatório")
    @Column(name = "cli_sobrenome")
	private String sobrenome;

	@NotEmpty(message = "Sexo é obrigatório")
    @Column(name = "cli_sexo")
	private String sexo;

    @Column(name = "cli_dtnasc")
	private Date dtNasc;

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

	public ClientesModel() 
	{
		super();

		this.id = 0;
		this.nome = "";
		this.tipo = "";
	}

	public ClientesModel(Integer id, String nome, String tipo) 
	{
		super( );

		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
	}

	public Integer getId() 
	{
		return this.id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getTipo() 
	{
		return this.tipo;
	}

	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	public String getNome() 
	{
		return this.nome;
	}

	public void setNome(String nome) 
	{
		this.nome = nome;
	}

	public String getSobrenome() 
	{
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) 
	{
		this.sobrenome = sobrenome;
	}

	public String getSexo() 
	{
		return this.sexo;
	}

	public void setSexo(String sexo) 
	{
		this.sexo = sexo;
	}

	public Date getDtNasc() 
	{
		return this.dtNasc;
	}

	public void setDtNasc(Date dtNasc) 
	{
		this.dtNasc = dtNasc;
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
		return id == clientesModel.id;
	}

	@Override
	public int hashCode() 
	{
		return Objects.hashCode(id);
	}
}
