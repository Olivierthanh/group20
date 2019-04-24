// Balance chart init
function getYearMonth() {
    let date = new Date();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    return year + '-' + (month < 10 ? "0" + month : month);
}

function getXAxisBalanceChart(data) {
    if (data.length === 0){
        return ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10',
            '11', '12', '13', '14', '15', '16', '17', '18', '19', '20',
            '21', '22', '23', '24', '25', '26', '27', '28', '29', '30'
        ];
    }
    else {
        date = new Date(data[0].date);
        no_day = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
        xAxis = [];
        for (var i = 1; i <= no_day; i++) {
            xAxis.push(i);
        }
        return xAxis;
    }
}

function getYAxisBalanceChart(data) {
    if (data.length === 0) {
        return [
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        ];
    }
    else {
        let yAxis = [];
        let transactionAmount = {};
        let no_day;
        let today = new Date();
        let date = new Date(data[0].date);
        if (today.getFullYear() === date.getFullYear() && today.getMonth() === date.getMonth()) {
            no_day = today.getDate();
        }
        else {
            no_day = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
        }

        for (let i = 1; i <= no_day; i++) {
            day = getDate(date.getFullYear(), date.getMonth() + 1, i);
            transactionAmount[day] = 0;
            yAxis.push(0);
        }

        $.each(data, (key, value) => {
            if (value.type === "income") {
                transactionAmount[value.date] += value.amount;
            }
            else {
                transactionAmount[value.date] -= value.amount;
            }
        });

        let balanceAmount = parseInt($("#balance-amount").text());
        console.log(balanceAmount);
        yAxis = getBalanceArray(Object.values(transactionAmount), balanceAmount);
        console.log(yAxis);
        return yAxis;
    }
}

function getDate(year, month, date) {
    return year + '-' + (month < 10 ? "0" + month: month) + '-' + (date < 10 ? "0" + date: date);
}

function getBalanceArray(amountList, presentBalance) {
    let sumAmount = amountList.reduce((sum, value) => sum + value, 0);
    let startBalance = presentBalance - sumAmount;
    let balanceArray = [];
    $.each(amountList, (key, value) => {
        startBalance += value;
        balanceArray[key] = startBalance;
    });
    return balanceArray;
}

function initBalanceChart(data) {
    let xAxis = getXAxisBalanceChart(data);
    let yAxis = getYAxisBalanceChart(data);

    var balanceChartOption = {
        color: ['#34C73B'],

        tooltip: {
            trigger: 'axis'
        },
        legend: {
            show: true,
            color: '#fff',
            data: ['Balance']
        },

        calculable: true,

        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: xAxis
            }
        ],
        yAxis: [
            {
                type: 'value',
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name: 'Balance',
                type: 'line',
                data: yAxis,
                markPoint: {
                    data: [
                        { type: 'max', name: 'Max' },
                        { type: 'min', name: 'Min' }
                    ]
                },
                markLine: {
                    data: [
                        { type: 'average', name: 'Average' }
                    ]
                }
            },
        ]
    };

    if (balanceChartOption && typeof balanceChartOption === "object") {
        balanceChart.setOption(balanceChartOption, true);
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
