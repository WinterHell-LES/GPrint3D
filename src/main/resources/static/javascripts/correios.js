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
    link = "http://localhost:8080/calcularFrete/" + id + "/" + cep;

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
    lista.innerHTML = "";

    lista.innerHTML += "<div class=\"container\">";

    json.forEach(aux =>
    {
        if ((aux.Valor != "0,00") && (aux.PrazoEntrega != "0"))
        {
            lista.innerHTML += "<div class=\"row my-1\"><div class=\"col-lg\">" + aux.Nome + "</div><div class=\"col-sm\">R$ " + aux.Valor + "</div></div>";
        }
    });

    if (lista.innerHTML == "")
    {
        lista.innerHTML += "<div>Erro ao calcular o frete</div>"
    }

    lista.innerHTML += "</div>";
}