package com.project.GPrint3D.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "LOG_TRASACOES")
public class LogTransacoesModel 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @NotNull(message = "data é obrigatória")
    @Column(name = "log_data")
    private Date logData;

    @NotEmpty(message = "Ação é obrigatória")
    @Column(name = "log_acao")
    private String logAcao;

    @NotEmpty(message = "Descrição é obrigatória")
    @Column(name = "log_descricao")
    private String logDescricao;

    @NotEmpty(message = "Usuário é obrigatório")
    @Column(name = "log_usuario")
    private String logUsuario;

    public LogTransacoesModel() 
    {
        super();

        java.util.Date dataAtual = new java.util.Date();

        this.logId = 0;
        this.logData = new Date(dataAtual.getTime());
        this.logAcao = "";
        this.logDescricao = "";
        this.logUsuario = "";
    }

    public LogTransacoesModel(Integer logId, Date logData, String logAcao, String logDescricao, String logUsuario) 
    {
        super( );

        this.logId = logId;
        this.logData = logData;
        this.logAcao = logAcao;
        this.logDescricao = logDescricao;
        this.logUsuario = logUsuario;
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

    public String getLogUsuario() 
    {
        return this.logUsuario;
    }

    public void setLogUsuario(String logUsuario) 
    {
        this.logUsuario = logUsuario;
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
