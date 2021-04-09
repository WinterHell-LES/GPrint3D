async function validarCEP()
{
    var cep = document.getElementById("cep").value.replace("-", "");

    link = "http://localhost:8080/validarCEP/" + cep;

    var config = { 
        method: 'GET'
    };

    const response = await fetch(link, config);

    if (!response.ok)
    {
        throw new Error(response.status);
    }

    const json = await response.json();

    if (json.erro == "false")
    {
        return true;
    }
    
    return false;
}

async function buscarFrete()
{
    var cep = document.getElementById("cep").value.replace("-", "");
    
    var local = checkUrl()

    switch (local)
    {
        case "produto":
            link = "http://localhost:8080/calcularFrete/" + id + "/" + cep;
            break;

        case "carrinhoLogado":
        case "carrinhoDeslogado":
            link = "http://localhost:8080/carrinho/calcularFreteLista/" + cep;
            break;

        default:
            return null;
    }

    var config = { 
        method: 'GET'
    };

    const response = await fetch(link, config);

    if (!response.ok)
    {
        throw new Error(response.status);
    }

    const json = await response.json();

    return json;
}

function calcularFrete()
{
    var load = document.getElementById("loadFrete");
    var disp = document.getElementById("dispFrete");

    load.classList.remove("d-none");

    if (!disp.classList.contains("d-none"))
    {
        disp.classList.add("d-none");
    }

    var cep = document.getElementById("cep").value.replace("-", "");

    if (!cep)
    {
        alert("Insira o CEP para poder calcular o preço e prazo do frete");
    }
    else 
    {
        validarCEP().then(response =>
        {
            if (response)
            {
                buscarFrete().then(json => 
                {
                    listarFretes(json);
                    //console.log(json);

                    load.classList.add("d-none");
                    disp.classList.remove("d-none");
                }).catch(json =>
                {
                    load.classList.add("d-none");
                });
            }
        });
    }
}

function listarFretes(json)
{
    var lista =  document.getElementById("dispFrete");
    var codigoHTML = "";
    var contador = 0;

    codigoHTML += "<div class=\"container\">";

    json.forEach(aux =>
    {
        if ((aux.Valor != "0,00") && (aux.PrazoEntrega != "0"))
        {
            switch (checkUrl())
            {
                case "produto":
                case "carrinhoDeslogado":
                    codigoHTML +=   "<div class=\"row my-1\">" +
                                        "<div class=\"col-lg\">" + 
                                            aux.Nome + 
                                        "</div>" +
                                        "<div class=\"col-sm\">" +
                                            "R$ " + aux.Valor.replace(/\B(?=(\d{3})+(?!\d))/g, ".") +
                                        "</div>" +
                                    "</div>";
                    break;

                case "carrinhoLogado":
                    codigoHTML +=   "<div class=\"form-check\">" +
                                        "<input class=\"form-check-input\" type=\"radio\" name=\"frete[" + contador + "]\" id=" + aux.Nome.replace(" ", "") + " value=" + aux.Valor.replace(",", ".") + ">" +
                                        "<label class=\"form-check-label row\" for=" + aux.Nome.replace(" ", "") + ">" +
                                            "<div class=\"col-lg\">" +
                                                aux.Nome +
                                            "</div>" +
                                            "<div class=\"col-sm\">" +
                                                "R$ " + aux.Valor.replace(/\B(?=(\d{3})+(?!\d))/g, ".") +
                                            "</div>" +
                                        "</label>" +
                                    "</div>";
                    break;

                default:
                    break;
            }
            
            contador++;
        }
    });

    if (codigoHTML == "")
    {
        codigoHTML += "<div>Erro ao calcular o frete</div>"
    }

    codigoHTML += "</div>";
    
    lista.innerHTML = codigoHTML;
}

function checkUrl()
{
    var url = window.location.href.toString().toLowerCase();
    
    if (url.includes("/c/") && url.includes("/p/"))
    {
        return "produto";
    }
    else if (!url.includes("/cliente/") && url.includes("/carrinho/"))
    {
        return "carrinhoDeslogado";
    }
    else if (url.includes("/cliente/") && url.includes("/carrinho/"))
    {
        return "carrinhoLogado";
    }
}