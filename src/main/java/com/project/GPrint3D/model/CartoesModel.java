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
    private Integer id;

    @NotEmpty(message = "Bandeira é obrigatória")
    @Column(name = "crt_bandeira")
    private String bandeira;

    @NotEmpty(message = "Nome é obrigatório")
    @Column(name = "crt_nome")
    private String nome;

    @NotEmpty(message = "Número é obrigatório")
    @Column(name = "crt_numero")
    private String numero;

    @NotEmpty(message = "Validade é obrigatória")
    @Column(name = "crt_validade")
    private Date validade;

    @NotEmpty(message = "CVV é obrigatório")
    @Column(name = "crt_cvv")
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "crt_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    public CartoesModel() 
    {
        this.id = 0;
        this.bandeira = "";
        this.nome = "";
        this.numero = "";
        this.validade = null;
        this.cvv = "";
    }

    public CartoesModel(Integer id, String bandeira, String nome, String numero, Date validade, String cvv) 
    {
        this.id = id;
        this.bandeira = bandeira;
        this.nome = nome;
        this.numero = numero;
        this.validade = validade;
        this.cvv = cvv;
    }

    public Integer getId() 
    {
        return this.id;
    }

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public String getBandeira() 
    {
        return this.bandeira;
    }

    public void setBandeira(String bandeira) 
    {
        this.bandeira = bandeira;
    }

    public String getNome() 
    {
        return this.nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getNumero() 
    {
        return this.numero;
    }

    public void setNumero(String numero) 
    {
        this.numero = numero;
    }

    public Date getValidade() 
    {
        return this.validade;
    }

    public void setValidade(Date validade) 
    {
        this.validade = validade;
    }

    public String getCvv() 
    {
        return this.cvv;
    }

    public void setCvv(String cvv) 
    {
        this.cvv = cvv;
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
        return Objects.equals(id, cartoesModel.id);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(id);
    }
}
