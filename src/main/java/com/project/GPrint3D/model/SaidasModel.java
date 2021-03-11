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
@Table(name = "SAIDAS")
public class SaidasModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sai_id", insertable = false, updatable = false)
    private Integer saiId;

    @NotEmpty(message = "Descrição é obrigatória")
    @Column(name = "sai_descricao")
    private String saiDescricao;

    @NotNull(message = "Quantidade é obrigatória")
    @Column(name = "sai_quantidade")
    private Integer saiQuantidade;

    @NotNull(message = "Data é obrigatória")
    @Column(name = "sai_data")
    private Date saiData;

    @ManyToOne
    @JoinColumn(name = "sai_usu_id", referencedColumnName = "usu_id")
    private UsuariosModel usuario;

    @OneToOne
    @JoinColumn(name = "sai_prd_id", referencedColumnName = "prd_id")
    private ProdutosModel produto;

    public SaidasModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.saiId = 0;
        this.saiDescricao = "";
        this.saiQuantidade = 0;
        this.saiData = new Date(dataAtual.getTime());
    }

    public SaidasModel(Integer saiId, String saiDescricao, Integer saiQuantidade, Date saiData) 
    {
        super( );

        this.saiId = saiId;
        this.saiDescricao = saiDescricao;
        this.saiQuantidade = saiQuantidade;
        this.saiData = saiData;
    }

    public Integer getSaiId() 
    {
        return this.saiId;
    }

    public void setSaiId(Integer saiId) 
    {
        this.saiId = saiId;
    }

    public String getSaiDescricao() 
    {
        return this.saiDescricao;
    }

    public void setSaiDescricao(String saiDescricao) 
    {
        this.saiDescricao = saiDescricao;
    }

    public Integer getSaiQuantidade() 
    {
        return this.saiQuantidade;
    }

    public void setSaiQuantidade(Integer saiQuantidade) 
    {
        this.saiQuantidade = saiQuantidade;
    }

    public Date getSaiData() 
    {
        return this.saiData;
    }

    public void setSaiData(Date saiData) 
    {
        this.saiData = saiData;
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
            
        if (!(o instanceof SaidasModel)) 
        {
            return false;
        }
        
        SaidasModel saidasModel = (SaidasModel) o;
        return Objects.equals(saiId, saidasModel.saiId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(saiId);
    }
}
