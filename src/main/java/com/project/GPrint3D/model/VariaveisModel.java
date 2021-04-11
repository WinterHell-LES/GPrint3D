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

    public VariaveisModel() 
    {
        this.varId = 0;
        this.varCep = "";
        this.varCategoria = 0;
        this.varTempTroca = 0;
        this.varRank1 = 0;
        this.varRank2 = 0;
        this.varRank3 = 0;
        this.varRank4 = 0;
    }

    public VariaveisModel(Integer varId, String varCep, Integer varCategoria, Integer varTempTroca, Integer varRank1, Integer varRank2, Integer varRank3, Integer varRank4) 
    {
        this.varId = varId;
        this.varCep = varCep;
        this.varCategoria = varCategoria;
        this.varTempTroca = varTempTroca;
        this.varRank1 = varRank1;
        this.varRank2 = varRank2;
        this.varRank3 = varRank3;
        this.varRank4 = varRank4;
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

    public Integer getVarRank1() 
    {
        return this.varRank1;
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