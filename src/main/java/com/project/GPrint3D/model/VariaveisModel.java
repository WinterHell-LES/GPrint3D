package com.project.GPrint3D.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SIS_VARIAVEIS")
public class VariaveisModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "var_id", insertable = false, updatable = false)
    private Integer varId;

    @NotEmpty(message = "CEP é obrigatório")
    @Column(name = "var_cep")
    private String varCep;

    @NotNull(message = "Quantidade de categorias é obrigatório")
    @Column(name = "var_categoria")
    private Integer varCategoria;

    @NotNull(message = "Tempo de troca é obrigatório")
    @Column(name = "var_temptroca")
    private Integer varTempTroca;

    @NotNull(message = "Tempo do carrinho é obrigatório")
    @Column(name = "var_tempcarrinho")
    private Integer varTempCarrinho;

    @NotNull(message = "Validade do cupom é obrigatória")
    @Column(name = "var_validcupom")
    private Integer varValidCupom;

    @NotNull(message = "Rank 1 é obrigatório")
    @Column(name = "var_rank_1")
    private Integer varRank1;
    
    @NotNull(message = "Rank 2 é obrigatório")
    @Column(name = "var_rank_2")
    private Integer varRank2;

    @NotNull(message = "Rank 3 é obrigatório")
    @Column(name = "var_rank_3")
    private Integer varRank3;

    @NotNull(message = "Rank 4 é obrigatório")
    @Column(name = "var_rank_4")
    private Integer varRank4;

    @NotNull(message = "Altura máxima é obrigatória")
    @Column(name = "var_cor_almax")
    private double varCorAlMax;

    @NotNull(message = "Altura mínima é obrigatória")
    @Column(name = "var_cor_almin")
    private double varCorAlMin;

    @NotNull(message = "Largura máxima é obrigatória")
    @Column(name = "var_cor_lamax")
    private double varCorLaMax;

    @NotNull(message = "Largura mínima é obrigatória")
    @Column(name = "var_cor_lamin")
    private double varCorLaMin;

    @NotNull(message = "Profundidade máxima é obrigatória")
    @Column(name = "var_cor_prmax")
    private double varCorPrMax;

    @NotNull(message = "Profundidade mínima é obrigatória")
    @Column(name = "var_cor_prmin")
    private double varCorPrMin;

    @NotNull(message = "Soma máxima do dimensional é obrigatória")
    @Column(name = "var_cor_somdimmax")
    private double varCorSomDimMax;

    @NotNull(message = "Soma mínima do dimensional é obrigatória")
    @Column(name = "var_cor_somdimmin")
    private double varCorSomDimMin;

    @NotNull(message = "Peso máximo é obrigatório")
    @Column(name = "var_cor_pemax")
    private double varCorPeMax;

    @NotNull(message = "Peso mínima é obrigatório")
    @Column(name = "var_cor_pemin")
    private double varCorPeMin;

    public VariaveisModel() 
    {
        this.varId = 0;
        this.varCep = "";
        this.varCategoria = 0;
        this.varTempTroca = 0;
        this.varTempCarrinho = 0;
        this.varValidCupom = 0;
        this.varRank1 = 0;
        this.varRank2 = 0;
        this.varRank3 = 0;
        this.varRank4 = 0;
        this.varCorAlMax = 0.0;
        this.varCorAlMin = 0.0;
        this.varCorLaMax = 0.0;
        this.varCorLaMin = 0.0;
        this.varCorPrMax = 0.0;
        this.varCorPrMin = 0.0;
        this.varCorSomDimMax = 0.0;
        this.varCorSomDimMin = 0.0;
        this.varCorPeMax = 0.0;
        this.varCorPeMin = 0.0;
    }

    public VariaveisModel(Integer varId, String varCep, Integer varCategoria, Integer varTempTroca, Integer varTempCarrinho, Integer varValidCupom, Integer varRank1, Integer varRank2, Integer varRank3, Integer varRank4, double varCorAlMax, double varCorAlMin, double varCorLaMax, double varCorLaMin, double varCorPrMax, double varCorPrMin, double varCorSomDimMax, double varCorSomDimMin, double varCorPeMax, double varCorPeMin) 
    {
        this.varId = varId;
        this.varCep = varCep;
        this.varCategoria = varCategoria;
        this.varTempTroca = varTempTroca;
        this.varTempCarrinho = varTempCarrinho;
        this.varValidCupom = varValidCupom;
        this.varRank1 = varRank1;
        this.varRank2 = varRank2;
        this.varRank3 = varRank3;
        this.varRank4 = varRank4;
        this.varCorAlMax = varCorAlMax;
        this.varCorAlMin = varCorAlMin;
        this.varCorLaMax = varCorLaMax;
        this.varCorLaMin = varCorLaMin;
        this.varCorPrMax = varCorPrMax;
        this.varCorPrMin = varCorPrMin;
        this.varCorSomDimMax = varCorSomDimMax;
        this.varCorSomDimMin = varCorSomDimMin;
        this.varCorPeMax = varCorPeMax;
        this.varCorPeMin = varCorPeMin;
    }
    
    public Integer getVarId() 
    {
        return this.varId;
    }

    public void setVarId(Integer varId) 
    {
        this.varId = varId;
    }

    public String getVarCep() 
    {
        return this.varCep;
    }

    public void setVarCep(String varCep) 
    {
        this.varCep = varCep;
    }

    public Integer getVarCategoria() 
    {
        return this.varCategoria;
    }

    public void setVarCategoria(Integer varCategoria) 
    {
        this.varCategoria = varCategoria;
    }

    public Integer getVarTempTroca() 
    {
        return this.varTempTroca;
    }

    public void setVarTempTroca(Integer varTempTroca) 
    {
        this.varTempTroca = varTempTroca;
    }

    public Integer getVarTempCarrinho() 
    {
        return this.varTempCarrinho;
    }

    public void setVarTempCarrinho(Integer varTempCarrinho) 
    {
        this.varTempCarrinho = varTempCarrinho;
    }

    public Integer getVarRank1() 
    {
        return this.varRank1;
    }

    public Integer getVarValidCupom() 
    {
        return this.varValidCupom;
    }

    public void setVarValidCupom(Integer varValidCupom) 
    {
        this.varValidCupom = varValidCupom;
    }

    public void setVarRank1(Integer varRank1) 
    {
        this.varRank1 = varRank1;
    }

    public Integer getVarRank2() 
    {
        return this.varRank2;
    }

    public void setVarRank2(Integer varRank2) 
    {
        this.varRank2 = varRank2;
    }

    public Integer getVarRank3() 
    {
        return this.varRank3;
    }

    public void setVarRank3(Integer varRank3) 
    {
        this.varRank3 = varRank3;
    }

    public Integer getVarRank4() 
    {
        return this.varRank4;
    }

    public void setVarRank4(Integer varRank4) 
    {
        this.varRank4 = varRank4;
    }

    public double getVarCorAlMax() 
    {
        return this.varCorAlMax;
    }

    public void setVarCorAlMax(double varCorAlMax) 
    {
        this.varCorAlMax = varCorAlMax;
    }

    public double getVarCorAlMin() 
    {
        return this.varCorAlMin;
    }

    public void setVarCorAlMin(double varCorAlMin) 
    {
        this.varCorAlMin = varCorAlMin;
    }

    public double getVarCorLaMax() 
    {
        return this.varCorLaMax;
    }

    public void setVarCorLaMax(double varCorLaMax) 
    {
        this.varCorLaMax = varCorLaMax;
    }

    public double getVarCorLaMin() 
    {
        return this.varCorLaMin;
    }

    public void setVarCorLaMin(double varCorLaMin) 
    {
        this.varCorLaMin = varCorLaMin;
    }

    public double getVarCorPrMax() 
    {
        return this.varCorPrMax;
    }

    public void setVarCorPrMax(double varCorPrMax) 
    {
        this.varCorPrMax = varCorPrMax;
    }

    public double getVarCorPrMin() 
    {
        return this.varCorPrMin;
    }

    public void setVarCorPrMin(double varCorPrMin) 
    {
        this.varCorPrMin = varCorPrMin;
    }

    public double getVarCorSomDimMax() 
    {
        return this.varCorSomDimMax;
    }

    public void setVarCorSomDimMax(double varCorSomDimMax) 
    {
        this.varCorSomDimMax = varCorSomDimMax;
    }

    public double getVarCorSomDimMin() 
    {
        return this.varCorSomDimMin;
    }

    public void setVarCorSomDimMin(double varCorSomDimMin) 
    {
        this.varCorSomDimMin = varCorSomDimMin;
    }

    public double getVarCorPeMax() 
    {
        return this.varCorPeMax;
    }

    public void setVarCorPeMax(double varCorPeMax) 
    {
        this.varCorPeMax = varCorPeMax;
    }

    public double getVarCorPeMin() 
    {
        return this.varCorPeMin;
    }

    public void setVarCorPeMin(double varCorPeMin) 
    {
        this.varCorPeMin = varCorPeMin;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof VariaveisModel)) 
        {
            return false;
        }
        
        VariaveisModel variaveisModel = (VariaveisModel) o;
        return Objects.equals(varId, variaveisModel.varId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(varId);
    }
}