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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PEDIDOS_PRODUTOS")
public class PedProdutosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ppd_id", insertable = false, updatable = false)
    private Integer ppdId;

    @NotNull(message = "Quantidade é obrigatória")
    @Column(name = "ppd_quantidade")
    private Integer ppdQuantidade;

    @ManyToOne
    @JoinColumn(name = "ppd_ped_id", referencedColumnName = "ped_id")
    private PedidosModel pedido;

    @ManyToOne
    @JoinColumn(name = "ppd_prd_id", referencedColumnName = "prd_id")
    private ProdutosModel produto;

    public PedProdutosModel() 
    {
        super();

        this.ppdId = 0;
    }

    public PedProdutosModel(Integer ppdId) 
    {
        super( );

        this.ppdId = ppdId;
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

    public PedidosModel getPedido() 
    {
        return this.pedido;
    }

    public void setPedido(PedidosModel pedido) 
    {
        this.pedido = pedido;
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
