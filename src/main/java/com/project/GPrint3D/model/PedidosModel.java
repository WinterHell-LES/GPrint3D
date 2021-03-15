package com.project.GPrint3D.model;

import java.sql.Date;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PEDIDOS")
public class PedidosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id", insertable = false, updatable = false)
    private Integer pedId;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "ped_data")
    private Date pedData;

    @Column(name = "ped_statuspedido")
    private String pedStatusPedido;

    @Column(name = "ped_statuslogistica")
    private String pedStatusLogistica;

    @OneToOne
    @JoinColumn(name = "ped_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    @OneToOne
    @JoinColumn(name = "ped_end_id", referencedColumnName = "end_id")
    private EnderecosModel endereco;

    @OneToMany(mappedBy = "pedido")
    private List<PedCuponsModel> listPedCupons;

    @OneToMany(mappedBy = "pedido")
    private List<PedProdutosModel> listPedProdutos;

    @OneToMany(mappedBy = "pedido")
    private List<PedCartoesModel> listPedCartoes;

    public PedidosModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.pedId = 0;
        this.pedData = new Date(dataAtual.getTime());
        this.pedStatusPedido = "";
        this.pedStatusLogistica = "";
    }

    public PedidosModel(Integer pedId, Date pedData, String pedStatusPedido, String pedStatusLogistica) 
    {
        super( );

        this.pedId = pedId;
        this.pedData = pedData;
        this.pedStatusPedido = pedStatusPedido;
        this.pedStatusLogistica = pedStatusLogistica;
    }

    public Integer getPedId() 
    {
        return this.pedId;
    }

    public void setPedId(Integer pedId) 
    {
        this.pedId = pedId;
    }

    public Date getPedData() 
    {
        return this.pedData;
    }

    public void setPedData(Date pedData) 
    {
        this.pedData = pedData;
    }

    public String getPedStatusPedido() 
    {
        return this.pedStatusPedido;
    }

    public void setPedStatusPedido(String pedStatusPedido) 
    {
        this.pedStatusPedido = pedStatusPedido;
    }

    public String getPedStatusLogistica() 
    {
        return this.pedStatusLogistica;
    }

    public void setPedStatusLogistica(String pedStatusLogistica) 
    {
        this.pedStatusLogistica = pedStatusLogistica;
    }
    
    public ClientesModel getCliente() 
    {
        return this.cliente;
    }

    public void setCliente(ClientesModel cliente) 
    {
        this.cliente = cliente;
    }

    public EnderecosModel getEndereco() 
    {
        return this.endereco;
    }

    public void setEndereco(EnderecosModel endereco) 
    {
        this.endereco = endereco;
    }

    public List<PedCuponsModel> getListPedCupons() 
    {
        return this.listPedCupons;
    }

    public void setListPedCupons(List<PedCuponsModel> listPedCupons) 
    {
        this.listPedCupons = listPedCupons;
    }

    public List<PedProdutosModel> getListPedProdutos() 
    {
        return this.listPedProdutos;
    }

    public void setListPedProdutos(List<PedProdutosModel> listPedProdutos) 
    {
        this.listPedProdutos = listPedProdutos;
    }

    public List<PedCartoesModel> getListPedCartoes() 
    {
        return this.listPedCartoes;
    }

    public void setListPedCartoes(List<PedCartoesModel> listPedCartoes) 
    {
        this.listPedCartoes = listPedCartoes;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof PedidosModel)) 
        {
            return false;
        }

        PedidosModel pedidosModel = (PedidosModel) o;
        return Objects.equals(pedId, pedidosModel.pedId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(pedId);
    }
}
