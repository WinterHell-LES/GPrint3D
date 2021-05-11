function dropKeeper(urlString, id, negate = [], button)
{
    var url = window.location.href.toString().toLowerCase();

    if (negate != null && negate.length > 0)
    {
        var aux = true;

        negate.forEach(e => {
            if (url.includes(e.toLowerCase()))
            {
                aux = false;
            }
        });

        if (url.includes(urlString) && aux)
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
    dropKeeper("categorias", "categorias", ["cadastrocategorias", "grafico"], "bt_categorias");
    dropKeeper("produtos", "produtos", ["grafico", "infocompras", "infotrocas"], "bt_produtos");
    dropKeeper("cadastrocategorias", ["produtos"], null, "bt_produtos");
    dropKeeper("cadastrofotos", ["produtos"], null, "bt_produtos");
    dropKeeper("entradas", "estoque", null, "bt_estoque");
    dropKeeper("saidas", "estoque", null, "bt_estoque");
    dropKeeper("entradas", "entradas", null, "bt_entradas");
    dropKeeper("saidas", "saidas", null, "bt_saidas");
    dropKeeper("cupons", "cupons", null, "bt_cupons");
    dropKeeper("cuponspromocionais", "cupons_promocionais", null, "bt_cupons_promocionais");
    dropKeeper("cuponstrocas", "cupons_trocas", null, "bt_cupons_trocas");
    dropKeeper("pedidos", "pedidos", ["logisticapedidos", "grafico"], "bt_pedidos");
    dropKeeper("infocompras", "pedidos", ["logisticapedidos", "grafico"], "bt_pedidos");
    dropKeeper("infotrocas", "pedidos", ["logisticapedidos", "grafico"], "bt_pedidos");
    dropKeeper("logistica", "logistica", null, "bt_logistica");
    dropKeeper("clientes", "clientes", null, "bt_clientes");
    dropKeeper("grafico", "graficos", null, "bt_graficos");
    dropKeeper("variaveis", "variaveis", null, "bt_variaveis");
}

document.addEventListener('DOMContentLoaded', x =>
{
    pageListener()
});