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
@Table(name = "PRODUTOS_CARRINHOS")
public class PrdCarrinhosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", insertable = false, updatable = false)
    private Integer pcrId;

    @ManyToOne
    @JoinColumn(name = "pcr_prd_id", referencedColumnName = "prd_id")
    private ProdutosModel produto;

    @ManyToOne
    @JoinColumn(name = "pcr_car_id", referencedColumnName = "car_id")
    private CarrinhosModel carrinho;

    public PrdCarrinhosModel() 
    {
        super();

        this.pcrId = 0;
    }

    public PrdCarrinhosModel(Integer pcrId) 
    {
        super( );

        this.pcrId = pcrId;
    }

    public Integer getPcrId() 
    {
        return this.pcrId;
    }

    public void setPcrId(Integer pcrId) 
    {
        this.pcrId = pcrId;
    }

    public ProdutosModel getProduto() 
    {
        return this.produto;
    }

    public void setProduto(ProdutosModel produto) 
    {
        this.produto = produto;
    }

    public CarrinhosModel getCarrinho() 
    {
        return this.carrinho;
    }

    public void setCarrinho(CarrinhosModel carrinho) 
    {
        this.carrinho = carrinho;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof PrdCarrinhosModel)) 
        {
            return false;
        }

        PrdCarrinhosModel prodCarrinhosModel = (PrdCarrinhosModel) o;
        return Objects.equals(pcrId, prodCarrinhosModel.pcrId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(pcrId);
    }
}
