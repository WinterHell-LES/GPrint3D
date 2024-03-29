package com.project.GPrint3D.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARRINHOS")
public class CarrinhosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id", insertable = false, updatable = false)
    private Integer carId;

    @OneToOne
    @JoinColumn(name = "car_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;
    
    @Column(name = "car_cli_temp")
    private String temporaryCliId;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<PrdCarrinhosModel> listProdutos;

    public CarrinhosModel() 
    {
        super();

        this.carId = 0;
    }

    public CarrinhosModel(Integer carId) 
    {
        super( );

        this.carId = carId;
    }

    public Integer getCarId() 
    {
        return this.carId;
    }

    public void setCarId(Integer carId) 
    {
        this.carId = carId;
    }

    public List<PrdCarrinhosModel> getListProdutos() 
    {
        return this.listProdutos;
    }

    public void setListProdutos(List<PrdCarrinhosModel> listProdutos) 
    {
        this.listProdutos = listProdutos;
    }

    public ClientesModel getCliente() 
    {
        return this.cliente;
    }

    public void setCliente(ClientesModel cliente) 
    {
        this.cliente = cliente;
    }

    public String getTemporaryCliId() 
    {
        return this.temporaryCliId;
    }

    public String setTemporaryCliId(String temporaryCliId) 
    {
        return this.temporaryCliId = temporaryCliId;
    }

    public double getValorTotal()
    {
        double valorTotal = 0.0;

        for (PrdCarrinhosModel aux : getListProdutosAtivo())
        {
            valorTotal += aux.getProduto().getPrdPreco() * aux.getPcrQuantidade();
        }

        return valorTotal;
    }

    public List<PrdCarrinhosModel> getListProdutosAtivo() 
    {
        if (this.listProdutos.isEmpty())
        {
            return new ArrayList<>();
        }

        List<PrdCarrinhosModel> listProduto = new ArrayList<>();

        for (PrdCarrinhosModel aux : this.listProdutos)
        {
            if (aux.isPcrAtivo())
            {
                listProduto.add(aux);
            }
        }

        return listProduto;
    }

    public List<PrdCarrinhosModel> getListProdutosInativo() 
    {
        if (this.listProdutos.isEmpty())
        {
            return new ArrayList<>();
        }

        List<PrdCarrinhosModel> listProduto = new ArrayList<>();

        for (PrdCarrinhosModel aux : this.listProdutos)
        {
            if (!aux.isPcrAtivo())
            {
                listProduto.add(aux);
            }
        }

        return listProduto;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof CarrinhosModel)) 
        {
            return false;
        }
        
        CarrinhosModel carrinhosModel = (CarrinhosModel) o;
        return Objects.equals(carId, carrinhosModel.carId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(carId);
    }
}
