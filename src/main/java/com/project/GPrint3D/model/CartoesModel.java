package com.project.GPrint3D.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "CARTOES")
public class CartoesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crt_id", insertable = false, updatable = false)
    private Integer crtId;

    @NotEmpty(message = "Nome é obrigatório")
    @Column(name = "crt_nome")
    private String crtNome;

    @NotEmpty(message = "Número é obrigatório")
    @Column(name = "crt_numero")
    private String crtNumero;

    @NotEmpty(message = "Validade é obrigatória")
    @Column(name = "crt_validade")
    private String crtValidade;

    @NotEmpty(message = "CVV é obrigatório")
    @Column(name = "crt_cvv")
    private String crtCvv;

    @ManyToOne
    @JoinColumn(name = "crt_ban_id", referencedColumnName = "ban_id")
    private BandeirasModel bandeira;

    @ManyToOne
    @JoinColumn(name = "crt_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

	@OneToOne(mappedBy = "cartao")
    private CartoesPadroesModel cartaoPadrao;

    public CartoesModel() 
    {
        super();

        this.crtId = 0;
        this.crtNome = "";
        this.crtNumero = "";
        this.crtValidade = null;
        this.crtCvv = "";
    }

    public CartoesModel(Integer crtId, String crtNome, String crtNumero, String crtValidade, String crtCvv) 
    {
        super( );

        this.crtId = crtId;
        this.crtNome = crtNome;
        this.crtNumero = crtNumero;
        this.crtValidade = crtValidade;
        this.crtCvv = crtCvv;
    }

    public Integer getCrtId() 
    {
        return this.crtId;
    }

    public void setCrtId(Integer crtId) 
    {
        this.crtId = crtId;
    }

    public String getCrtNome() 
    {
        return this.crtNome;
    }

    public void setCrtNome(String crtNome) 
    {
        this.crtNome = crtNome;
    }

    public String getCrtNumero() 
    {
        return this.crtNumero;
    }

    public void setCrtNumero(String crtNumero) 
    {
        this.crtNumero = crtNumero;
    }

    public String getCrtValidade() 
    {
        return this.crtValidade;
    }

    public void setCrtValidade(String crtValidade) 
    {
        this.crtValidade = crtValidade;
    }

    public String getCrtCvv() 
    {
        return this.crtCvv;
    }

    public void setCrtCvv(String crtCvv) 
    {
        this.crtCvv = crtCvv;
    }

    public BandeirasModel getBandeira() 
    {
        return this.bandeira;
    }

    public void setBandeira(BandeirasModel bandeira) 
    {
        this.bandeira = bandeira;
    }

    public ClientesModel getCliente() 
    {
        return this.cliente;
    }

    public void setCliente(ClientesModel cliente) 
    {
        this.cliente = cliente;
    }

    public CartoesPadroesModel getCartaoPadrao()
    {
        return this.cartaoPadrao;
    }

    public void setCartaoPadrao(CartoesPadroesModel cartaoPadrao)
    {
        this.cartaoPadrao = cartaoPadrao;
    }


    @Override
    public boolean equals(Object o) 
    {
        if (o == this) 
        {
            return true;
        }

        if (!(o instanceof CartoesModel)) 
        {
            return false;
        }
        CartoesModel cartoesModel = (CartoesModel) o;
        return Objects.equals(crtId, cartoesModel.crtId);
    }

    @Override
    public int hashCode() 
    
    {
        return Objects.hashCode(crtId);
    }
}