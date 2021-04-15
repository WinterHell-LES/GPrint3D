package com.project.GPrint3D.util.Listas;

import java.util.HashMap;

public class PedidosTrocasListUtil 
{
    private HashMap<Integer, String> listTrocaPedidosIni = new HashMap<>();
    private HashMap<Integer, String> listTrocaPedidosTroca = new HashMap<>();
    private HashMap<Integer, String> listTrocaPedidosCupom = new HashMap<>();
    private HashMap<Integer, String> listTrocaPedidosReemb = new HashMap<>();
    private HashMap<Integer, String> listTrocaPedidosRecus = new HashMap<>();
    private HashMap<Integer, String> listTrocaLogistica = new HashMap<>();
    private HashMap<Integer, String> listTrocaLogisticaTroca = new HashMap<>();
    private HashMap<Integer, String> listTrocaLogisticaRecus = new HashMap<>();
    private HashMap<Integer, String> listEscolha = new HashMap<>();

    public PedidosTrocasListUtil() 
    {
        listEscolha.put(0, "-");
        listEscolha.put(1, "Troca");
        listEscolha.put(2, "Cupom");
        listEscolha.put(3, "Reembolso");
        listEscolha.put(4, "Recusado");
        
        listTrocaPedidosIni.put(0, "Pedido recebido");
        listTrocaPedidosIni.put(1, "Aguardando recebimento do produto");
        listTrocaPedidosIni.put(2, "Em análise");
        listTrocaPedidosIni.put(3, "Pedido aprovado");
        listTrocaPedidosIni.put(4, "Pedido recusado");

        listTrocaPedidosTroca.put(0, "Pedido aprovado");
        listTrocaPedidosTroca.put(1, "Nota fiscal disponível");
        listTrocaPedidosTroca.put(2, "Em transporte");
        listTrocaPedidosTroca.put(3, "Pedido entregue");

        listTrocaPedidosCupom.put(0, "Pedido aprovado");
        listTrocaPedidosCupom.put(1, "Gerando cupom de troca");
        listTrocaPedidosCupom.put(2, "Cupom de troca gerado");

        listTrocaPedidosReemb.put(0, "Pedido aprovado");
        listTrocaPedidosReemb.put(1, "Processando valor de reembolso");
        listTrocaPedidosReemb.put(2, "Valor de reembolso creditado");

        listTrocaPedidosRecus.put(0, "Pedido recusado");
        listTrocaPedidosRecus.put(1, "Nota fiscal disponível");
        listTrocaPedidosRecus.put(2, "Em transporte");
        listTrocaPedidosRecus.put(3, "Pedido entregue");

        listTrocaLogistica.put(0, "Não aplicável");

        listTrocaLogisticaTroca.put(0, "Aguardando nota fiscal");
        listTrocaLogisticaTroca.put(1, "Separando pedido");
        listTrocaLogisticaTroca.put(2, "Embalando pedido");
        listTrocaLogisticaTroca.put(3, "Enviando para transportadora");
        listTrocaLogisticaTroca.put(4, "Em transporte");
        listTrocaLogisticaTroca.put(5, "Pedido entregue");

        listTrocaLogisticaRecus.put(0, "Separando pedido");
        listTrocaLogisticaRecus.put(1, "Embalando pedido");
        listTrocaLogisticaRecus.put(2, "Enviando para transportadora");
        listTrocaLogisticaRecus.put(3, "Em transporte");
        listTrocaLogisticaRecus.put(4, "Pedido entregue");
    }

    public HashMap<Integer,String> getListTrocaPedidosIni() 
    {
        return this.listTrocaPedidosIni;
    }

    public HashMap<Integer,String> getListTrocaPedidosTroca() 
    {
        return this.listTrocaPedidosTroca;
    }

    public HashMap<Integer,String> getListTrocaPedidosCupom() 
    {
        return this.listTrocaPedidosCupom;
    }

    public HashMap<Integer,String> getListTrocaPedidosReemb() 
    {
        return this.listTrocaPedidosReemb;
    }

    public HashMap<Integer,String> getListTrocaPedidosRecus() 
    {
        return this.listTrocaPedidosRecus;
    }

    public HashMap<Integer,String> getListTrocaLogistica() 
    {
        return this.listTrocaLogistica;
    }

    public HashMap<Integer,String> getListTrocaLogisticaTroca() 
    {
        return this.listTrocaLogisticaTroca;
    }

    public HashMap<Integer,String> getListTrocaLogisticaRecus() 
    {
        return this.listTrocaLogisticaRecus;
    }

    public HashMap<Integer,String> getListEscolha() 
    {
        return this.listEscolha;
    }

    public HashMap<Integer,String> getListTrocaEscolha(int escolha) 
    {
        switch (escolha)
        {
            case 0:
                return this.listTrocaPedidosIni;
            case 1:
                return this.listTrocaPedidosTroca;
            case 2:
                return this.listTrocaPedidosCupom;
            case 3:
                return this.listTrocaPedidosReemb;
            case 4:
                return this.listTrocaPedidosRecus;
            default:
                return new HashMap<>();
        }
    }

    public HashMap<Integer,String> getListTrocaLog(int escolha)
    {
        switch (escolha)
        {
            case 0:
                return this.listTrocaLogistica;
            case 1:
                return this.listTrocaLogisticaTroca;
            case 2:
                return this.listTrocaLogistica;
            case 3:
                return this.listTrocaLogistica;
            case 4:
                return this.listTrocaLogisticaRecus;
            default:
                return new HashMap<>();
        }
    }
}
