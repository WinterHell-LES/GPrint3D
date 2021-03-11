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
@Table(name = "CUPONS")
public class CuponsModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cpn_id", insertable = false, updatable = false)
    private Integer cpnId;

    @NotEmpty(message = "Nome é obrigatório")
    @Column(name = "cpn_nome")
    private String cpnNome;

    @NotEmpty(message = "Código é obrigatório")
    @Column(name = "cpn_codigo")
    private String cpnCodigo;

    @NotNull(message = "Desconto é obrigatório")
    @Column(name = "cpn_desconto")
    private Integer cpnDesconto;

    @Column(name = "cpn_validade")
    private Date cpnValidade;

    @ManyToOne
    @JoinColumn(name = "cpn_ctg_id", referencedColumnName = "ctg_id")
    private CategoriasModel categoria;

    @OneToMany(mappedBy = "cupom")
    private List<PedCuponsModel> listPedCupons;

    public CuponsModel() 
    {
        super();

        this.cpnId = 0;
        this.cpnNome = "";
        this.cpnCodigo = "";
        this.cpnDesconto = 0;
        this.cpnValidade = null;
    }

    public CuponsModel(Integer cpnId, String cpnNome, String cpnCodigo, Integer cpnDesconto, Date cpnValidade) 
    {
        super( );

        this.cpnId = cpnId;
        this.cpnNome = cpnNome;
        this.cpnCodigo = cpnCodigo;
        this.cpnDesconto = cpnDesconto;
        this.cpnValidade = cpnValidade;
    }

    public Integer getCpnId() 
    {
        return this.cpnId;
    }

    public void setCpnId(Integer cpnId) 
    {
        this.cpnId = cpnId;
    }

    public String getCpnNome() 
    {
        return this.cpnNome;
    }

    public void setCpnNome(String cpnNome) 
    {
        this.cpnNome = cpnNome;
    }

    public String getCpnCodigo() 
    {
        return this.cpnCodigo;
    }

    public void setCpnCodigo(String cpnCodigo) 
    {
        this.cpnCodigo = cpnCodigo;
    }

    public Integer getCpnDesconto() 
    {
        return this.cpnDesconto;
    }

    public void setCpnDesconto(Integer cpnDesconto) 
    {
        this.cpnDesconto = cpnDesconto;
    }

    public Date getCpnValidade() 
    {
        return this.cpnValidade;
    }

    public void setCpnValidade(Date cpnValidade) 
    {
        this.cpnValidade = cpnValidade;
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
            
        if (!(o instanceof CuponsModel)) 
        {
            return false;
        }
        
        CuponsModel cuponsModel = (CuponsModel) o;
        return Objects.equals(cpnId, cuponsModel.cpnId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(cpnId);
    }
}
