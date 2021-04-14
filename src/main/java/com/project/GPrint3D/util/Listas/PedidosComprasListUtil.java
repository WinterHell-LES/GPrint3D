package com.project.GPrint3D.util.Listas;

import java.util.HashMap;

public class PedidosComprasListUtil 
{
    private HashMap<Integer, String> listCompraPedidos = new HashMap<>();
    private HashMap<Integer, String> listCompraLogistica = new HashMap<>();

    public PedidosComprasListUtil() 
    {
        listCompraPedidos.put(0, "Pedido recebido");
        listCompraPedidos.put(1, "Pagamento aprovado");
        listCompraPedidos.put(2, "Nota Fiscal disponível");
        listCompraPedidos.put(3, "Em transporte");
        listCompraPedidos.put(4, "Pedido entregue");

        listCompraLogistica.put(0, "Aguardando nota fiscal");
        listCompraLogistica.put(1, "Separando pedido");
        listCompraLogistica.put(2, "Embalando pedido");
        listCompraLogistica.put(3, "Enviando para transportadora");
        listCompraLogistica.put(4, "Em transporte");
        listCompraLogistica.put(5, "Pedido entregue");
    }

    public HashMap<Integer,String> getListCompraPedidos() 
    {
        return this.listCompraPedidos;
    }

    public HashMap<Integer,String> getListCompraLogistica() 
    {
        return this.listCompraLogistica;
    }
}
