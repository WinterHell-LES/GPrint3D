package com.project.GPrint3D.model;

import java.sql.Date;
import java.util.ArrayList;
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

    @NotNull(message = "Data inicial é obrigatória")
    @Column(name = "pdc_datainicio")
    private Date pdcDataInicio;

    @Column(name = "pdc_datafim")
    private Date pdcDataFim;

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
    private List<PedCuponsPromocoesModel> listPedCuponsPromocoes;

    @OneToMany(mappedBy = "pedidoCompra")
    private List<PedCuponsTrocasModel> listPedCuponsTrocas;

    @OneToMany(mappedBy = "pedidoCompra")
    private List<PedProdutosModel> listPedProdutos;

    @OneToMany(mappedBy = "pedidoCompra")
    private List<PedCartoesModel> listPedCartoes;

    @OneToMany(mappedBy = "pedidoCompra")
    private List<PedidosTrocasModel> listPedTrocas;

    @OneToMany(mappedBy = "pedidoCompra")
    private List<SaidasModel> listSaidas;

    @OneToOne(mappedBy = "pedidoCompra")
    private PedComFretesModel frete;

    public PedidosComprasModel ()
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.pdcId = 0;
        this.pdcNumero = "";
        this.pdcDataInicio = new Date(dataAtual.getTime());
        this.pdcDataFim = null;
        this.pdcStatusPedido = 0;
        this.pdcStatusLogistica = 0;
    }

    public PedidosComprasModel (Integer pdcId, String pdcNumero, Date pdcDataInicio, Date pdcDataFim,
            Integer pdcStatusPedido, Integer pdcStatusLogistica)
    {
        super();

        this.pdcId = pdcId;
        this.pdcNumero = pdcNumero;
        this.pdcDataInicio = pdcDataInicio;
        this.pdcDataFim = pdcDataFim;
        this.pdcStatusPedido = pdcStatusPedido;
        this.pdcStatusLogistica = pdcStatusLogistica;
    }

    public Integer getPdcId ()
    {
        return this.pdcId;
    }

    public void setPdcId (Integer pdcId)
    {
        this.pdcId = pdcId;
    }

    public String getPdcNumero ()
    {
        return this.pdcNumero;
    }

    public void setPdcNumero (String pdcNumero)
    {
        this.pdcNumero = pdcNumero;
    }

    public Date getPdcDataInicio ()
    {
        return this.pdcDataInicio;
    }

    public void setPdcDataInicio (Date pdcDataInicio)
    {
        this.pdcDataInicio = pdcDataInicio;
    }

    public Date getPdcDataFim ()
    {
        return this.pdcDataFim;
    }

    public void setPdcDataFim (Date pdcDataFim)
    {
        this.pdcDataFim = pdcDataFim;
    }

    public Integer getPdcStatusPedido ()
    {
        return this.pdcStatusPedido;
    }

    public void setPdcStatusPedido (Integer pdcStatusPedido)
    {
        this.pdcStatusPedido = pdcStatusPedido;
    }

    public Integer getPdcStatusLogistica ()
    {
        return this.pdcStatusLogistica;
    }

    public void setPdcStatusLogistica (Integer pdcStatusLogistica)
    {
        this.pdcStatusLogistica = pdcStatusLogistica;
    }

    public ClientesModel getCliente ()
    {
        return this.cliente;
    }

    public void setCliente (ClientesModel cliente)
    {
        this.cliente = cliente;
    }

    public EnderecosModel getEndereco ()
    {
        return this.endereco;
    }

    public void setEndereco (EnderecosModel endereco)
    {
        this.endereco = endereco;
    }

    public List<PedCuponsPromocoesModel> getListPedCuponsPromocoes ()
    {
        if (this.listPedCuponsPromocoes == null)
        {
            this.listPedCuponsPromocoes = new ArrayList<>();
        }

        return this.listPedCuponsPromocoes;
    }

    public void setListPedCuponsPromocoes (List<PedCuponsPromocoesModel> listPedCuponsPromocoes)
    {
        this.listPedCuponsPromocoes = listPedCuponsPromocoes;
    }

    public void removeAllFromListPedCuponsPromocoes ()
    {
        this.listPedCuponsPromocoes = new ArrayList<>();
    }

    public List<PedCuponsTrocasModel> getListPedCuponsTrocas ()
    {
        if (this.listPedCuponsPromocoes == null)
        {
            this.listPedCuponsPromocoes = new ArrayList<>();
        }

        return this.listPedCuponsTrocas;
    }

    public void setListPedCuponsTrocas (List<PedCuponsTrocasModel> listPedCuponsTrocas)
    {
        this.listPedCuponsTrocas = listPedCuponsTrocas;
    }

    public void removeAllFromListPedCuponsTrocas ()
    {
        this.listPedCuponsTrocas = new ArrayList<>();
    }

    public List<PedProdutosModel> getListPedProdutos ()
    {
        if (this.listPedCuponsPromocoes == null)
        {
            this.listPedCuponsPromocoes = new ArrayList<>();
        }

        return this.listPedProdutos;
    }

    public void setListPedProdutos (List<PedProdutosModel> listPedProdutos)
    {
        this.listPedProdutos = listPedProdutos;
    }

    public void removeAllFromListPedProdutos ()
    {
        this.listPedProdutos = new ArrayList<>();
    }

    public List<PedCartoesModel> getListPedCartoes ()
    {
        if (this.listPedCuponsPromocoes == null)
        {
            this.listPedCuponsPromocoes = new ArrayList<>();
        }

        return this.listPedCartoes;
    }

    public void setListPedCartoes (List<PedCartoesModel> listPedCartoes)
    {
        this.listPedCartoes = listPedCartoes;
    }

    public void removeAllFromListPedCartoes ()
    {
        this.listPedCartoes = new ArrayList<>();
    }

    public List<SaidasModel> getListSaidas ()
    {
        if (this.listPedCuponsPromocoes == null)
        {
            this.listPedCuponsPromocoes = new ArrayList<>();
        }

        return this.listSaidas;
    }

    public void setListSaidas (List<SaidasModel> listSaidas)
    {
        this.listSaidas = listSaidas;
    }

    public List<PedidosTrocasModel> getListPedTrocas ()
    {
        if (this.listPedCuponsPromocoes == null)
        {
            this.listPedCuponsPromocoes = new ArrayList<>();
        }

        return this.listPedTrocas;
    }

    public void setListPedTrocas (List<PedidosTrocasModel> listPedTrocas)
    {
        this.listPedTrocas = listPedTrocas;
    }

    public PedComFretesModel getFrete ()
    {
        return this.frete;
    }

    public void setFrete (PedComFretesModel frete)
    {
        this.frete = frete;
    }

    public double getValorTotalProdutos ()
    {
        double valorTotal = 0.0;

        for (PedProdutosModel aux : this.listPedProdutos)
        {
            valorTotal += aux.getProduto().getPrdPreco() * aux.getPpdQuantidade();
        }

        return valorTotal;
    }

    public String getStrStatusPedido ()
    {
        PedidosComprasListUtil utilTrocas = new PedidosComprasListUtil();

        HashMap<Integer, String> listTroca = utilTrocas.getListCompraPedidos();

        return listTroca.get(this.pdcStatusPedido);
    }

    public String getStrStatusLogistica ()
    {
        PedidosComprasListUtil utilTrocas = new PedidosComprasListUtil();

        HashMap<Integer, String> listTroca = utilTrocas.getListCompraLogistica();

        return listTroca.get(this.pdcStatusLogistica);
    }

    public boolean getStatusDataTroca (String dataPlus)
    {
        if (this.pdcDataFim != null)
        {
            Date dataEntregaP = Date.valueOf(this.pdcDataFim.toLocalDate().plusDays(Integer.parseInt(dataPlus)));
            Date dataAtual = new Date(new java.util.Date().getTime());

            return dataAtual.before(dataEntregaP);
        }

        return true;
    }

    public double getValorTotalCartoes ()
    {
        double valorTotal = 0.0;

        for (PedCartoesModel pedCartoes : this.listPedCartoes)
        {
            valorTotal += pedCartoes.getPctValor();
        }

        return valorTotal;
    }

    public double getValorTotalCuponsTroca ()
    {
        double valorTotal = 0.0;

        for (PedCuponsTrocasModel cupomTroca : this.listPedCuponsTrocas)
        {
            valorTotal += cupomTroca.getCupom().getCptSaldo();
        }

        return valorTotal;
    }

    public double getValorCupomPromocao ()
    {
        double valorTotal = 0.0;

        if (!this.listPedCuponsPromocoes.isEmpty())
        {
            for (PedProdutosModel produto : this.listPedProdutos)
            {
                for (CategoriasProdutosModel categoria : produto.getProduto().getListCategoriasProdutos())
                {
                    if (categoria.getCategoria().getCtgId() == this.listPedCuponsPromocoes.get(0).getCupom()
                            .getCategoria().getCtgId())
                    {
                        valorTotal += produto.getProduto().getPrdPreco() * produto.getPpdQuantidade();
                    }
                }
            }

            valorTotal = valorTotal * this.listPedCuponsPromocoes.get(0).getCupom().getCppDesconto() / 100;
        }

        return valorTotal;
    }

    public double getValorTotalCupons ()
    {
        return this.getValorTotalCuponsTroca() + this.getValorCupomPromocao();
    }

    public Double getValorPendenteTotal ()
    {
        return this.getValorTotalProdutos() + this.getFrete().getPcfValor() - this.getValorTotalCupons()
                - this.getValorTotalCartoes();
    }

    public double getValorTotalPedido ()
    {
        return this.getValorTotalProdutos() + this.getFrete().getPcfValor() - this.getValorTotalCupons();
    }

    @Override
    public boolean equals (Object o)
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
    public int hashCode ()
    {
        return Objects.hashCode(pdcId);
    }
}
