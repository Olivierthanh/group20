

// Init Income Chart
function initExpenseChart(expenseTransactions) {
    let {legendData, seriesData} = getSeriesData(expenseTransactions);

    var app = {};
    let option = null;
    option = {
        color: ['#800080', '#FF00FF', '#000080', '#008080', '#00FFFF', '#008000', '#00FF00', '#808000', '#FFFF00',
            '#800000', '#FF0000', '#000000', '#808080', '#C0C0C0', '#DCDCDC', '#34C73B',
            '#F44336', '#00A2FF', '#0000FF'],
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'horizontal',
            // x: 'left',
            y: 'top',
            data: legendData
        },
        calculable: true,
        series: [
            {
                name: 'Loại',
                type: 'pie',
                radius: '55%',
                center: ['50%', '70%'],
                data: seriesData
            }
        ]
    };

    if (option && typeof option === "object") {
        expenseChart.setOption(option, false);
    }
}

