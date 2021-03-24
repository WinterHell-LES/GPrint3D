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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PEDIDOS_COMPRAS")
public class PedidosComprasModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdc_id", insertable = false, updatable = false)
    private Integer pdcId;

    @NotEmpty(message = "Número é obrigatório")
    @Column(name = "pdc_numero")
    private String pdcNumero;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "pdc_data")
    private Date pdcData;

    @Column(name = "pdc_statuspedido")
    private String pdcStatusPedido;

    @Column(name = "pdc_statuslogistica")
    private String pdcStatusLogistica;

    @OneToOne
    @JoinColumn(name = "pdc_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    @OneToOne
    @JoinColumn(name = "pdc_end_id", referencedColumnName = "end_id")
    private EnderecosModel endereco;

    @OneToMany(mappedBy = "pedidoCompra")
    private List<PedCuponsModel> listPedCupons;

    @OneToMany(mappedBy = "pedidoCompra")
    private List<PedProdutosModel> listPedProdutos;

    @OneToMany(mappedBy = "pedidoCompra")
    private List<PedCartoesModel> listPedCartoes;

    @OneToMany(mappedBy = "pedidoCompra")
    private List<PedidosTrocasModel> listPedTrocas;

    public PedidosComprasModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.pdcId = 0;
        this.pdcNumero = "";
        this.pdcData = new Date(dataAtual.getTime());
        this.pdcStatusPedido = "";
        this.pdcStatusLogistica = "";
    }

    public PedidosComprasModel(Integer pdcId, String pdcNumero, Date pdcData, String pdcStatusPedido, String pdcStatusLogistica) 
    {
        super( );

        this.pdcId = pdcId;
        this.pdcNumero = pdcNumero;
        this.pdcData = pdcData;
        this.pdcStatusPedido = pdcStatusPedido;
        this.pdcStatusLogistica = pdcStatusLogistica;
    }

    public Integer getPdcId() 
    {
        return this.pdcId;
    }

    public void setPdcId(Integer pdcId) 
    {
        this.pdcId = pdcId;
    }

    public String getPdcNumero() 
    {
        return this.pdcNumero;
    }

    public void setPdcNumero(String pdcNumero) 
    {
        this.pdcNumero = pdcNumero;
    }

    public Date getPdcData() 
    {
        return this.pdcData;
    }

    public void setPdcData(Date pdcData) 
    {
        this.pdcData = pdcData;
    }

    public String getPdcStatusPedido() 
    {
        return this.pdcStatusPedido;
    }

    public void setPdcStatusPedido(String pdcStatusPedido) 
    {
        this.pdcStatusPedido = pdcStatusPedido;
    }

    public String getPdcStatusLogistica() 
    {
        return this.pdcStatusLogistica;
    }

    public void setPdcStatusLogistica(String pdcStatusLogistica) 
    {
        this.pdcStatusLogistica = pdcStatusLogistica;
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
            
        if (!(o instanceof PedidosComprasModel)) 
        {
            return false;
        }

        PedidosComprasModel pedidosComprasModel = (PedidosComprasModel) o;
        return Objects.equals(pdcId, pedidosComprasModel.pdcId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(pdcId);
    }
}
