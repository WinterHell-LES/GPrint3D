package com.project.GPrint3D.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "PRODUTOS")
public class ProdutosModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prd_id", insertable = false, updatable = false)
    private Integer prdId;

    @NotEmpty(message = "Nome é obrigatório")
    @Column(name = "prd_nome")
    private String prdNome;
    
    @NotEmpty(message = "Descrição é obrigatória")
    @Column(name = "prd_descricao")
    private String prdDescricao;

    @Column(name = "prd_dim_prd_al")
    private double prdDimPrdAl;

    @Column(name = "prd_dim_prd_la")
    private double prdDimPrdLa;

    @Column(name = "prd_dim_prd_pr")
    private double prdDimPrdPr;

    @Column(name = "prd_dim_prd_pe")
    private double prdDimPrdPe;

    @Column(name = "prd_dim_emb_al")
    private double prdDimEmbAl;

    @Column(name = "prd_dim_emb_la")
    private double prdDimEmbLa;

    @Column(name = "prd_dim_emb_pr")
    private double prdDimEmbPr;

    @Column(name = "prd_dim_emb_pe")
    private double prdDimEmbPe;

    @Column(name = "prd_fabricante")
    private String prdFabricante;

    @Column(name = "prd_modelo")
    private String prdModelo;

    @Column(name = "prd_quantidade")
    private Integer prdQuantidade;

    @NotEmpty(message = "Preço é obrigatório")
    @Column(name = "prd_preco")
    private double prdPreco;

    @OneToMany(mappedBy = "produto")
    private List<EntradasModel> listEntradas;

    @OneToMany(mappedBy = "produto")
    private List<SaidasModel> listSaidas;

    @OneToMany(mappedBy = "produto")
    private List<PedProdutosModel> listPedProdutos;

    @OneToMany(mappedBy = "produto")
    private List<PrdCarrinhosModel> listProdCarrinhos;

    @OneToMany(mappedBy = "produto")
    private List<CategoriasProdutosModel> listCategoriasProdutos;

    public ProdutosModel() 
    {
        this.prdId = 0;
        this.prdNome = "";
        this.prdDescricao = "";
        this.prdDimPrdAl = 0.0;
        this.prdDimPrdLa = 0.0;
        this.prdDimPrdPr = 0.0;
        this.prdDimPrdPe = 0.0;
        this.prdDimEmbAl = 0.0;
        this.prdDimEmbLa = 0.0;
        this.prdDimEmbPr = 0.0;
        this.prdDimEmbPe = 0.0;
        this.prdFabricante = "";
        this.prdModelo = "";
        this.prdQuantidade = 0;
        this.prdPreco = 0.0;
    }

    public ProdutosModel(Integer prdId, String prdNome, String prdDescricao, double prdDimPrdAl, double prdDimPrdLa, double prdDimPrdPr, double prdDimPrdPe, double prdDimEmbAl, double prdDimEmbLa, double prdDimEmbPr, double prdDimEmbPe, String prdFabricante, String prdModelo, Integer prdQuantidade, double prdPreco) 
    {
        this.prdId = prdId;
        this.prdNome = prdNome;
        this.prdDescricao = prdDescricao;
        this.prdDimPrdAl = prdDimPrdAl;
        this.prdDimPrdLa = prdDimPrdLa;
        this.prdDimPrdPr = prdDimPrdPr;
        this.prdDimPrdPe = prdDimPrdPe;
        this.prdDimEmbAl = prdDimEmbAl;
        this.prdDimEmbLa = prdDimEmbLa;
        this.prdDimEmbPr = prdDimEmbPr;
        this.prdDimEmbPe = prdDimEmbPe;
        this.prdFabricante = prdFabricante;
        this.prdModelo = prdModelo;
        this.prdQuantidade = prdQuantidade;
        this.prdPreco = prdPreco;
    }
    
    public Integer getPrdId() 
    {
        return this.prdId;
    }

    public void setPrdId(Integer prdId) 
    {
        this.prdId = prdId;
    }

    public String getPrdNome() 
    {
        return this.prdNome;
    }

    public void setPrdNome(String prdNome) 
    {
        this.prdNome = prdNome;
    }

    public String getPrdDescricao() 
    {
        return this.prdDescricao;
    }

    public void setPrdDescricao(String prdDescricao) 
    {
        this.prdDescricao = prdDescricao;
    }

    public double getPrdDimPrdAl() 
    {
        return this.prdDimPrdAl;
    }

    public void setPrdDimPrdAl(double prdDimPrdAl) 
    {
        this.prdDimPrdAl = prdDimPrdAl;
    }

    public double getPrdDimPrdLa() 
    {
        return this.prdDimPrdLa;
    }

    public void setPrdDimPrdLa(double prdDimPrdLa) 
    {
        this.prdDimPrdLa = prdDimPrdLa;
    }

    public double getPrdDimPrdPr() 
    {
        return this.prdDimPrdPr;
    }

    public void setPrdDimPrdPr(double prdDimPrdPr) 
    {
        this.prdDimPrdPr = prdDimPrdPr;
    }

    public double getPrdDimPrdPe() 
    {
        return this.prdDimPrdPe;
    }

    public void setPrdDimPrdPe(double prdDimPrdPe) 
    {
        this.prdDimPrdPe = prdDimPrdPe;
    }

    public double getPrdDimEmbAl() 
    {
        return this.prdDimEmbAl;
    }

    public void setPrdDimEmbAl(double prdDimEmbAl) 
    {
        this.prdDimEmbAl = prdDimEmbAl;
    }

    public double getPrdDimEmbLa() 
    {
        return this.prdDimEmbLa;
    }

    public void setPrdDimEmbLa(double prdDimEmbLa) 
    {
        this.prdDimEmbLa = prdDimEmbLa;
    }

    public double getPrdDimEmbPr() 
    {
        return this.prdDimEmbPr;
    }

    public void setPrdDimEmbPr(double prdDimEmbPr) 
    {
        this.prdDimEmbPr = prdDimEmbPr;
    }

    public double getPrdDimEmbPe() 
    {
        return this.prdDimEmbPe;
    }

    public void setPrdDimEmbPe(double prdDimEmbPe) 
    {
        this.prdDimEmbPe = prdDimEmbPe;
    }

    public String getPrdFabricante() 
    {
        return this.prdFabricante;
    }

    public void setPrdFabricante(String prdFabricante) 
    {
        this.prdFabricante = prdFabricante;
    }

    public String getPrdModelo() 
    {
        return this.prdModelo;
    }

    public void setPrdModelo(String prdModelo) 
    {
        this.prdModelo = prdModelo;
    }

    public Integer getPrdQuantidade() 
    {
        return this.prdQuantidade;
    }

    public void setPrdQuantidade(Integer prdQuantidade) 
    {
        this.prdQuantidade = prdQuantidade;
    }

    public double getPrdPreco() 
    {
        return this.prdPreco;
    }

    public void setPrdPreco(double prdPreco) 
    {
        this.prdPreco = prdPreco;
    }

    public List<EntradasModel> getListEntradas() 
    {
        return this.listEntradas;
    }

    public void setListEntradas(List<EntradasModel> listEntradas) 
    {
        this.listEntradas = listEntradas;
    }

    public List<SaidasModel> getListSaidas() 
    {
        return this.listSaidas;
    }

    public void setListSaidas(List<SaidasModel> listSaidas) 
    {
        this.listSaidas = listSaidas;
    }

    public List<PedProdutosModel> getListPedProdutos() 
    {
        return this.listPedProdutos;
    }

    public void setListPedProdutos(List<PedProdutosModel> listPedProdutos) 
    {
        this.listPedProdutos = listPedProdutos;
    }

    public List<PrdCarrinhosModel> getListProdCarrinhos() 
    {
        return this.listProdCarrinhos;
    }

    public void setListProdCarrinhos(List<PrdCarrinhosModel> listProdCarrinhos) 
    {
        this.listProdCarrinhos = listProdCarrinhos;
    }

    public List<CategoriasProdutosModel> getListCategoriasProdutos() 
    {
        return this.listCategoriasProdutos;
    }

    public void setListCategoriasProdutos(List<CategoriasProdutosModel> listCategoriasProdutos) 
    {
        this.listCategoriasProdutos = listCategoriasProdutos;
    }
    
    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof ProdutosModel)) 
        {
            return false;
        }
        
        ProdutosModel produtosModel = (ProdutosModel) o;
        return Objects.equals(prdId, produtosModel.prdId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(prdId);
    }
}