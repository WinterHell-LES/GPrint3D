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
@Table(name = "CARTOES")
public class CartoesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crt_id", insertable = false, updatable = false)
    private Integer crtId;

    @NotEmpty(message = "Bandeira é obrigatória")
    @Column(name = "crt_bandeira")
    private String crtBandeira;

    @NotEmpty(message = "Nome é obrigatório")
    @Column(name = "crt_nome")
    private String crtNome;

    @NotEmpty(message = "Número é obrigatório")
    @Column(name = "crt_numero")
    private String crtNumero;

    @NotEmpty(message = "Validade é obrigatória")
    @Column(name = "crt_validade")
    private Date crtValidade;

    @NotEmpty(message = "CVV é obrigatório")
    @Column(name = "crt_cvv")
    private String crtCvv;

    @ManyToOne
    @JoinColumn(name = "crt_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    public CartoesModel() 
    {
        super();

        this.crtId = 0;
        this.crtBandeira = "";
        this.crtNome = "";
        this.crtNumero = "";
        this.crtValidade = null;
        this.crtCvv = "";
    }

    public CartoesModel(Integer crtId, String crtBandeira, String crtNome, String crtNumero, Date crtValidade, String crtCvv) 
    {
        super( );

        this.crtId = crtId;
        this.crtBandeira = crtBandeira;
        this.crtNome = crtNome;
        this.crtNumero = crtNumero;
        this.crtValidade = crtValidade;
        this.crtCvv = crtCvv;
    }

    public Integer getCrtId() 
    {
        return this.crtId;
    }

    public void setCrtId(Integer crtId) 
    {
        this.crtId = crtId;
    }

    public String getCrtBandeira() 
    {
        return this.crtBandeira;
    }

    public void setCrtBandeira(String crtBandeira) 
    {
        this.crtBandeira = crtBandeira;
    }

    public String getCrtNome() 
    {
        return this.crtNome;
    }

    public void setCrtNome(String crtNome) 
    {
        this.crtNome = crtNome;
    }

    public String getCrtNumero() 
    {
        return this.crtNumero;
    }

    public void setCrtNumero(String crtNumero) 
    {
        this.crtNumero = crtNumero;
    }

    public Date getCrtValidade() 
    {
        return this.crtValidade;
    }

    public void setCrtValidade(Date crtValidade) 
    {
        this.crtValidade = crtValidade;
    }

    public String getCrtCvv() 
    {
        return this.crtCvv;
    }

    public void setCrtCvv(String crtCvv) 
    {
        this.crtCvv = crtCvv;
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

        if (!(o instanceof CartoesModel)) 
        {
            return false;
        }
        CartoesModel cartoesModel = (CartoesModel) o;
        return Objects.equals(crtId, cartoesModel.crtId);
    }

    @Override
    public int hashCode() 
    
    {
        return Objects.hashCode(crtId);
    }
}