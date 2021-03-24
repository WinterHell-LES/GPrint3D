package com.project.GPrint3D.model;

import java.sql.Date;
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
@Table(name = "PRODUTOS_JUSTIFICATIVAS")
public class ProdutosJustificativasModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pju_id", insertable = false, updatable = false)
    private Integer pjuId;

    @NotEmpty(message = "Produto é obrigatório")
    @Column(name = "pju_produto")
    private String pjuProduto;

    @NotEmpty(message = "Categorias é obrigatório")
    @Column(name = "pju_categorias")
    private String pjuCategorias;

    @NotEmpty(message = "Ação é obrigatória")
    @Column(name = "pju_acao")
    private String pjuAcao;

    @NotEmpty(message = "Justificativa é obrigatória")
    @Column(name = "pju_justificativa")
    private String pjuJustificativa;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "pju_data")
    private Date pjuData;

    public ProdutosJustificativasModel() 
    {
        java.util.Date dataAtual = new java.util.Date();

        this.pjuId = 0;
        this.pjuProduto = "";
        this.pjuCategorias = "";
        this.pjuAcao = "";
        this.pjuJustificativa = "";
        this.pjuData = new Date(dataAtual.getTime());
    }

    public ProdutosJustificativasModel(Integer pjuId, String pjuProduto, String pjuCategorias, String pjuAcao, String pjuJustificativa, Date pjuData) 
    {
        this.pjuId = pjuId;
        this.pjuProduto = pjuProduto;
        this.pjuCategorias = pjuCategorias;
        this.pjuAcao = pjuAcao;
        this.pjuJustificativa = pjuJustificativa;
        this.pjuData = pjuData;
    }

    public Integer getPjuId() 
    {
        return this.pjuId;
    }

    public void setPjuId(Integer pjuId) 
    {
        this.pjuId = pjuId;
    }

    public String getPjuProduto() 
    {
        return this.pjuProduto;
    }

    public void setPjuProduto(String pjuProduto) 
    {
        this.pjuProduto = pjuProduto;
    }

    public String getPjuCategorias() 
    {
        return this.pjuCategorias;
    }

    public void setPjuCategorias(String pjuCategorias) 
    {
        this.pjuCategorias = pjuCategorias;
    }

    public String getPjuAcao() 
    {
        return this.pjuAcao;
    }

    public void setPjuAcao(String pjuAcao) 
    {
        this.pjuAcao = pjuAcao;
    }

    public String getPjuJustificativa() 
    {
        return this.pjuJustificativa;
    }

    public void setPjuJustificativa(String pjuJustificativa) 
    {
        this.pjuJustificativa = pjuJustificativa;
    }

    public Date getPjuData() 
    {
        return this.pjuData;
    }

    public void setPjuData(Date pjuData) 
    {
        this.pjuData = pjuData;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof ProdutosJustificativasModel)) 
        {
            return false;
        }

        ProdutosJustificativasModel produtosJustificativasModel = (ProdutosJustificativasModel) o;
        return Objects.equals(pjuId, produtosJustificativasModel.pjuId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(pjuId);
    }
}
