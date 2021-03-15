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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "HISTORICOS_CUPONS_TROCAS")
public class HistCuponsTrocasModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hct_id")
    private Integer hctId;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "hct_data")
    private Date hctData;

    @NotNull(message = "Saldo é obrigatório")
    @Column(name = "hct_saldo")
    private double hctSaldo;

    @ManyToOne
    @JoinColumn(name = "hct_cpt_id", referencedColumnName = "cpt_id")
    private CuponsTrocasModel cupomTroca;

    public HistCuponsTrocasModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.hctId = 0;
        this.hctData = new Date(dataAtual.getTime());
        this.hctSaldo = 0.0;
    }

    public HistCuponsTrocasModel(Integer hctId, Date hctData, double hctSaldo) 
    {
        this.hctId = hctId;
        this.hctData = hctData;
        this.hctSaldo = hctSaldo;
    }

    public Integer getHctId() 
    {
        return this.hctId;
    }

    public void setHctId(Integer hctId) 
    {
        this.hctId = hctId;
    }

    public Date getHctData() 
    {
        return this.hctData;
    }

    public void setHctData(Date hctData) 
    {
        this.hctData = hctData;
    }

    public double getHctSaldo() 
    {
        return this.hctSaldo;
    }

    public void setHctSaldo(double hctSaldo) 
    {
        this.hctSaldo = hctSaldo;
    }

    public CuponsTrocasModel getCupomTroca() 
    {
        return this.cupomTroca;
    }

    public void setCupomTroca(CuponsTrocasModel cupomTroca) 
    {
        this.cupomTroca = cupomTroca;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof HistCuponsTrocasModel)) 
        {
            return false;
        }
        
        HistCuponsTrocasModel histCuponsTrocasModel = (HistCuponsTrocasModel) o;
        return Objects.equals(hctId, histCuponsTrocasModel.hctId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(hctId);
    }
}
