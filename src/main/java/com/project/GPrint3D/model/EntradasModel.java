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

    @NotEmpty(message = "Descrição é obrigatória")
    @Column(name = "ent_descricao")
    private String entDescricao;

    @NotNull(message = "Quantidade é obrigatória")
    @Column(name = "ent_quantidade")
    private Integer entQuantidade;

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
        this.entDescricao = "";
        this.entQuantidade = 0;
        this.entData = new Date(dataAtual.getTime());
    }

    public EntradasModel(Integer entId, String entDescricao, Integer entQuantidade, Date entData) 
    {
        super( );

        this.entId = entId;
        this.entDescricao = entDescricao;
        this.entQuantidade = entQuantidade;
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

    public String getEntDescricao() 
    {
        return this.entDescricao;
    }

    public void setEntDescricao(String entDescricao) 
    {
        this.entDescricao = entDescricao;
    }

    public Integer getEntQuantidade() 
    {
        return this.entQuantidade;
    }

    public void setEntQuantidade(Integer entQuantidade) 
    {
        this.entQuantidade = entQuantidade;
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
