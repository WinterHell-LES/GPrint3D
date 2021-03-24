package com.project.GPrint3D.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CuponsUtil 
{
    private String codigo;

    public CuponsUtil() 
    {
        this.codigo = "";
    }

    public CuponsUtil(String codigo) 
    {
        this.codigo = codigo;
    }

    public String getCodigo() 
    {
        return this.codigo;
    }

    public void setCodigo(String codigo) 
    {
        this.codigo = codigo;
    }

    public String getGerarCodigoPromocional()
    {
        Date data = new Date();

        this.codigo = "GP3DCP" + new SimpleDateFormat("yyMMddHHmmss").format(data);

        return this.codigo;
    }

    public String getGerarCodigoTroca()
    {
        Date data = new Date();

        this.codigo = "GP3DCT" + new SimpleDateFormat("yyMMddHHmmss").format(data);

        return this.codigo;
    }
}
