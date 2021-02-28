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
@Table(name = "TELEFONES")
public class TelefonesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tel_id", insertable = false, updatable = false)
    private Integer telId;

    @NotEmpty(message = "Tipo é obrigatório")
    @Column(name = "tel_tipo")
    private String telTipo;

    @NotEmpty(message = "DDD é obrigatório")
    @Column(name = "tel_ddd")
    private String telDdd;

    @NotEmpty(message = "Número é obrigatório")
    @Column(name = "tel_numero")
    private String telNumero;

    @ManyToOne
    @JoinColumn(name = "tel_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    public TelefonesModel() 
    {
        super();
		
        this.telId = 0;
        this.telTipo = "";
        this.telDdd = "";
        this.telNumero = "";
    }

    public TelefonesModel(Integer telId, String telTipo, String telDdd, String telNumero) 
    {
        super();

        this.telId = telId;
        this.telTipo = telTipo;
        this.telDdd = telDdd;
        this.telNumero = telNumero;
    }

    public Integer getTelId() 
    {
        return this.telId;
    }

    public void setTelId(Integer telId) 
    {
        this.telId = telId;
    }

    public String getTelTipo() 
    {
        return this.telTipo;
    }

    public void setTelTipo(String telTipo) 
    {
        this.telTipo = telTipo;
    }

    public String getTelDdd() 
    {
        return this.telDdd;
    }

    public void setTelDdd(String telDdd) 
    {
        this.telDdd = telDdd;
    }

    public String getTelNumero() 
    {
        return this.telNumero;
    }

    public void setTelNumero(String telNumero) 
    {
        this.telNumero = telNumero;
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

        if (!(o instanceof TelefonesModel)) 
        {
            return false;
        }
        TelefonesModel telefonesModel = (TelefonesModel) o;
        return Objects.equals(telId, telefonesModel.telId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(telId);
    }
}
