package com.project.GPrint3D.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENDERECOS_COBRANCAS_PADROES")
public class EndCobrancasPadroesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ecp_id", insertable = false, updatable = false)
    private Integer ecpId;

    @OneToOne
    @JoinColumn(name = "ecp_end_id", referencedColumnName = "end_id")
    private EnderecosModel endereco;

    @OneToOne
    @JoinColumn(name = "ecp_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    public EndCobrancasPadroesModel() 
    {
        super();

        this.ecpId = 0;
    }

    public EndCobrancasPadroesModel(Integer ecpId) 
    {
        super( );
        
        this.ecpId = ecpId;
    }

    public Integer getEcpId() 
    {
        return this.ecpId;
    }

    public void setEcpId(Integer ecpId) 
    {
        this.ecpId = ecpId;
    }

    public EnderecosModel getEndereco() 
    {
        return this.endereco;
    }

    public void setEndereco(EnderecosModel endereco) 
    {
        this.endereco = endereco;
    }

    public ClientesModel getCliente() 
    {
        return this.cliente;
    }

    public void setCliente(ClientesModel cliente) 
    {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
            
        if (!(o instanceof EndCobrancasPadroesModel)) 
        {
            return false;
        }

        EndCobrancasPadroesModel endCobrancasPadroesModel = (EndCobrancasPadroesModel) o;
        return Objects.equals(ecpId, endCobrancasPadroesModel.ecpId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(ecpId);
    }
}
