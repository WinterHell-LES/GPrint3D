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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ENTRADAS")
public class EntradasModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ent_id", insertable = false, updatable = false)
    private Integer entId;

    @NotNull(message = "Quantidade é obrigatória")
    @Column(name = "ent_quantidade")
    private Integer entQuantidade;

    @NotEmpty(message = "Fornecedor é obrigatório")
    @Column(name = "ent_fornecedor")
    private String entFornecedor;

    @NotNull(message = "Preço de custo é obrigatório")
    @Column(name = "ent_custo")
    private double entPrecoCusto;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "ent_data")
    private Date entData;

    @ManyToOne
    @JoinColumn(name = "ent_usu_id", referencedColumnName = "usu_id")
    private UsuariosModel usuario;

    @OneToOne
    @JoinColumn(name = "ent_prd_id", referencedColumnName = "prd_id")
    private ProdutosModel produto;

    public EntradasModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.entId = 0;
        this.entQuantidade = 0;
        this.entFornecedor = "";
        this.entPrecoCusto = 0.0;
        this.entData = new Date(dataAtual.getTime());
    }

    public EntradasModel(Integer entId, Integer entQuantidade, String entFornecedor, double entPrecoCusto, Date entData) 
    {
        super( );

        this.entId = entId;
        this.entQuantidade = entQuantidade;
        this.entFornecedor = entFornecedor;
        this.entPrecoCusto = entPrecoCusto;
        this.entData = entData;
    }

    public Integer getEntId() 
    {
        return this.entId;
    }

    public void setEntId(Integer entId) 
    {
        this.entId = entId;
    }

    public Integer getEntQuantidade() 
    {
        return this.entQuantidade;
    }

    public void setEntQuantidade(Integer entQuantidade) 
    {
        this.entQuantidade = entQuantidade;
    }

    public String getEntFornecedor() 
    {
        return this.entFornecedor;
    }

    public void setEntFornecedor(String entFornecedor) 
    {
        this.entFornecedor = entFornecedor;
    }

    public double getEntPrecoCusto() 
    {
        return this.entPrecoCusto;
    }

    public void setEntPrecoCusto(double entPrecoCusto) 
    {
        this.entPrecoCusto = entPrecoCusto;
    }

    public Date getEntData() 
    {
        return this.entData;
    }

    public void setEntData(Date entData) 
    {
        this.entData = entData;
    }

    public UsuariosModel getUsuario() 
    {
        return this.usuario;
    }

    public void setUsuario(UsuariosModel usuario) 
    {
        this.usuario = usuario;
    }

    public ProdutosModel getProduto() 
    {
        return this.produto;
    }

    public void setProduto(ProdutosModel produto) 
    {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof EntradasModel)) 
        {
            return false;
        }

        EntradasModel entradasModel = (EntradasModel) o;
        return Objects.equals(entId, entradasModel.entId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(entId);
    }
}
