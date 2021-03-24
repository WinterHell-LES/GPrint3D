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
@Table(name = "PEDIDOS_TROCAS")
public class PedidosTrocasModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdt_id", insertable = false, updatable = false)
    private Integer pdtId;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "pdt_data")
    private Date pdtData;

    @Column(name = "pdt_statuspedido")
    private String pdtStatusPedido;

    @Column(name = "pdt_statuslogistica")
    private String pdtStatusLogistica;

    @ManyToOne
    @JoinColumn(name = "pdt_pdc_id", referencedColumnName = "pdc_id")
    private PedidosComprasModel pedidoCompra;

    public PedidosTrocasModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.pdtId = 0;
        this.pdtData = new Date(dataAtual.getTime());
        this.pdtStatusPedido = "";
        this.pdtStatusLogistica = "";
    }

    public PedidosTrocasModel(Integer pdtId, Date pdtData, String pdtStatusPedido, String pdtStatusLogistica) 
    {
        super( );

        this.pdtId = pdtId;
        this.pdtData = pdtData;
        this.pdtStatusPedido = pdtStatusPedido;
        this.pdtStatusLogistica = pdtStatusLogistica;
    }

    public Integer getPdtId() 
    {
        return this.pdtId;
    }

    public void setPdtId(Integer pdtId) 
    {
        this.pdtId = pdtId;
    }

    public Date getPdtData() 
    {
        return this.pdtData;
    }

    public void setPdtData(Date pdtData) 
    {
        this.pdtData = pdtData;
    }

    public String getPdtStatusPedido() 
    {
        return this.pdtStatusPedido;
    }

    public void setPdtStatusPedido(String pdtStatusPedido) 
    {
        this.pdtStatusPedido = pdtStatusPedido;
    }

    public String getPdtStatusLogistica() 
    {
        return this.pdtStatusLogistica;
    }

    public void setPdtStatusLogistica(String pdtStatusLogistica) 
    {
        this.pdtStatusLogistica = pdtStatusLogistica;
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
            
        if (!(o instanceof PedidosTrocasModel)) 
        {
            return false;
        }

        PedidosTrocasModel pedidosTrocasModel = (PedidosTrocasModel) o;
        return Objects.equals(pdtId, pedidosTrocasModel.pdtId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(pdtId);
    }
}
