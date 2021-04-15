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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BANDEIRAS")
public class BandeirasModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ban_id", insertable = false, updatable = false)
    private Integer banId;

    @NotEmpty(message = "Nome é obrigatório")
    @Column(name = "ban_nome")
    private String banNome;

    @NotNull(message = "teste")
    @Column(name = "ban_ativo")
    private boolean banAtivo;

    @OneToMany(mappedBy = "bandeira")
    private List<CartoesModel> listCartoes;

    public BandeirasModel() 
    {
        super();

        this.banId = 0;
        this.banNome = "";
        this.banAtivo = false;
    }

    public BandeirasModel(Integer banId, String banNome, boolean banAtivo) 
    {
        super( );
        
        this.banId = banId;
        this.banNome = banNome;
        this.banAtivo = banAtivo;
    }

    public Integer getBanId() 
    {
        return this.banId;
    }

    public void setBanId(Integer banId) 
    {
        this.banId = banId;
    }

    public String getBanNome() 
    {
        return this.banNome;
    }

    public void setBanNome(String banNome) 
    {
        this.banNome = banNome;
    }

    public List<CartoesModel> getListCartoes() 
    {
        return this.listCartoes;
    }

    public void setListCartoes(List<CartoesModel> listCartoes) 
    {
        this.listCartoes = listCartoes;
    }

    public boolean isBanAtivo() 
    {
        return this.banAtivo;
    }

    public boolean getBanAtivo() 
    {
        return this.banAtivo;
    }

    public void setBanAtivo(boolean banAtivo) 
    {
        this.banAtivo = banAtivo;
    }

    public String getStrBanAtivo()
    {
        return this.banAtivo ? "Ativo" : "Inativo";
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof BandeirasModel)) 
        {
            return false;
        }

        BandeirasModel bandeirasModel = (BandeirasModel) o;
        return Objects.equals(banId, bandeirasModel.banId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(banId);
    }
}
