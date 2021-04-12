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
    dropKeeper("cupons", "cupons", null, "bt_cupons");
    dropKeeper("pedidos", "pedidos", null, "bt_pedidos");
    dropKeeper("cadastro", "dados_pessoais", null, "bt_dados_pessoais");
    dropKeeper("enderecos", "dados_pessoais", null, "bt_dados_pessoais");
    dropKeeper("cartoes", "dados_pessoais", null, "bt_dados_pessoais");
    dropKeeper("senha", "dados_pessoais", null, "bt_dados_pessoais");
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