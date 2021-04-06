function dropKeeper(urlString, id, negate = null, button)
{
    var url = window.location.href.toString().toLowerCase();

    if (negate != null)
    {
        if (url.includes(urlString) && !url.includes(negate))
        {
            document.getElementById(id).classList.add("show");
            document.getElementById(button).classList.remove("collapsed");
        }
    }
    else
    {
        if (url.includes(urlString))
        {
            document.getElementById(id).classList.add("show");
            document.getElementById(button).classList.remove("collapsed");
        }
    }
}

function pageListener()
{
    dropKeeper("bandeiras", "bandeiras_cartao", null, "bt_bandeiras_cartao");
    dropKeeper("precificacoes", "precificacoes", null, "bt_precificacoes");
    dropKeeper("categorias", "categorias", null, "bt_categorias");
    dropKeeper("produtos", "produtos", null, "bt_produtos");
    dropKeeper("entradas", "entradas", null, "bt_entradas");
    dropKeeper("cupons", "cupons", null, "bt_cupons");
    dropKeeper("cuponspromocionais", "cupons_promocionais", null, "bt_cupons_promocionais");
    dropKeeper("cuponstrocas", "cupons_trocas", null, "bt_cupons_trocas");
    dropKeeper("pedidos", "pedidos", "logisticapedidos", "bt_pedidos");
    dropKeeper("logistica", "logistica", null, "bt_logistica");
    dropKeeper("clientes", "clientes", null, "bt_clientes");
    dropKeeper("vendas", "graficos", null, "bt_graficos");
}

document.addEventListener('DOMContentLoaded', x =>
{
    pageListener()
});

/*var sidebarContent;

document.addEventListener('DOMContentLoaded', x =>
{
    if (typeof(sidebarContent) == "undefined"){
        sidebarContent = document.getElementById("sidebarMenu").innerHTML
    };

    document.getElementById("sidebarMenu").innerHTML = sidebarContent;
});

document.addEventListener('beforeunload', x =>
{
    sidebarContent = document.getElementById("sidebarMenu").innerHTML;
});*/