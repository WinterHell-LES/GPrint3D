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

    switch (checkUrl())
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
    var cep = "";
    
    if(document.getElementById("cep").value != "")
    {
        cep = document.getElementById("cep").value.replace("-", "");
        setCookie("cliCEP", cep, 30)
    }
    else
    {
        return;
    }

    var load = document.getElementById("loadFrete");
    var disp = document.getElementById("dispFrete");

    load.classList.remove("d-none");

    if (!disp.classList.contains("d-none"))
    {
        disp.classList.add("d-none");
    }

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
                }).catch(json =>
                {
                    erroFrete("Error 500 - Erro ao calcular o frete");
                }).then(json =>
                {
                    {
                        load.classList.add("d-none");
                        disp.classList.remove("d-none");
                    }
                });
            }
            else
            {
                erroFrete("CEP inválido");
            }
        }).catch(response =>
        {
            erroFrete("Error 500 - Erro ao validar o CEP");
        });
    }
}

function listarFretes(json)
{
    var lista =  document.getElementById("dispFrete");
    
    lista.classList.remove("text-danger");
    var codigoHTML = "";
    var contador = 0;

    codigoHTML += "<div>";

    json.forEach(aux =>
    {
        if ((aux.Valor != "0,00") && (aux.PrazoEntrega != "0"))
        {
            switch (checkUrl())
            {
                case "produto":
                    codigoHTML += "<div class=\"row\">";
                    codigoHTML += "<div class=\"col-5 pe-0\"><small>" + aux.Nome + "</small></div>";
                    codigoHTML += "<div class=\"col-sm-4 px-0\"><small>R$ " + aux.Valor + "</small></div>";
                    codigoHTML += "<div class=\"col-sm-3 text-center ps-0\"><small>" + aux.PrazoEntrega + " dia(s)</small></div>";
                    codigoHTML += "</div>";
                    break;

                    case "carrinhoDeslogado":
                    codigoHTML +=   "<div class=\"row my-1\">" +
                                        "<div class=\"col-lg\">" + 
                                            aux.Nome + 
                                        "</div>" +
                                        "<div class=\"col-sm\">" +
                                            "R$ " + aux.Valor.replace(/\B(?=(\d{3})+(?!\d))/g, ".") +
                                        "</div>" +
                                        "<div class=\"col-sm\">" +
                                            aux.PrazoEntrega + " dia(s)" +
                                        "</div>" +
                                    "</div>";
                    break;

                case "carrinhoLogado":
                    codigoHTML +=   "<div class=\"form-check\">" +
                                        "<input class=\"form-check-input\" type=\"radio\" name=\"frete\" id=" + aux.Nome.replace(" ","_") + " value=" + JSON.stringify({'empresa':'correios', 'modalidade': aux.Nome.replace(" ","_"), 'prazo': aux.PrazoEntrega, 'valor': aux.Valor.replace(",", ".")}) + " required>" +
                                        "<label class=\"form-check-label row\" for=" + aux.Nome.replace(" ","_") + ">" +
                                            "<div class=\"col-lg\">" +
                                                aux.Nome +
                                            "</div>" +
                                            "<div class=\"col-sm\">" +
                                                "R$ " + aux.Valor.replace(/\B(?=(\d{3})+(?!\d))/g, ".") +
                                            "</div>" +
                                            "<div class=\"col-sm\">" +
                                                aux.PrazoEntrega + " dia(s)" +
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

    codigoHTML += "</div>";

    lista.innerHTML = "";

    if (codigoHTML == "")
    {
        erroFrete("Nenhum valor encontrado")
    }
    else
    {
        lista.innerHTML = codigoHTML;
    }
}

function erroFrete (erro = "")
{
    var load = document.getElementById("loadFrete");
    var disp = document.getElementById("dispFrete");
    
    disp.innerHTML = "<div>Erro ao calcular o frete,</div><div>" + erro + "</div>";

    load.classList.add("d-none");
    disp.classList.remove("d-none");
    disp.classList.add("text-center");
    disp.classList.add("text-danger");
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

function setCookie(cookieNome, cookieValor, expiraDias)
{
    var maxAge = "max-age=" + (expiraDias * 24 * 60 * 60);

    document.cookie = cookieNome + "=" + cookieValor + ";" + maxAge + ";path=/;Secure" ;
}

function getCookie(cookieNome)
{
    var nome = cookieNome + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var partesCookie = decodedCookie.split(';');

    for(var i = 0; i <partesCookie.length; i++)
    {
      var char = partesCookie[i];
      
      while (char.charAt(0) == ' ')
      {
        char = char.substring(1);
      }

      if (char.indexOf(nome) == 0)
      {
        return char.substring(nome.length, char.length);
      }
    }

    return null;
  }