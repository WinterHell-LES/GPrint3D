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
@Table(name = "ENDERECOS_ENTREGAS_PADROES")
public class EndEntregasPadroesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eep_id", insertable = false, updatable = false)
    private Integer eepId;

    @OneToOne
    @JoinColumn(name = "eep_end_id", referencedColumnName = "end_id")
    private EnderecosModel endereco;

    @OneToOne
    @JoinColumn(name = "eep_cli_id", referencedColumnName = "cli_id")
    private ClientesModel cliente;

    public EndEntregasPadroesModel() 
    {
        super();

        this.eepId = 0;
    }

    public EndEntregasPadroesModel(Integer eepId) 
    {
        super( );
        
        this.eepId = eepId;
    }

    public Integer getEepId() 
    {
        return this.eepId;
    }

    public void setEepId(Integer eepId) 
    {
        this.eepId = eepId;
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
            
        if (!(o instanceof EndEntregasPadroesModel)) 
        {
            return false;
        }

        EndEntregasPadroesModel endEntregasPadroesModel = (EndEntregasPadroesModel) o;
        return Objects.equals(eepId, endEntregasPadroesModel.eepId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(eepId);
    }
}
