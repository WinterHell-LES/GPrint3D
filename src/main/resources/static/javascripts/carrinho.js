//window.addEventListener('load', function () {
//    pagamentoTotal();
//  })

/*function pagamentoTotal()
{
    var cartoes = document.getElementsByClassName("dinheiro");
    var pagamentoTotal = document.getElementById("pagamentoTotal");
    var valorTotal = 0;

    for (var cartao of cartoes)
    {
        valorTotal += Number(cartao.value.replace("R$ ", "").replace(".", "").replace(",", "."));
    }

    pagamentoTotal.innerHTML = "R$ " + (Math.round((valorTotal + Number.EPSILON) * 100) / 100).toFixed(2).toString().replace(".", ",").replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

function atualizarQtd(selfElem)
{
    var prodId = document.querySelector('[name="id"]');

    const param = {};

    param[selfElem.name] = selfElem.value;
    param[prodId.name] = prodId.value;

    createPost("/carrinho/atualizarProduto", param);
}

function findFormParent(selfElem)
{
    var form = selfElem;
    try
    {
        do
        {
            form = form.parentElement;
        }
        while (form.tagName.toLowerCase() != "form");
    }
    catch(e)
    {
        return null;
    }

    return form;
}*/

function submit(selfElem)
{
    var form = findFormParent(selfElem);
    form.submit();
}

/*function createPost(path, params, method='POST') {

    // The rest of this code assumes you are not using a library.
    // It can be made less verbose if you use one.
    // Usage post("/product", {id:6});
    // retrn <form method="post" action="/cliente/carrinho/inlcuiCartao"><input type="hidden" name="id" value="6"></form>
    const form = document.createElement('form');
    form.method = method;
    form.action = path;

    var csrf = getCSRF();
    params = {...csrf, ...params}
  
    for (const key in params) {
      if (params.hasOwnProperty(key)) {
        const hiddenField = document.createElement('input');
        hiddenField.type = 'hidden';
        hiddenField.name = key;
        hiddenField.value = params[key];
  
        form.appendChild(hiddenField);
      }
    }
  
    document.body.appendChild(form);
    form.submit();
}

function getCSRF()
{
    const csrfElem = document.querySelector('[name="_csrf"]');
    const param = {};

    param[csrfElem.name] = csrfElem.value;

    return param;
}*/

function alert(url = "", selfElem = Object)
{   
    var confirmacao = confirm("Isto fará você reinicar o processo de compra, você realmente deseja ser redirecionado para outra página?");
    
    if (confirmacao == true && url != null)
    {
        location.replace(url + "#");
    }
    else if (confirmacao == true && selfElem != null)
    {
        selfElem.setAttribute("type", "submit");
    }
}
    
function modificarBotoes(tipo)
{
    if (tipo == "endereco")
    {
        var novoBotao = document.getElementById("novoEnderecoEntrega");
        var editarExcluirBotoes = document.getElementsByName("id");
    }
    
    if (tipo == "cartao")
    {
        var novoBotao = document.getElementById("novoCartao");
        var editarExcluirBotoes = document.getElementsByName("id");
    }

    var urlNovo = novoBotao.getAttribute("href").toString();

    novoBotao.setAttribute("onclick", "alert(\"" + urlNovo + "\")");
    novoBotao.setAttribute("class", "text-decoration-underline");
    novoBotao.outerHTML = novoBotao.outerHTML.replace("<a", "<span").replace("<\/a>", "<\/span>");

    for (let i = 0; i < editarExcluirBotoes.length; i++)
    {
        var id = editarExcluirBotoes[i].getAttribute("id");
        if (id != null)
        {
            if (id.includes("excluir") || id.includes("alterar"))
            {
                editarExcluirBotoes[i].setAttribute("onclick", "alert(null, this)");
                editarExcluirBotoes[i].setAttribute("type", "button");
            }
        }
    }
}

/*function updateThis(selfElement)
{
    selfElement.setAttribute("value", selfElement.value.replace("R$", "").replace(".", "").trim().replace(",", "."));

    pagamentoTotal();
}*/