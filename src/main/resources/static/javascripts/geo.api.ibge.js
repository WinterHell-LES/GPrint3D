async function buscarPais()
{
    var link = "https://servicodados.ibge.gov.br/api/v1/localidades/paises?orderBy=nome";

    var config = {
        method: 'GET',
    };

    const response = await fetch(link, config);

    if (!response.ok)
    {
        throw new Error(response.status);
    }

    const json = await response.json();

    return json
}

async function buscarEstados()
{
    var link = "https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome";

    var config = {
        method: 'GET',
    };

    const response = await fetch(link, config);

    if (!response.ok)
    {
        throw new Error(response.status);
    }

    const json = await response.json();

    return json
}

async function buscarCidade(estadoId)
{
    var link = `https://servicodados.ibge.gov.br/api/v1/localidades/estados/${estadoId}/municipios`;

    var config = {
        method: 'GET',
    };

    const response = await fetch(link, config);

    if (!response.ok)
    {
        throw new Error(response.status);
    }

    const json = await response.json();

    return json
}

(function listarAll()
{
    var selectPais = document.getElementById("selectPais");
    var selectEstado = document.getElementById("selectEstado");
    var selectCidade = document.getElementById("selectCidade");

    var inputEstado = document.getElementById("inputEstado");
    var inputCidade = document.getElementById("inputCidade");

    var divSelectEstado = document.getElementById("divSelectEstado");
    var divSelectCidade = document.getElementById("divSelectCidade");

    var divInputEstado = document.getElementById("divInputEstado");
    var divInputCidade = document.getElementById("divInputCidade");

    listarPais();

    console.log(selectPais.value.toLowerCase());

    if (pais.length > 0)
    {
        if (pais.toLowerCase() == 'brasil')
        {
            divInputEstado.classList.add("d-none");
            divSelectEstado.classList.remove("d-none");
            selectEstado.disabled = false;

            listarEstados();

            if (cidade.length > 0)
            {
                listarCidades();
            }
        }
        else
        {
            inputEstado.disabled = false;
        }
    }
})();

function listarPais()
{
    var selectPais = document.getElementById("selectPais");

    selectPais.innerHTML = "";

    buscarPais().then(respose =>
    {
        respose.forEach(element =>
        {
            selectPais.innerHTML += `<option value="${ element.nome }" ${ element.nome.toLowerCase() == pais.toLowerCase() ? 'selected' : '' }>${ element.nome }</option>`;
        });
    });
}

function listarEstados()
{
    var selectPais = document.getElementById("selectPais");
    var selectEstado = document.getElementById("selectEstado");
    var selectCidade = document.getElementById("selectCidade");

    selectEstado.innerHTML = "";
    selectCidade.innerHTML = "";

    if (selectPais.value.toLowerCase() == "brasil")
    {
        buscarEstados().then(respose =>
        {
            respose.forEach(element =>
            {
                selectEstado.innerHTML += `<option value="${ element.sigla }" ${ element.sigla == estado ? 'selected' : '' }>${ element.nome }</option>`;
            });
        });
    }
}

function listarCidades()
{
    var selectEstado = document.getElementById("selectEstado");
    var selectCidade = document.getElementById("selectCidade");

    selectCidade.innerHTML = "";

    buscarEstados().then(respose1 =>
    {
        respose1.forEach(element1 =>
        {
            if (element1.sigla == selectEstado.value)
            {
                buscarCidade(element1.id).then(respose2 =>
                {
                    respose2.forEach(element2 =>
                    {
                        selectCidade.innerHTML += `<option value="${ element2.nome }" ${ element2.nome.toLowerCase() == cidade.toLowerCase() ? 'selected' : '' }>${ element2.nome }</option>`;
                    });
                });
            }
        });
    });
}