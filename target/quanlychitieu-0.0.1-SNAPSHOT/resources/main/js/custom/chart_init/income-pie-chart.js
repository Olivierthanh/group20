// Init Income Chart
function initIncomeChart(incomeTransactions) {
    let {legendData, seriesData} = getSeriesData(incomeTransactions);

    var app = {};
    let option = null;
    option = {
        color: ['#0000FF', '#00A2FF', '#F44336', '#34C73B', '#DCDCDC', '#C0C0C0', '#808080', '#000000',
            '#FF0000', '#800000', '#FFFF00', '#808000', '#00FF00', '#008000', '#00FFFF', '#008080',
            '#000080', '#FF00FF', '#800080'],
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
        incomeChart.setOption(option, false);
    }
}

function getSeriesData(incomeTransactions) {
    let seriesData = incomeTransactions.reduce((data, transaction) => {
        if (data[transaction.category.categoryName]) {
            data[transaction.category.categoryName].value += transaction.amount
        }
        else {
            data[transaction.category.categoryName] = {
                value: transaction.amount,
                name: transaction.category.categoryName
            }
        }
        return data;
    }, {});

    return {
        legendData: Object.keys(seriesData),
        seriesData: Object.values(seriesData)
    }

}

// // Data sample
// amount: 1000000
// category:
//     categoryId: 1
// categoryName: "Award"
// type: "income"
// __proto__: Object
// date: "2019-04-23"
// note: "something to note"
// transactionId: 1
// type: "income"