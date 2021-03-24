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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CUPONS_PROMOCOES")
public class CuponsPromocoesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpp_id", insertable = false, updatable = false)
    private Integer cppId;

    @NotEmpty(message = "Nome é obrigatório")
    @Column(name = "cpp_nome")
    private String cppNome;

    @NotEmpty(message = "Código é obrigatório")
    @Column(name = "cpp_codigo")
    private String cppCodigo;

    @NotNull(message = "Desconto é obrigatório")
    @Column(name = "cpp_desconto")
    private Integer cppDesconto;

    @Column(name = "cpp_validade")
    private Date cppValidade;

    @ManyToOne
    @JoinColumn(name = "cpp_ctg_id", referencedColumnName = "ctg_id")
    private CategoriasModel categoria;

    @OneToMany(mappedBy = "cupom")
    private List<PedCuponsModel> listPedCupons;

    public CuponsPromocoesModel() 
    {
        super();

        this.cppId = 0;
        this.cppNome = "";
        this.cppCodigo = "";
        this.cppDesconto = 0;
        this.cppValidade = null;
    }

    public CuponsPromocoesModel(Integer cppId, String cppNome, String cppCodigo, Integer cppDesconto, Date cppValidade) 
    {
        super( );

        this.cppId = cppId;
        this.cppNome = cppNome;
        this.cppCodigo = cppCodigo;
        this.cppDesconto = cppDesconto;
        this.cppValidade = cppValidade;
    }

    public Integer getCppId() 
    {
        return this.cppId;
    }

    public void setCppId(Integer cppId) 
    {
        this.cppId = cppId;
    }

    public String getCppNome() 
    {
        return this.cppNome;
    }

    public void setCppNome(String cppNome) 
    {
        this.cppNome = cppNome;
    }

    public String getCppCodigo() 
    {
        return this.cppCodigo;
    }

    public void setCppCodigo(String cppCodigo) 
    {
        this.cppCodigo = cppCodigo;
    }

    public Integer getCppDesconto() 
    {
        return this.cppDesconto;
    }

    public void setCppDesconto(Integer cppDesconto) 
    {
        this.cppDesconto = cppDesconto;
    }

    public Date getCppValidade() 
    {
        return this.cppValidade;
    }

    public void setCppValidade(Date cppValidade) 
    {
        this.cppValidade = cppValidade;
    }

    public CategoriasModel getCategoria() 
    {
        return this.categoria;
    }

    public void setCategoria(CategoriasModel categoria) 
    {
        this.categoria = categoria;
    }

    public List<PedCuponsModel> getListPedCupons() 
    {
        return this.listPedCupons;
    }

    public void setListPedCupons(List<PedCuponsModel> listPedCupons) 
    {
        this.listPedCupons = listPedCupons;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof CuponsPromocoesModel)) 
        {
            return false;
        }
        
        CuponsPromocoesModel cuponsModel = (CuponsPromocoesModel) o;
        return Objects.equals(cppId, cuponsModel.cppId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(cppId);
    }
}
