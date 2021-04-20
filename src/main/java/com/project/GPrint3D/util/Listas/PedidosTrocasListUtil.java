package com.project.GPrint3D.util.Listas;

import java.util.HashMap;

public class PedidosTrocasListUtil 
{
    private HashMap<Integer, String> listTrocaPedidosIni = new HashMap<>();
    private HashMap<Integer, String> listTrocaPedidosIniCli = new HashMap<>();
    private HashMap<Integer, String> btListTrocaPedidosIniCli = new HashMap<>();

    private HashMap<Integer, String> listTrocaPedidosTroca = new HashMap<>();
    private HashMap<Integer, String> btListTrocaPedidosTroca = new HashMap<>();

    private HashMap<Integer, String> listTrocaPedidosCupom = new HashMap<>();
    private HashMap<Integer, String> btListTrocaPedidosCupom = new HashMap<>();

    private HashMap<Integer, String> listTrocaPedidosReemb = new HashMap<>();
    private HashMap<Integer, String> btListTrocaPedidosReemb = new HashMap<>();

    private HashMap<Integer, String> listTrocaPedidosRecus = new HashMap<>();
    private HashMap<Integer, String> btListTrocaPedidosRecus = new HashMap<>();


    private HashMap<Integer, String> listTrocaLogistica = new HashMap<>();
    private HashMap<Integer, String> btListTrocaLogistica = new HashMap<>();

    private HashMap<Integer, String> listTrocaLogisticaTroca = new HashMap<>();
    private HashMap<Integer, String> btListTrocaLogisticaTroca = new HashMap<>();

    private HashMap<Integer, String> listTrocaLogisticaRecus = new HashMap<>();
    private HashMap<Integer, String> btListTrocaLogisticaRecus = new HashMap<>();
    

    private HashMap<Integer, String> listEscolha = new HashMap<>();

    public PedidosTrocasListUtil() 
    {
        // Controle dos pedidos

        listEscolha.put(0, "-");
        listEscolha.put(1, "Troca");
        listEscolha.put(2, "Cupom");
        listEscolha.put(3, "Reembolso");
        listEscolha.put(4, "Recusado");
        
        // Controle do inicio do pedido
        listTrocaPedidosIni.put(0, "Pedido recebido");
        listTrocaPedidosIni.put(1, "Aguardando recebimento do produto");
        listTrocaPedidosIni.put(2, "Em análise");
        listTrocaPedidosIni.put(3, "Pedido aprovado");
        listTrocaPedidosIni.put(4, "Pedido recusado");
        
        listTrocaPedidosIniCli.put(0, "Pedido recebido");
        listTrocaPedidosIniCli.put(1, "Aguardando recebimento do produto");
        listTrocaPedidosIniCli.put(2, "Em análise");
        listTrocaPedidosIniCli.put(3, "Pedido aprovado");

        btListTrocaPedidosIniCli.put(0, "Aguardar recebimento do produto");
        btListTrocaPedidosIniCli.put(1, "Analisar produto");
        btListTrocaPedidosIniCli.put(2, "Aprovar pedido");

        // Controle do pedido de troca
        listTrocaPedidosTroca.put(0, "Pedido aprovado");
        listTrocaPedidosTroca.put(1, "Nota fiscal disponível");
        listTrocaPedidosTroca.put(2, "Em transporte");
        listTrocaPedidosTroca.put(3, "Pedido entregue");

        btListTrocaPedidosTroca.put(0, "Gerar nota fiscal");

        // Controle do pedido de cupom
        listTrocaPedidosCupom.put(0, "Pedido aprovado");
        listTrocaPedidosCupom.put(1, "Gerando cupom de troca");
        listTrocaPedidosCupom.put(2, "Cupom de troca gerado");

        btListTrocaPedidosCupom.put(0, "Gerar cupom de troca");
        btListTrocaPedidosCupom.put(1, "Confirmar cupom de troca");


        // Controle do pedido de reembolso
        listTrocaPedidosReemb.put(0, "Pedido aprovado");
        listTrocaPedidosReemb.put(1, "Processando valor de reembolso");
        listTrocaPedidosReemb.put(2, "Valor de reembolso creditado");

        btListTrocaPedidosReemb.put(0, "Iniciar processamento do valor de reembolso");
        btListTrocaPedidosReemb.put(1, "Confirmar processamento do valor de reembolso");


        // Controle do pedido recusado
        listTrocaPedidosRecus.put(0, "Pedido recusado");
        listTrocaPedidosRecus.put(1, "Nota fiscal disponível");
        listTrocaPedidosRecus.put(2, "Em transporte");
        listTrocaPedidosRecus.put(3, "Pedido entregue");

        btListTrocaPedidosRecus.put(0, "Gerar nota fiscal");



        // Controle da logística

        // Controle das logisticas não aplicáveis
        listTrocaLogistica.put(0, "Não aplicável");

        btListTrocaLogistica.put(0, "Não aplicável");


        // Controle da logística de troca
        listTrocaLogisticaTroca.put(0, "Aguardando nota fiscal");
        listTrocaLogisticaTroca.put(1, "Separando pedido");
        listTrocaLogisticaTroca.put(2, "Embalando pedido");
        listTrocaLogisticaTroca.put(3, "Enviando para transportadora");
        listTrocaLogisticaTroca.put(4, "Em transporte");
        listTrocaLogisticaTroca.put(5, "Pedido entregue");

        btListTrocaLogisticaTroca.put(0, "Separar pedido");
        btListTrocaLogisticaTroca.put(1, "Embalar pedido");
        btListTrocaLogisticaTroca.put(2, "Enviar para a transportadora");
        btListTrocaLogisticaTroca.put(3, "Enviar para transporte");
        btListTrocaLogisticaTroca.put(4, "Entregar pedido");


        // Controle da logóstica de recusados
        listTrocaLogisticaRecus.put(0, "Separando pedido");
        listTrocaLogisticaRecus.put(1, "Embalando pedido");
        listTrocaLogisticaRecus.put(2, "Enviando para transportadora");
        listTrocaLogisticaRecus.put(3, "Em transporte");
        listTrocaLogisticaRecus.put(4, "Pedido entregue");

        btListTrocaLogisticaRecus.put(0, "Embalar pedido");
        btListTrocaLogisticaRecus.put(1, "Enviar para a transportadora");
        btListTrocaLogisticaRecus.put(2, "Enviar para transporte");
        btListTrocaLogisticaRecus.put(3, "Entregar pedido");
    }

    public HashMap<Integer,String> getListTrocaPedidosIni() 
    {
        return this.listTrocaPedidosIni;
    }

    public HashMap<Integer,String> getListTrocaPedidosIniCli() 
    {
        return this.listTrocaPedidosIniCli;
    }

    public HashMap<Integer,String> getBtListTrocaPedidosIniCli() 
    {
        return this.btListTrocaPedidosIniCli;
    }

    public HashMap<Integer,String> getListTrocaPedidosTroca() 
    {
        return this.listTrocaPedidosTroca;
    }

    public HashMap<Integer,String> getBtListTrocaPedidosTroca() 
    {
        return this.btListTrocaPedidosTroca;
    }

    public HashMap<Integer,String> getListTrocaPedidosCupom() 
    {
        return this.listTrocaPedidosCupom;
    }

    public HashMap<Integer,String> getBtListTrocaPedidosCupom() 
    {
        return this.btListTrocaPedidosCupom;
    }

    public HashMap<Integer,String> getListTrocaPedidosReemb() 
    {
        return this.listTrocaPedidosReemb;
    }

    public HashMap<Integer,String> getBtListTrocaPedidosReemb() 
    {
        return this.btListTrocaPedidosReemb;
    }

    public HashMap<Integer,String> getListTrocaPedidosRecus() 
    {
        return this.listTrocaPedidosRecus;
    }

    public HashMap<Integer,String> getBtListTrocaPedidosRecus() 
    {
        return this.btListTrocaPedidosRecus;
    }

    public HashMap<Integer,String> getListTrocaLogistica() 
    {
        return this.listTrocaLogistica;
    }

    public HashMap<Integer,String> getListTrocaLogisticaTroca() 
    {
        return this.listTrocaLogisticaTroca;
    }

    public HashMap<Integer,String> getBtListTrocaLogisticaTroca() 
    {
        return this.btListTrocaLogisticaTroca;
    }

    public HashMap<Integer,String> getListTrocaLogisticaRecus() 
    {
        return this.listTrocaLogisticaRecus;
    }

    public HashMap<Integer,String> getBtListTrocaLogisticaRecus() 
    {
        return this.btListTrocaLogisticaRecus;
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
                return this.listTrocaPedidosIniCli;
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

    public HashMap<Integer,String> getBtListTrocaEscolha(int escolha) 
    {
        switch (escolha)
        {
            case 0:
                return this.btListTrocaPedidosIniCli;
            case 1:
                return this.btListTrocaPedidosTroca;
            case 2:
                return this.btListTrocaPedidosCupom;
            case 3:
                return this.btListTrocaPedidosReemb;
            case 4:
                return this.btListTrocaPedidosRecus;
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

    public HashMap<Integer,String> getBtListTrocaLog(int escolha)
    {
        switch (escolha)
        {
            case 0:
                return this.btListTrocaLogistica;
            case 1:
                return this.btListTrocaLogisticaTroca;
            case 2:
                return this.btListTrocaLogistica;
            case 3:
                return this.btListTrocaLogistica;
            case 4:
                return this.btListTrocaLogisticaRecus;
            default:
                return new HashMap<>();
        }
    }
}
