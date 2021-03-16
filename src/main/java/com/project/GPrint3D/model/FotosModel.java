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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FOTOS")
public class FotosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fto_id", insertable = false, updatable = false)
    private Integer ftoId;

    @NotEmpty(message = "Nome é obrigatório")
    @Column(name = "fto_nome")
    private String ftoNome;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "fto_data")
    private Date ftoData;

    @Column(name = "fto_content")
    private byte[] ftoContent;

    @ManyToOne
    @JoinColumn(name = "fto_prd_id", referencedColumnName = "prd_id")
    private ProdutosModel produto;

    public FotosModel() 
    {
        java.util.Date dataAtual = new java.util.Date();
        
        this.ftoId = 0;
        this.ftoNome = "";
        this.ftoData = new Date(dataAtual.getTime());
        this.ftoContent = null;
    }

    public FotosModel(Integer ftoId, String ftoNome, Date ftoData, byte[] ftoContent) 
    {
        this.ftoId = ftoId;
        this.ftoNome = ftoNome;
        this.ftoData = ftoData;
        this.ftoContent = ftoContent;
    }

    public Integer getFtoId() 
    {
        return this.ftoId;
    }

    public void setFtoId(Integer ftoId) 
    {
        this.ftoId = ftoId;
    }

    public String getFtoNome() 
    {
        return this.ftoNome;
    }

    public void setFtoNome(String ftoNome) 
    {
        this.ftoNome = ftoNome;
    }

    public Date getFtoData() 
    {
        return this.ftoData;
    }

    public void setFtoData(Date ftoData) 
    {
        this.ftoData = ftoData;
    }

    public byte[] getFtoContent() 
    {
        return this.ftoContent;
    }

    public void setFtoContent(byte[] ftoContent) 
    {
        this.ftoContent = ftoContent;
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
            
        if (!(o instanceof FotosModel)) 
        {
            return false;
        }

        FotosModel fotosModel = (FotosModel) o;
        return Objects.equals(ftoId, fotosModel.ftoId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(ftoId);
    }
}
