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
@Table(name = "PEDIDOS_COMPRAS_CARTOES")
public class PedCartoesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pct_id", insertable = false, updatable = false)
    private Integer pctId;

    @NotNull(message = "Valor é obrigatório")
    @Column(name = "pct_valor")
    private double pctValor;

    @ManyToOne
    @JoinColumn(name = "pct_crt_id", referencedColumnName = "crt_id")
    private CartoesModel cartao;

    @ManyToOne
    @JoinColumn(name = "pct_pdc_id", referencedColumnName = "pdc_id")
    private PedidosComprasModel pedidoCompra;

    public PedCartoesModel() 
    {
        super();

        this.pctId = 0;
        this.pctValor = 0.0;
    }

    public PedCartoesModel(Integer pctId, double pctValor) 
    {
        super( );

        this.pctId = pctId;
        this.pctValor = pctValor;
    }

    public Integer getPctId() 
    {
        return this.pctId;
    }

    public void setPctId(Integer pctId) 
    {
        this.pctId = pctId;
    }

    public double getPctValor() 
    {
        return this.pctValor;
    }

    public void setPctValor(double pctValor) 
    {
        this.pctValor = pctValor;
    }

    public CartoesModel getCartao() 
    {
        return this.cartao;
    }

    public void setCartao(CartoesModel cartao) 
    {
        this.cartao = cartao;
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
            
        if (!(o instanceof PedCartoesModel)) 
        {
            return false;
        }

        PedCartoesModel pedCartoesModel = (PedCartoesModel) o;
        return Objects.equals(pctId, pedCartoesModel.pctId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(pctId);
    }
}
