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
@Table(name = "PRODUTOS_CARRINHOS")
public class PrdCarrinhosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pcr_id", insertable = false, updatable = false)
    private Integer pcrId;

    @NotNull(message = "Quantidade é obrigatória")
    @Column(name = "pcr_quantidade")
    private Integer pcrQuantidade;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "pcr_data")
    private Date pcrData;

    @NotNull(message = "Estado é obrigatório")
    @Column(name = "pcr_ativo")
    private boolean pcrAtivo;

    @ManyToOne
    @JoinColumn(name = "pcr_prd_id", referencedColumnName = "prd_id")
    private ProdutosModel produto;

    @ManyToOne
    @JoinColumn(name = "pcr_car_id", referencedColumnName = "car_id")
    private CarrinhosModel carrinho;

    public PrdCarrinhosModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.pcrId = 0;
        this.pcrQuantidade = 1;
        this.pcrData = new Date(dataAtual.getTime());
        this.pcrAtivo = true;
    }

    public PrdCarrinhosModel(Integer pcrId, Integer pcrQuantidade, Date pcrData, boolean pcrAtivo) 
    {
        super( );

        java.util.Date dataAtual = new java.util.Date();

        this.pcrId = pcrId;
        this.pcrQuantidade = pcrQuantidade;
        this.pcrData = new Date(dataAtual.getTime());
        this.pcrAtivo = true;
    }

    public PrdCarrinhosModel(CarrinhosModel carrinho)
    {
        super( );

        java.util.Date dataAtual = new java.util.Date();

        this.pcrId = 0;
        this.pcrQuantidade = 1;
        this.pcrData = new Date(dataAtual.getTime());
        this.pcrAtivo = true;
        this.carrinho = carrinho;
    }

    public Integer getPcrId() 
    {
        return this.pcrId;
    }

    public void setPcrId(Integer pcrId) 
    {
        this.pcrId = pcrId;
    }

    public Integer getPcrQuantidade() 
    {
        return this.pcrQuantidade;
    }

    public void setPcrQuantidade(Integer pcrQuantidade) 
    {
        this.pcrQuantidade = pcrQuantidade;
    }

    public ProdutosModel getProduto() 
    {
        return this.produto;
    }

    public void setProduto(ProdutosModel produto) 
    {
        this.produto = produto;
    }

    public CarrinhosModel getCarrinho() 
    {
        return this.carrinho;
    }

    public void setCarrinho(CarrinhosModel carrinho) 
    {
        this.carrinho = carrinho;
    }

    public Date getPcrData() 
    {
        return this.pcrData;
    }

    public void setPcrData(Date pcrData) 
    {
        this.pcrData = pcrData;
    }

    public boolean isPcrAtivo() 
    {
        return this.pcrAtivo;
    }

    public boolean getPcrAtivo() 
    {
        return this.pcrAtivo;
    }

    public void setPcrAtivo(boolean pcrAtivo) 
    {
        this.pcrAtivo = pcrAtivo;
    }

    public void aumentarProduto()
    {
        java.util.Date dataAtual = new java.util.Date();

        this.pcrQuantidade = this.pcrQuantidade + 1;
        this.pcrData = new Date(dataAtual.getTime());
    }

    public void diminuirProduto()
    {
        java.util.Date dataAtual = new java.util.Date();

        this.pcrQuantidade = this.pcrQuantidade - 1;
        this.pcrData = new Date(dataAtual.getTime());
    }

    public void atualizarQtdProduto(Integer quantidade)
    {
        java.util.Date dataAtual = new java.util.Date();

        this.pcrQuantidade = quantidade;
        this.pcrData = new Date(dataAtual.getTime());
    }

    public String getMotivoInativacao(String dataPlus)
    {
        if (this.pcrQuantidade > this.produto.getPrdQuantidade())
        {
            return "Indisponíbilidade do produto";
        }

        Date dataCarrinhoP = Date.valueOf(this.pcrData.toLocalDate().plusDays(Integer.parseInt(dataPlus)));
        Date dataAtual = new Date(new java.util.Date().getTime());

        if (dataAtual.after(dataCarrinhoP))
        {
            return "Prazo de compra expirado";
        }

        return "Motivo desconhecido";
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof PrdCarrinhosModel)) 
        {
            return false;
        }

        PrdCarrinhosModel prodCarrinhosModel = (PrdCarrinhosModel) o;
        return Objects.equals(pcrId, prodCarrinhosModel.pcrId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(pcrId);
    }
}