package com.project.GPrint3D.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "USUARIOS")
public class UsuariosModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id", insertable = false, updatable = false)
    private Integer usuId;

    @NotEmpty(message = "Email é obrigatório")
    @Column(name = "usu_email", unique = true)
    private String usuEmail;

    @NotEmpty(message = "Senha é obrigatória")
    @Column(name = "usu_senha")
    private String usuSenha;

    @NotEmpty(message = "Nível de acesso é obrigatório")
    @Column(name = "usu_regra")
    private String usuRegra;

    @Column(name = "usu_ativo")
    private boolean usuAtivo;

    @OneToOne(mappedBy = "usuario")
    private ClientesModel cliente;

    @OneToMany(mappedBy = "usuario")
    private List<EntradasModel> listEntradas;

    public UsuariosModel()
    {
        super();

        this.usuId = 0;
        this.usuEmail = "";
        this.usuSenha = "";
        this.usuRegra = "";
        this.usuAtivo = false;
    }

    public UsuariosModel(Integer usuId, String usuEmail, String usuSenha, String usuRegra, boolean usuAtivo) 
    {
        super();

        this.usuId = usuId;
        this.usuEmail = usuEmail;
        this.usuSenha = usuSenha;
        this.usuRegra = usuRegra;
        this.usuAtivo = usuAtivo;
    }

    public Integer getUsuId() 
    {
        return this.usuId;
    }

    public void setUsuId(Integer usuId) 
    {
        this.usuId = usuId;
    }

    public String getUsuEmail() 
    {
        return this.usuEmail;
    }

    public void setUsuEmail(String usuEmail) 
    {
        this.usuEmail = usuEmail;
    }

    public String getUsuSenha() 
    {
        return this.usuSenha;
    }

    public void setUsuSenha(String usuSenha) 
    {
        this.usuSenha = usuSenha;
    }

    public String getUsuRegra() 
    {
        return this.usuRegra;
    }

    public void setUsuRegra(String usuRegra) 
    {
        this.usuRegra = usuRegra;
    }

    public boolean isUsuAtivo() 
    {
        return this.usuAtivo;
    }

    public boolean getUsuAtivo() 
    {
        return this.usuAtivo;
    }

    public void setUsuAtivo(boolean usuAtivo) 
    {
        this.usuAtivo = usuAtivo;
    }

    public ClientesModel getCliente() 
    {
        return this.cliente;
    }

    public void setCliente(ClientesModel cliente) 
    {
        this.cliente = cliente;
    }

    public List<EntradasModel> getListEntradas() 
    {
        return this.listEntradas;
    }

    public void setListEntradas(List<EntradasModel> listEntradas) 
    {
        this.listEntradas = listEntradas;
    }

    public String getStrUsuAtivo()
    {
        return this.usuAtivo ? "Ativo" : "Inativo";
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this) 
        {
            return true;
        }

        if (!(o instanceof UsuariosModel)) 
        {
            return false;
        }

        UsuariosModel usuariosModel = (UsuariosModel) o;
        return Objects.equals(usuId, usuariosModel.usuId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(usuId);
    }
}
