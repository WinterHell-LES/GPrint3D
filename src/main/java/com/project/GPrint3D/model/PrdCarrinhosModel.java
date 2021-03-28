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
@Table(name = "PRODUTOS_CARRINHOS")
public class PrdCarrinhosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pcr_id", insertable = false, updatable = false)
    private Integer pcrId;

    @NotNull(message = "Quantidade é obrigatória")
    @Column(name = "pcr_quantidade")
    private Integer pcrQuantidade;

    @ManyToOne
    @JoinColumn(name = "pcr_prd_id", referencedColumnName = "prd_id")
    private ProdutosModel produto;

    @ManyToOne
    @JoinColumn(name = "pcr_car_id", referencedColumnName = "car_id")
    private CarrinhosModel carrinho;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "pcr_date")
    private Date pcrDate;

    @NotNull(message = "Estado é obrigatório")
    @Column(name = "pcr_ativo")
    private boolean pcrAtivo;

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

    public Integer getPcrQuantidade() 
    {
        return this.pcrQuantidade;
    }

    public void setPcrQuantidade(Integer pcrQuantidade) 
    {
        this.pcrQuantidade = pcrQuantidade;
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

    public Date getPcrDate() {
        return this.pcrDate;
    }

    public void setPcrDate(Date pcrDate) {
        this.pcrDate = pcrDate;
    }

    public boolean isPcrAtivo() {
        return this.pcrAtivo;
    }

    public boolean getPcrAtivo() {
        return this.pcrAtivo;
    }

    public void setPcrAtivo(boolean pcrAtivo) {
        this.pcrAtivo = pcrAtivo;
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
