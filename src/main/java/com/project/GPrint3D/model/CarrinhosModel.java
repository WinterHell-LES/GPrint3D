package com.project.GPrint3D.model;

import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "carrinho")
    private List<PrdCarrinhosModel> listProdutos;

    @OneToOne
    @JoinColumn(name = "car_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

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
