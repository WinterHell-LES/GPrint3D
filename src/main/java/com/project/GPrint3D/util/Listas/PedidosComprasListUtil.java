package com.project.GPrint3D.util.Listas;

import java.util.HashMap;

public class PedidosComprasListUtil
{
    private HashMap<Integer, String> listCompraPedidos = new HashMap<>();
    private HashMap<Integer, String> listCompraPedidosCli = new HashMap<>();
    private HashMap<Integer, String> listCompraLogistica = new HashMap<>();
    private HashMap<Integer, String> listCompraLogisticaCli = new HashMap<>();

    private HashMap<Integer, String> btListCompraPedidos = new HashMap<>();
    private HashMap<Integer, String> btListCompraLogistica = new HashMap<>();

    public PedidosComprasListUtil ()
    {
        listCompraPedidos.put(0, "Pedido recebido");
        listCompraPedidos.put(1, "Pagamento aprovado");
        listCompraPedidos.put(2, "Nota Fiscal disponível");
        listCompraPedidos.put(3, "Em transporte");
        listCompraPedidos.put(4, "Pedido entregue");
        listCompraPedidos.put(10, "Pagamento reprovado");
        listCompraPedidos.put(11, "Pedido cancelado");

        listCompraPedidosCli.put(0, "Pedido recebido");
        listCompraPedidosCli.put(1, "Pagamento aprovado");
        listCompraPedidosCli.put(2, "Nota Fiscal disponível");
        listCompraPedidosCli.put(3, "Em transporte");
        listCompraPedidosCli.put(4, "Pedido entregue");

        btListCompraPedidos.put(0, "Aprovar pagamento");
        btListCompraPedidos.put(1, "Gerar nota fiscal");

        listCompraLogistica.put(0, "Aguardando nota fiscal");
        listCompraLogistica.put(1, "Separando pedido");
        listCompraLogistica.put(2, "Embalando pedido");
        listCompraLogistica.put(3, "Enviando para transportadora");
        listCompraLogistica.put(4, "Em transporte");
        listCompraLogistica.put(5, "Pedido entregue");
        listCompraLogistica.put(11, "Pedido cancelado");

        listCompraLogisticaCli.put(0, "Aguardando nota fiscal");
        listCompraLogisticaCli.put(1, "Separando pedido");
        listCompraLogisticaCli.put(2, "Embalando pedido");
        listCompraLogisticaCli.put(3, "Enviando para transportadora");
        listCompraLogisticaCli.put(4, "Em transporte");
        listCompraLogisticaCli.put(5, "Pedido entregue");

        btListCompraLogistica.put(0, "Separar pedido");
        btListCompraLogistica.put(1, "Embalar pedido");
        btListCompraLogistica.put(2, "Enviar para a transportadora");
        btListCompraLogistica.put(3, "Enviar para transporte");
        btListCompraLogistica.put(4, "Entregar pedido");
    }

    public HashMap<Integer, String> getListCompraPedidos ()
    {
        return this.listCompraPedidos;
    }

    public HashMap<Integer, String> getListCompraPedidosCli ()
    {
        return this.listCompraPedidosCli;
    }

    public HashMap<Integer, String> getListCompraLogistica ()
    {
        return this.listCompraLogistica;
    }

    public HashMap<Integer, String> getListCompraLogisticaCli ()
    {
        return this.listCompraLogisticaCli;
    }

    public HashMap<Integer, String> getBtListCompraPedidos ()
    {
        return this.btListCompraPedidos;
    }

    public HashMap<Integer, String> getBtListCompraLogistica ()
    {
        return this.btListCompraLogistica;
    }
}
