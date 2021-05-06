Highcharts.getJSON('http://localhost:8080/admin/graphFeed/pedidos', function (data)
{
    var content = [];

    data.forEach(element => {
        content.push({
            x: parseInt(element.date, 10),
            y: parseFloat(element.sale),
            pedidos_recebidos: parseInt(element.pedidos_recebidos, 10),
            produtos_vendidos: parseInt(element.produtos_vendidos, 10)
        });
    });

    var tooltipFormat = '<table>Demonstrativo de vendas<tbody>' +
                        '<tr><td>Lucro: </td><td style="text-align: right"><b>{point.y}</b></td></tr></br>' +
                        '<tr><td>Pedidos recebidos: </td><td style="text-align: right"><b>{point.pedidos_recebidos}</b></td></tr></br>' +
                        '<tr><td>Produtos vendidos: </td><td style="text-align: right"><b>{point.produtos_vendidos}</b></td></tr>' +
                        '</tbody></table>';

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
            filename: "Demonstrativo de vendas por pedido",
            scale: 3
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

        series: [{
            type: 'spline',
            data: content,
            marker: {
                enabled: true,
                radius: 4
            },
            tooltip: {
                valuePrefix: "R$ ",
                valueDecimals: 2
            }
        }],

        tooltip: {
            shared: true,
            useHTML: true,
            pointFormat: tooltipFormat,
            valueDecimals: 2
        },

        xAxis: [{
            minRange: 2592000000,
            title: {
                text: 'Periodo'
            }
        }],

        yAxis: [{
            labels: {
                formatter: function () {
                    return 'R$ ' + this.value / 1000 + 'K';
                }
            },
            title: {
                text: 'Lucro'
            }
        }]
    });
});