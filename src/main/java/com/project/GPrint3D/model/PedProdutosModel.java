package com.project.GPrint3D.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PEDIDOS_COMPRAS_PRODUTOS")
public class PedProdutosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ppd_id", insertable = false, updatable = false)
    private Integer ppdId;

    @NotNull(message = "Quantidade é obrigatória")
    @Column(name = "ppd_quantidade")
    private Integer ppdQuantidade;

    @NotNull(message = "Status é obrigatório")
    @Column(name = "ppd_status")
    private Integer ppdStatus;

    @ManyToOne
    @JoinColumn(name = "ppd_pdc_id", referencedColumnName = "pdc_id")
    private PedidosComprasModel pedidoCompra;

    @ManyToOne
    @JoinColumn(name = "ppd_prd_id", referencedColumnName = "prd_id")
    private ProdutosModel produto;

    @OneToOne(mappedBy = "pedProduto")
    private PedidosTrocasModel pedidosTroca;

    public PedProdutosModel() 
    {
        super();

        this.ppdId = 0;
        this.ppdQuantidade = 0;
        this.ppdStatus = 0;
    }

    public PedProdutosModel(Integer ppdId, Integer ppdQuantidade, Integer ppdStatus) 
    {
        super( );

        this.ppdId = ppdId;
        this.ppdQuantidade = ppdQuantidade;
        this.ppdStatus = ppdStatus;
    }

    public Integer getPpdId() 
    {
        return this.ppdId;
    }

    public void setPpdId(Integer ppdId) 
    {
        this.ppdId = ppdId;
    }

    public Integer getPpdQuantidade() 
    {
        return this.ppdQuantidade;
    }

    public void setPpdQuantidade(Integer ppdQuantidade) 
    {
        this.ppdQuantidade = ppdQuantidade;
    }

    public Integer getPpdStatus() 
    {
        return this.ppdStatus;
    }

    public void setPpdStatus(Integer ppdStatus) 
    {
        this.ppdStatus = ppdStatus;
    }

    public PedidosComprasModel getPedidoCompra() 
    {
        return this.pedidoCompra;
    }

    public void setPedidoCompra(PedidosComprasModel pedidoCompra) 
    {
        this.pedidoCompra = pedidoCompra;
    }

    public ProdutosModel getProduto() 
    {
        return this.produto;
    }

    public void setProduto(ProdutosModel produto) 
    {
        this.produto = produto;
    }

    public PedidosTrocasModel getPedidosTroca() 
    {
        return this.pedidosTroca;
    }

    public void setPedidosTroca(PedidosTrocasModel pedidosTroca) 
    {
        this.pedidosTroca = pedidosTroca;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof PedProdutosModel)) 
        {
            return false;
        }

        PedProdutosModel pedProdutosModel = (PedProdutosModel) o;
        return Objects.equals(ppdId, pedProdutosModel.ppdId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(ppdId);
    }
}
