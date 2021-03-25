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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CUPONS_TROCAS")
public class CuponsTrocasModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpt_id", insertable = false, updatable = false)
    private Integer cptId;
    
    @NotEmpty(message = "Status é obrigatório")
    @Column(name = "cpt_status")
    private String cptStatus;

    @NotNull(message = "Validade é obrigatória")
    @Column(name = "cpt_validade")
    private Date cptValidade;

    @NotNull(message = "Valor é obrigatório")
    @Column(name = "cpt_valor")
    private double cptValor;

    @Column(name = "cpt_saldo")
    private double cptSaldo;

    @NotNull(message = "Código é obrigatório")
    @Column(name = "cpt_codigo")
    private String cptCodigo;

    @ManyToOne
    @JoinColumn(name = "cpt_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    @OneToMany(mappedBy = "cupomTroca")
    private List<HistCuponsTrocasModel> listHistCuponsTrocas;

    public CuponsTrocasModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.cptId = 0;
        this.cptStatus = "";
        this.cptValidade = new Date(dataAtual.getTime());
        this.cptValor = 0.0;
        this.cptSaldo = 0.0;
        this.cptCodigo = "";
    }

    public CuponsTrocasModel(Integer cptId, String cptStatus, Date cptValidade, double cptValor, double cptSaldo, String cptCodigo) 
    {
        super( );

        this.cptId = cptId;
        this.cptStatus = cptStatus;
        this.cptValidade = cptValidade;
        this.cptValor = cptValor;
        this.cptSaldo = cptSaldo;
        this.cptCodigo = cptCodigo;
    }

    public Integer getCptId() 
    {
        return this.cptId;
    }

    public void setCptId(Integer cptId) 
    {
        this.cptId = cptId;
    }

    public String getCptStatus() 
    {
        return this.cptStatus;
    }

    public void setCptStatus(String cptStatus) 
    {
        this.cptStatus = cptStatus;
    }

    public Date getCptValidade() 
    {
        return this.cptValidade;
    }

    public void setCptValidade(Date cptValidade) 
    {
        this.cptValidade = cptValidade;
    }

    public double getCptValor() 
    {
        return this.cptValor;
    }

    public void setCptValor(double cptValor) 
    {
        this.cptValor = cptValor;
    }

    public double getCptSaldo() 
    {
        return this.cptSaldo;
    }

    public void setCptSaldo(double cptSaldo) 
    {
        this.cptSaldo = cptSaldo;
    }

    public String getCptCodigo() 
    {
        return this.cptCodigo;
    }

    public void setCptCodigo(String cptCodigo) 
    {
        this.cptCodigo = cptCodigo;
    }

    public ClientesModel getCliente() 
    {
        return this.cliente;
    }

    public void setCliente(ClientesModel cliente) 
    {
        this.cliente = cliente;
    }

    public List<HistCuponsTrocasModel> getListHistCuponsTrocas() 
    {
        return this.listHistCuponsTrocas;
    }

    public void setListHistCuponsTrocas(List<HistCuponsTrocasModel> listHistCuponsTrocas) 
    {
        this.listHistCuponsTrocas = listHistCuponsTrocas;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof CuponsTrocasModel)) 
        {
            return false;
        }
        
        CuponsTrocasModel cuponsTrocasModel = (CuponsTrocasModel) o;
        return Objects.equals(cptId, cuponsTrocasModel.cptId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(cptId);
    }
}