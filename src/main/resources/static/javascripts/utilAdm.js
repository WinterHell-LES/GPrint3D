function dropKeeper(urlString, id, negate = null)
{
    var url = window.location.href.toString().toLowerCase();

    if (negate != null)
    {
        if (url.includes(urlString) && !url.includes(negate))
        {
            document.getElementById(id).classList.add("show");
        }
    }else
    {
        if (url.includes(urlString))
        {
            document.getElementById(id).classList.add("show");
        }
    }
}

function pageListener()
{
    dropKeeper("bandeiras", "bandeiras_cartao");
    dropKeeper("categorias", "categorias");
    dropKeeper("produtos", "produtos");
    dropKeeper("entradas", "entradas");
    dropKeeper("cupons", "cupons");
    dropKeeper("cuponspromocionais", "cupons_promocionais");
    dropKeeper("cuponstrocas", "cupons_trocas");
    dropKeeper("pedidos", "pedidos", "logisticapedidos");
    dropKeeper("logistica", "logistica");
    dropKeeper("clientes", "clientes");
    dropKeeper("vendas", "graficos");
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