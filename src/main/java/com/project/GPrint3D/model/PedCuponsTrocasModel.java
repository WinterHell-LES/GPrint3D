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
@Table(name = "PEDIDOS_COMPRAS_CUPONS_TROCAS")
public class PedCuponsTrocasModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pct_id", insertable = false, updatable = false)
    private Integer pctId;

    @ManyToOne
    @JoinColumn(name = "pct_cpt_id", referencedColumnName = "cpt_id")
    private CuponsTrocasModel cupom;

    @ManyToOne
    @JoinColumn(name = "pct_pdc_id", referencedColumnName = "pdc_id")
    private PedidosComprasModel pedidoCompra;

    public PedCuponsTrocasModel ()
    {
        super();

        this.pctId = 0;
    }

    public PedCuponsTrocasModel (Integer pctId)
    {
        super( );

        this.pctId = pctId;
    }

    public Integer getPctId ()
    {
        return this.pctId;
    }

    public void setPctId (Integer pctId)
    {
        this.pctId = pctId;
    }

    public CuponsTrocasModel getCupom ()
    {
        return this.cupom;
    }

    public void setCupom (CuponsTrocasModel cupom)
    {
        this.cupom = cupom;
    }

    public PedidosComprasModel getPedidoCompra ()
    {
        return this.pedidoCompra;
    }

    public void setPedidoCompra (PedidosComprasModel pedidoCompra)
    {
        this.pedidoCompra = pedidoCompra;
    }

    @Override
    public boolean equals (Object o)
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof PedCuponsTrocasModel))
        {
            return false;
        }

        PedCuponsTrocasModel pedCuponsTrocasModel = (PedCuponsTrocasModel) o;
        return Objects.equals(pctId, pedCuponsTrocasModel.pctId);
    }

    @Override
    public int hashCode ()
    {
        return Objects.hashCode(pctId);
    }
}