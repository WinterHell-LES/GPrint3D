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

    @Column(name = "prd_quantidade")
    private Integer prdQuantidade;

    @NotEmpty(message = "Preço é obrigatório")
    @Column(name = "prd_preco")
    private double prdPreco;

    @OneToOne
    @JoinColumn(name = "prd_ctg_id", referencedColumnName = "ctg_id")
    private CategoriasModel categoria;

    @OneToMany(mappedBy = "produto")
    private List<EntradasModel> listEntradas;

    @OneToMany(mappedBy = "produto")
    private List<SaidasModel> listSaidas;

    @OneToMany(mappedBy = "produto")
    private List<PedProdutosModel> listPedProdutos;

    @OneToMany(mappedBy = "produto")
    private List<PrdCarrinhosModel> listProdCarrinhos;

    public ProdutosModel() 
    {
        super();

        this.prdId = 0;
        this.prdNome = "";
        this.prdDescricao = "";
        this.prdQuantidade = 0;
        this.prdPreco = 0.0;
    }

    public ProdutosModel(Integer prdId, String prdNome, String prdDescricao, Integer prdQuantidade, double prdPreco) 
    {
        super( );

        this.prdId = prdId;
        this.prdNome = prdNome;
        this.prdDescricao = prdDescricao;
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

    public CategoriasModel getCategoria() 
    {
        return this.categoria;
    }

    public void setCategoria(CategoriasModel categoria) 
    {
        this.categoria = categoria;
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