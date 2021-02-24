package com.project.GPrint3D.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Pessoa implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private List <Documento> documentos = new ArrayList <Documento>();

    public Pessoa() 
    {
    }

    public List<Documento> getDocumentos() 
    {
        return this.documentos;
    }

    public void setDocumentos(List<Documento> documentos) 
    {
        this.documentos = documentos;
    }
}
