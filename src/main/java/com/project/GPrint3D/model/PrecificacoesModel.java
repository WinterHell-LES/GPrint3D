package com.project.GPrint3D.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRECIFICACOES")
public class PrecificacoesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prc_id")
    private Integer prcId;

    @NotNull(message = "Despesas variáveis são obrigatórias")
    @Column(name = "prc_desp_var")
    private double prcDespVar;

    @NotNull(message = "Despesas fixas são obrigatórias")
    @Column(name = "prc_desp_fix")
    private double prcDespFix;

    @NotNull(message = "Margem de lucro é obrigatória")
    @Column(name = "prc_marg_luc")
    private double prcMargLuc;

    @OneToMany(mappedBy = "precificacao")
    private List<ProdutosModel> listProdutos;

    public PrecificacoesModel() 
    {
        this.prcId = 0;
        this.prcDespVar = 0.0;
        this.prcDespFix = 0.0;
        this.prcMargLuc = 0.0;
    }

    public PrecificacoesModel(Integer prcId, double prcDespVar, double prcDespFix, double prcMargLuc) 
    {
        this.prcId = prcId;
        this.prcDespVar = prcDespVar;
        this.prcDespFix = prcDespFix;
        this.prcMargLuc = prcMargLuc;
    }

    public Integer getPrcId() 
    {
        return this.prcId;
    }

    public void setPrcId(Integer prcId) 
    {
        this.prcId = prcId;
    }

    public double getPrcDespVar() 
    {
        return this.prcDespVar;
    }

    public void setPrcDespVar(double prcDespVar) 
    {
        this.prcDespVar = prcDespVar;
    }

    public double getPrcDespFix() 
    {
        return this.prcDespFix;
    }

    public void setPrcDespFix(double prcDespFix) 
    {
        this.prcDespFix = prcDespFix;
    }

    public double getPrcMargLuc() 
    {
        return this.prcMargLuc;
    }

    public void setPrcMargLuc(double prcMargLuc) 
    {
        this.prcMargLuc = prcMargLuc;
    }

    public List<ProdutosModel> getListProdutos() 
    {
        return this.listProdutos;
    }

    public void setListProdutos(List<ProdutosModel> listProdutos) 
    {
        this.listProdutos = listProdutos;
    }

    public String getNome()
    {
        return this.prcDespVar + "% / " + this.prcDespFix + "% / " + this.prcMargLuc + "%";
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof PrecificacoesModel)) 
        {
            return false;
        }

        PrecificacoesModel precificacoesModel = (PrecificacoesModel) o;
        return Objects.equals(prcId, precificacoesModel.prcId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(prcId);
    }
}