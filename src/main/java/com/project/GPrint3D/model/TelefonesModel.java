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
    private Integer id;

    @NotEmpty(message = "Tipo é obrigatório")
    @Column(name = "tel_tipo")
    private String tipo;

    @NotEmpty(message = "DDD é obrigatório")
    @Column(name = "tel_ddd")
    private String ddd;

    @NotEmpty(message = "Número é obrigatório")
    @Column(name = "tel_numero")
    private String numero;

    @ManyToOne
    @JoinColumn(name = "tel_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    public TelefonesModel() 
    {
        this.id = 0;
        this.tipo = "";
        this.ddd = "";
        this.numero = "";
    }

    public TelefonesModel(Integer id, String tipo, String ddd, String numero) 
    {
        this.id = id;
        this.tipo = tipo;
        this.ddd = ddd;
        this.numero = numero;
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

    public String getDdd() 
    {
        return this.ddd;
    }

    public void setDdd(String ddd) 
    {
        this.ddd = ddd;
    }

    public String getNumero() 
    {
        return this.numero;
    }

    public void setNumero(String numero) 
    {
        this.numero = numero;
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
        return Objects.equals(id, telefonesModel.id);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(id);
    }
}
