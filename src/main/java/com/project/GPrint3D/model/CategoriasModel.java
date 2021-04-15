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
@Table(name = "CATEGORIAS")
public class CategoriasModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ctg_id", insertable = false, updatable = false)
    private Integer ctgId;

    @NotEmpty(message = "Nome é obrigatório")
    @Column(name = "ctg_nome")
    private String ctgNome;

    @Column(name = "ctg_descricao")
    private String ctgDescricao;

    @Column(name = "ctg_ativo")
    private boolean ctgAtivo;

    @OneToMany(mappedBy = "categoria")
    private List<CuponsPromocoesModel> listCupons;

    @OneToMany(mappedBy = "categoria")
    private List<CategoriasProdutosModel> listCategoriasProdutos;

    public CategoriasModel() 
    {
        super();

        this.ctgId = 0;
        this.ctgNome = "";
        this.ctgDescricao = "";
        this.ctgAtivo = false;
    }

    public CategoriasModel(Integer ctgId, String ctgNome, String ctgDescricao, boolean ctgAtivo) 
    {
        super( );

        this.ctgId = ctgId;
        this.ctgNome = ctgNome;
        this.ctgDescricao = ctgDescricao;
        this.ctgAtivo = ctgAtivo;
    }

    public Integer getCtgId() 
    {
        return this.ctgId;
    }

    public void setCtgId(Integer ctgId) 
    {
        this.ctgId = ctgId;
    }

    public String getCtgNome() 
    {
        return this.ctgNome;
    }

    public void setCtgNome(String ctgNome) 
    {
        this.ctgNome = ctgNome;
    }

    public String getCtgDescricao() 
    {
        return this.ctgDescricao;
    }

    public void setCtgDescricao(String ctgDescricao) 
    {
        this.ctgDescricao = ctgDescricao;
    }

    public boolean isCtgAtivo() 
    {
        return this.ctgAtivo;
    }

    public boolean getCtgAtivo() 
    {
        return this.ctgAtivo;
    }

    public void setCtgAtivo(boolean ctgAtivo) 
    {
        this.ctgAtivo = ctgAtivo;
    }

    public List<CuponsPromocoesModel> getListCupons() 
    {
        return this.listCupons;
    }

    public void setListCupons(List<CuponsPromocoesModel> listCupons) 
    {
        this.listCupons = listCupons;
    }

    public List<CategoriasProdutosModel> getListCategoriasProdutos() 
    {
        return this.listCategoriasProdutos;
    }

    public void setListCategoriasProdutos(List<CategoriasProdutosModel> listCategoriasProdutos) 
    {
        this.listCategoriasProdutos = listCategoriasProdutos;
    }

    public boolean getVerifCategoria()
    {
        if (!this.getCtgAtivo())
        {
            return false;
        }
        
        for (CategoriasProdutosModel aux : this.listCategoriasProdutos)
        {
            if (aux.getProduto().isPrdAtivo() && (aux.getProduto().getPrdQuantidade() > 0))
            {
                return true;
            }
        }

        return false;
    }

    public String getStrCtgAtivo()
    {
        return this.ctgAtivo ? "Ativo" : "Inativo";
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof CategoriasModel)) 
        {
            return false;
        }

        CategoriasModel categoriasModel = (CategoriasModel) o;
        return Objects.equals(ctgId, categoriasModel.ctgId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(ctgId);
    }
}
