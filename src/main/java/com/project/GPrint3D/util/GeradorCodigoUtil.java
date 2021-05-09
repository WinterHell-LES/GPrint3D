package com.project.GPrint3D.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GeradorCodigoUtil
{
    private String codigo;

    public GeradorCodigoUtil ()
    {
        this.codigo = "";
    }

    public GeradorCodigoUtil (String codigo)
    {
        this.codigo = codigo;
    }

    public String getCodigo ()
    {
        return this.codigo;
    }

    public void setCodigo (String codigo)
    {
        this.codigo = codigo;
    }

    public String getGerarCodigoPromocional ()
    {
        Date data = new Date();

        this.codigo = "GP3DCP" + new SimpleDateFormat("yyMMddHHmmss").format(data);

        return this.codigo;
    }

    public String getGerarCodigoTroca ()
    {
        Date data = new Date();

        this.codigo = "GP3DCT" + new SimpleDateFormat("yyMMddHHmmss").format(data);

        return this.codigo;
    }

    public String getGerarNumeroPedido ()
    {
        Date data = new Date();

        this.codigo = "GP3PDC" + new SimpleDateFormat("yyMMddHHmmss").format(data);

        return this.codigo;
    }
}
