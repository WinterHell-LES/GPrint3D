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
@Table(name = "PEDIDOS_COMPRAS_CUPONS_PROMOCOES")
public class PedCuponsPromocoesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pcp_id", insertable = false, updatable = false)
    private Integer pcpId;

    @ManyToOne
    @JoinColumn(name = "pcp_cpp_id", referencedColumnName = "cpp_id")
    private CuponsPromocoesModel cupom;

    @ManyToOne
    @JoinColumn(name = "pcp_pdc_id", referencedColumnName = "pdc_id")
    private PedidosComprasModel pedidoCompra;

    public PedCuponsPromocoesModel() 
    {
        super();

        this.pcpId = 0;
    }

    public PedCuponsPromocoesModel(Integer pcpId) 
    {
        super( );

        this.pcpId = pcpId;
    }

    public Integer getPcpId() 
    {
        return this.pcpId;
    }

    public void setPcpId(Integer pcpId) 
    {
        this.pcpId = pcpId;
    }

    public CuponsPromocoesModel getCupom() 
    {
        return this.cupom;
    }

    public void setCupom(CuponsPromocoesModel cupom) 
    {
        this.cupom = cupom;
    }

    public PedidosComprasModel getPedidoCompra() 
    {
        return this.pedidoCompra;
    }

    public void setPedidoCompra(PedidosComprasModel pedidoCompra) 
    {
        this.pedidoCompra = pedidoCompra;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof PedCuponsPromocoesModel)) 
        {
            return false;
        }

        PedCuponsPromocoesModel pedCuponsModel = (PedCuponsPromocoesModel) o;
        return Objects.equals(pcpId, pedCuponsModel.pcpId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(pcpId);
    }
}
