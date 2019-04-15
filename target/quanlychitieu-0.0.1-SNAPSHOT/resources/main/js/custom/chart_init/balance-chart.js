// Balance chart init
var chartOneDom = document.getElementById("b-line");
var chartOne = echarts.init(chartOneDom);

var chartOneOption = {
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
            data: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10',
                '11', '12', '13', '14', '15', '16', '17', '18', '19', '20',
                '21', '22', '23', '24', '25', '26', '27', '28', '29', '30'
            ]
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
            data: [100000, -100000, 100000, -500000, 1000000, 400000,
                100000, -100000, 100000, -500000, 1000000, 400000,
                100000, -100000, 100000, -500000, 1000000, 400000,
                100000, -100000, 100000, -500000, 1000000, 400000,
                100000, -100000, 100000, -500000, 1000000, 400000,
            ],
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

if (chartOneOption && typeof chartOneOption === "object") {
    chartOne.setOption(chartOneOption, true);
}