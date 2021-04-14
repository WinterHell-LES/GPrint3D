package com.project.GPrint3D.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.project.GPrint3D.util.Listas.PedidosComprasListUtil;

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
    private Integer pdcStatusPedido;

    @Column(name = "pdc_statuslogistica")
    private Integer pdcStatusLogistica;

    @ManyToOne
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

    @OneToOne(mappedBy = "pedidoCompra")
    private PedComFretesModel frete;

    public PedidosComprasModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.pdcId = 0;
        this.pdcNumero = "";
        this.pdcData = new Date(dataAtual.getTime());
        this.pdcStatusPedido = 0;
        this.pdcStatusLogistica = 0;
    }

    public PedidosComprasModel(Integer pdcId, String pdcNumero, Date pdcData, Integer pdcStatusPedido, Integer pdcStatusLogistica) 
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

    public Integer getPdcStatusPedido() 
    {
        return this.pdcStatusPedido;
    }

    public void setPdcStatusPedido(Integer pdcStatusPedido) 
    {
        this.pdcStatusPedido = pdcStatusPedido;
    }

    public Integer getPdcStatusLogistica() 
    {
        return this.pdcStatusLogistica;
    }

    public void setPdcStatusLogistica(Integer pdcStatusLogistica) 
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

    public List<PedidosTrocasModel> getListPedTrocas() 
    {
        return this.listPedTrocas;
    }

    public void setListPedTrocas(List<PedidosTrocasModel> listPedTrocas) 
    {
        this.listPedTrocas = listPedTrocas;
    }

    public double getValorTotal()
    {
        double valorTotal = 0.0;

        for (PedProdutosModel aux : this.listPedProdutos)
        {
            valorTotal += aux.getProduto().getPrdPreco() * aux.getPpdQuantidade();
        }

        return valorTotal;
    }

    public String getStrStatusPedido()
    {        
        PedidosComprasListUtil utilTrocas = new PedidosComprasListUtil();

        HashMap<Integer, String> listTroca = utilTrocas.getListCompraPedidos();
        
        String status = listTroca.get(this.pdcStatusPedido);

        return status;
    }

    public String getStrStatusLogistica()
    {
        PedidosComprasListUtil utilTrocas = new PedidosComprasListUtil();

        HashMap<Integer, String> listTroca = utilTrocas.getListCompraLogistica();
        
        String status = listTroca.get(this.pdcStatusLogistica);

        return status;
    } 

    public PedComFretesModel getFrete() {
        return this.frete;
    }

    public void setFrete(PedComFretesModel frete) {
        this.frete = frete;
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
