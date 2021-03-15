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
@Table(name = "PEDIDOS_CUPONS")
public class PedCuponsModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pcp_id", insertable = false, updatable = false)
    private Integer pcpId;

    @ManyToOne
    @JoinColumn(name = "pcp_cpp_id", referencedColumnName = "cpp_id")
    private CuponsPromocoesModel cupom;

    @ManyToOne
    @JoinColumn(name = "pcp_ped_id", referencedColumnName = "ped_id")
    private PedidosModel pedido;

    public PedCuponsModel() 
    {
        super();

        this.pcpId = 0;
    }

    public PedCuponsModel(Integer pcpId) 
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

    public PedidosModel getPedido() 
    {
        return this.pedido;
    }

    public void setPedido(PedidosModel pedido) 
    {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof PedCuponsModel)) 
        {
            return false;
        }

        PedCuponsModel pedCuponsModel = (PedCuponsModel) o;
        return Objects.equals(pcpId, pedCuponsModel.pcpId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(pcpId);
    }
}
