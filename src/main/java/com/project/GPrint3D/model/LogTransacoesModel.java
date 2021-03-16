package com.project.GPrint3D.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOG_TRANSACOES")
public class LogTransacoesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @Column(name = "log_data")
    private Date logData;

    @Column(name = "log_acao")
    private String logAcao;

    @Column(name = "log_descricao")
    private String logDescricao;

    @Column(name = "log_tabela")
    private String logTabela;

    @Column(name = "log_dado")
    private Integer logDado;

    public LogTransacoesModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.logId = 0;
        this.logData = new Date(dataAtual.getTime());
        this.logAcao = "";
        this.logDescricao = "";
        this.logDado = 0;
    }

    public LogTransacoesModel(Integer logId, Date logData, String logAcao, String logDescricao, Integer logDado) 
    {
        super( );

        this.logId = logId;
        this.logData = logData;
        this.logAcao = logAcao;
        this.logDescricao = logDescricao;
        this.logDado = logDado;
    }

    public Integer getLogId() 
    {
        return this.logId;
    }

    public void setLogId(Integer logId) 
    {
        this.logId = logId;
    }

    public Date getLogData() 
    {
        return this.logData;
    }

    public void setLogData(Date logData) 
    {
        this.logData = logData;
    }

    public String getLogAcao() 
    {
        return this.logAcao;
    }

    public void setLogAcao(String logAcao) 
    {
        this.logAcao = logAcao;
    }

    public String getLogDescricao() 
    {
        return this.logDescricao;
    }

    public void setLogDescricao(String logDescricao) 
    {
        this.logDescricao = logDescricao;
    }

    public String getLogTabela() 
    {
        return this.logTabela;
    }

    public void setLogTabela(String logTabela) 
    {
        this.logTabela = logTabela;
    }

    public Integer getLogDado() 
    {
        return this.logDado;
    }

    public void setLogDado(Integer logDado) 
    {
        this.logDado = logDado;
    }

    @Override
    public boolean equals(Object o) 
    {
        if (o == this)
        {
            return true;
        }
    
        if (!(o instanceof LogTransacoesModel)) 
        {
            return false;
        }

        LogTransacoesModel logTransacoesModel = (LogTransacoesModel) o;
        return Objects.equals(logId, logTransacoesModel.logId);
    }

    @Override
    public int hashCode() 
    {
        return Objects.hashCode(logId);
    }
}
