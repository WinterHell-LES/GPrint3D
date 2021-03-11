package com.project.GPrint3D.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARTOES_PADROES")
public class CartoesPadroesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctp_id", insertable = false, updatable = false)
    private Integer ctpId;

    @OneToOne
    @JoinColumn(name = "ctp_crt_id", referencedColumnName = "crt_id")
    private CartoesModel cartao;

    @OneToOne
    @JoinColumn(name = "ctp_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    public CartoesPadroesModel() 
    {
        super();

        this.ctpId = 0;
    }

    public CartoesPadroesModel(Integer ctpId) 
    {
        super( );
        
        this.ctpId = ctpId;
    }

    public Integer getCtpId() 
    {
        return this.ctpId;
    }

    public void setCtpId(Integer ctpId) 
    {
        this.ctpId = ctpId;
    }

    public CartoesModel getCartao() 
    {
        return this.cartao;
    }

    public void setCartao(CartoesModel cartao) 
    {
        this.cartao = cartao;
    }

    public ClientesModel getCliente() 
    {
        return this.cliente;
    }

    public void setCliente(ClientesModel cliente) 
    {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof CartoesPadroesModel)) 
        {
            return false;
        }

        CartoesPadroesModel cartoesPadroesModel = (CartoesPadroesModel) o;
        return Objects.equals(ctpId, cartoesPadroesModel.ctpId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(ctpId);
    }
}
