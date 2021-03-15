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

@Entity
@Table(name = "CATEGORIAS_PRODUTOS")
public class CategoriasProdutosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpr_id", insertable = false, updatable = false)
    private Integer cprId;

    @ManyToOne
    @JoinColumn(name = "cpr_ctg_id", referencedColumnName = "ctg_id")
    private CategoriasModel categoria;

    @ManyToOne
    @JoinColumn(name = "cpr_prd_id", referencedColumnName = "prd_id")
    private ProdutosModel produto;

    public CategoriasProdutosModel() 
    {
        this.cprId = 0;
    }

    public CategoriasProdutosModel(Integer cprId) 
    {
        this.cprId = cprId;
    }

    public Integer getCprId() 
    {
        return this.cprId;
    }

    public void setCprId(Integer cprId) 
    {
        this.cprId = cprId;
    }

    public CategoriasModel getCategoria() 
    {
        return this.categoria;
    }

    public void setCategoria(CategoriasModel categoria) 
    {
        this.categoria = categoria;
    }

    public ProdutosModel getProduto() 
    {
        return this.produto;
    }

    public void setProduto(ProdutosModel produto) 
    {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof CategoriasProdutosModel)) 
        {
            return false;
        }

        CategoriasProdutosModel categoriasProdutosModel = (CategoriasProdutosModel) o;
        return Objects.equals(cprId, categoriasProdutosModel.cprId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(cprId);
    }
}
