package com.project.GPrint3D.util.MailSender;

import java.util.List;

public class Mensagem 
{
    private String remetente;
    
    private List<String> listDestinatarios;

    private String assunto;

    private String corpo;

    public Mensagem(String remetente, List<String> listDestinatarios, String assunto, String corpo) 
    {
        this.remetente = remetente;
        this.listDestinatarios = listDestinatarios;
        this.assunto = assunto;
        this.corpo = corpo;
    }

    public String getRemetente() 
    {
        return this.remetente;
    }

    public void setRemetente(String remetente) 
    {
        this.remetente = remetente;
    }

    public List<String> getListDestinatarios() 
    {
        return this.listDestinatarios;
    }

    public void setListDestinatarios(List<String> listDestinatarios) 
    {
        this.listDestinatarios = listDestinatarios;
    }

    public String getAssunto() 
    {
        return this.assunto;
    }

    public void setAssunto(String assunto) 
    {
        this.assunto = assunto;
    }

    public String getCorpo() 
    {
        return this.corpo;
    }

    public void setCorpo(String corpo) 
    {
        this.corpo = corpo;
    }
}
