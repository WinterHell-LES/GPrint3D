package com.project.GPrint3D.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.project.GPrint3D.util.Listas.PedidosTrocasListUtil;

@Entity
@Table(name = "PEDIDOS_TROCAS")
public class PedidosTrocasModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdt_id", insertable = false, updatable = false)
    private Integer pdtId;

    @NotEmpty(message = "Número é obrigatório")
    @Column(name = "pdt_numero")
    private String pdtNumero;

    @NotNull(message = "Quantidade é obrigatória")
    @Column(name = "pdt_quantidade")
    private Integer pdtQuantidade;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "pdt_data")
    private Date pdtData;

    @Column(name = "pdt_escolha")
    private Integer pdtEscolha;

    @Column(name = "pdt_statuspedido")
    private Integer pdtStatusPedido;

    @Column(name = "pdt_statuslogistica")
    private Integer pdtStatusLogistica;

    @NotEmpty(message = "Descrição é obrigatório")
    @Column(name = "pdt_descricao")
    private String pdtDescricao;

    @OneToOne
    @JoinColumn(name = "pdt_ppd_id", referencedColumnName = "ppd_id")
    private PedProdutosModel pedProduto;

    @ManyToOne
    @JoinColumn(name = "pdt_pdc_id", referencedColumnName = "pdc_id")
    private PedidosComprasModel pedidoCompra;

    @ManyToOne
    @JoinColumn(name = "pdt_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    public PedidosTrocasModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.pdtId = 0;
        this.pdtNumero = "";
        this.pdtQuantidade = 0;
        this.pdtData = new Date(dataAtual.getTime());
        this.pdtEscolha = 0;
        this.pdtStatusPedido = 0;
        this.pdtStatusLogistica = 0;
        this.pdtDescricao = "";
    }

    public PedidosTrocasModel(Integer pdtId, String pdtNumero, Integer pdtQuantidade, Date pdtData, Integer pdtEscolha, Integer pdtStatusPedido, Integer pdtStatusLogistica, String pdtDescricao) 
    {
        super( );

        this.pdtId = pdtId;
        this.pdtNumero = pdtNumero;
        this.pdtQuantidade = pdtQuantidade;
        this.pdtData = pdtData;
        this.pdtEscolha = pdtEscolha;
        this.pdtStatusPedido = pdtStatusPedido;
        this.pdtStatusLogistica = pdtStatusLogistica;
        this.pdtDescricao = pdtDescricao;
    }

    public Integer getPdtId() 
    {
        return this.pdtId;
    }

    public void setPdtId(Integer pdtId) 
    {
        this.pdtId = pdtId;
    }

    public String getPdtNumero() 
    {
        return this.pdtNumero;
    }

    public void setPdtNumero(String pdtNumero) 
    {
        this.pdtNumero = pdtNumero;
    }

    public Integer getPdtQuantidade() 
    {
        return this.pdtQuantidade;
    }

    public void setPdtQuantidade(Integer pdtQuantidade) 
    {
        this.pdtQuantidade = pdtQuantidade;
    }

    public Date getPdtData() 
    {
        return this.pdtData;
    }

    public void setPdtData(Date pdtData) 
    {
        this.pdtData = pdtData;
    }

    public Integer getPdtEscolha() 
    {
        return this.pdtEscolha;
    }

    public void setPdtEscolha(Integer pdtEscolha) 
    {
        this.pdtEscolha = pdtEscolha;
    }

    public Integer getPdtStatusPedido() 
    {
        return this.pdtStatusPedido;
    }

    public void setPdtStatusPedido(Integer pdtStatusPedido) 
    {
        this.pdtStatusPedido = pdtStatusPedido;
    }

    public Integer getPdtStatusLogistica() 
    {
        return this.pdtStatusLogistica;
    }

    public void setPdtStatusLogistica(Integer pdtStatusLogistica) 
    {
        this.pdtStatusLogistica = pdtStatusLogistica;
    }

    public String getPdtDescricao() 
    {
        return this.pdtDescricao;
    }

    public void setPdtDescricao(String pdtDescricao) 
    {
        this.pdtDescricao = pdtDescricao;
    }

    public PedProdutosModel getPedProduto() 
    {
        return this.pedProduto;
    }

    public void setPedProduto(PedProdutosModel pedProduto) 
    {
        this.pedProduto = pedProduto;
    }

    public PedidosComprasModel getPedidoCompra() 
    {
        return this.pedidoCompra;
    }

    public void setPedidoCompra(PedidosComprasModel pedidoCompra) 
    {
        this.pedidoCompra = pedidoCompra;
    }

    public ClientesModel getCliente() 
    {
        return this.cliente;
    }

    public void setCliente(ClientesModel cliente) 
    {
        this.cliente = cliente;
    }

    public String getStrStatusPedido()
    {        
        PedidosTrocasListUtil utilTrocas = new PedidosTrocasListUtil();

        HashMap<Integer, String> listTroca = new HashMap<>();

        switch (this.pdtEscolha)
        {
            case 0:
                listTroca = utilTrocas.getListTrocaPedidosIni();
            break;
            case 1:
                listTroca = utilTrocas.getListTrocaPedidosTroca();
            break;
            case 2:
                listTroca = utilTrocas.getListTrocaPedidosCupom();
            break;
            case 3:
                listTroca = utilTrocas.getListTrocaPedidosReemb();
            break;
            default:
                System.out.println("Erro na escolha da troca");
        }
        
        String status = listTroca.get(this.pdtStatusPedido);

        return status;
    }

    public String getStrStatusLogistica()
    {
        PedidosTrocasListUtil utilTrocas = new PedidosTrocasListUtil();

        HashMap<Integer, String> listTroca = utilTrocas.getListTrocaLogisticaTroca();
        
        String status = listTroca.get(this.pdtStatusLogistica);

        return status;
    } 

    public String getStrEscolha()
    {
        PedidosTrocasListUtil utilTrocas = new PedidosTrocasListUtil();

        HashMap<Integer, String> listTroca = utilTrocas.getListEscolha();
        
        String status = listTroca.get(this.pdtEscolha);

        return status;
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
