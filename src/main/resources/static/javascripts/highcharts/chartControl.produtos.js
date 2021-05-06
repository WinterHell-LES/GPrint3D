Highcharts.getJSON('http://localhost:8080/admin/graphFeed/produtos', function (data)
{    
    var data2 = data;
    var define2 = [];

    data2.forEach(element => {
        var define = [];
        var aux = 0;
        var produto = "";

        data2.forEach(e =>
        {
            var conteudo = {
                type: 'line',
                x: parseInt(e.date, 10),
                y: parseInt(e.produtos_vendidos, 10),
                produto_nome: e.produto_nome,
                marker: {
                    enabled: true,
                    radius: 4
                },
                tooltip: {
                    valueDecimals: 0
                }
            }

            if (aux == 0)
            {
                define.push(conteudo);

                produto = e.produto_nome;
            }
            else
            {
                if (define[0].produto_nome == e.produto_nome)
                {
                    define.push(conteudo);
                }
            }

            aux++;
        });

        define2.push({
            name: produto,
            data: define
        });

        data2 = data2.filter(e => { return produto != e.produto_nome });
    });
    
    define2 = define2.filter(e => { return e.name != "" });

    define2.sort(compare);

    Highcharts.setOptions({
        lang: {
            accessibility: {
                thousandsSep: null
            },
            months: ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
            shortMonths: ["Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"],
            weekdays: ["Domingo", "Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado"],
            shortWeekdays: ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"],
            decimalPoint: ",",
            downloadCSV: "Salvar em .csv",
            downloadJPEG: "Salvar em .jpg",
            downloadPDF: "Salvar em .pdf",
            downloadPNG: "Salvar em .png",
            downloadSVG: "Salvar em .svg",
            downloadXLS: "Salvar em .xls",
            hideData: "Esconder tabela de dados",
            loading: "Carregando...",
            noData: "Sem dados",
            printChart: "Imprimir gráfico",
            rangeSelectorZoom: "Periodo: ",
            thousandsSep: ".",
            viewData: "Visualizar tabela de dados",
            viewFullscreen: "Visualizar em tela cheia"
        }
    });

    Highcharts.stockChart('container', 
    {
        credits: {
            enabled: false
        },

        exporting: {
            filename: "Demonstrativo de vendas por produto",
            scale: 3
        },

        legend: {
            enabled: true,
            title: {
                text: 'Produtos'
            },
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },

        plotOptions: {
            series: {
                showInNavigator: true
            }
        },

        rangeSelector: 
        {
            selected: 4,
            buttons: [{
                type: 'month',
                count: 1,
                text: '1 mês',
                title: 'Visualizar 1 mês'
            }, {
                type: 'month',
                count: 3,
                text: '3 meses',
                title: 'Visualizar 3 meses'
            }, {
                type: 'month',
                count: 6,
                text: '6 meses',
                title: 'Visualizar 6 meses'
            }, {
                type: 'year',
                count: 1,
                text: '1 ano',
                title: 'Visualizar 1 ano'
            }, {
                type: 'all',
                text: 'Tudo',
                title: 'Visualizar tudo'
            }],
            buttonTheme: {
                width: 60
            },
        },

        series: define2,

        xAxis: [{
            minRange: 2592000000,
            title: {
                text: 'Periodo'
            }
        }],

        yAxis: [{
            title: {
                text: 'Produtos vendidos'
            }
        }]
    });
});

function compare(a, b)
{
    if (a.name < b.name)
    {
        return -1;
    }
    if (a.name > b.name)
    {
        return 1;
    }
    return 0;
}