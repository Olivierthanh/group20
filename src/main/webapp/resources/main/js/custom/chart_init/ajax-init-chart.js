// Ajax request list transaction by month

//Get chart instance
// balance chart
let balanceDom = document.getElementById("b-line");
let balanceChart = echarts.init(balanceDom);

// expense chart
let expenseDom = document.getElementById("expense-Pie");
let expenseChart = echarts.init(expenseDom);

let incomeDome = document.getElementById("income-Pie");
let incomeChart = echarts.init(incomeDome);



url = window.location.href.split(/[#?]/)[0] + '/getListTransactionByMonth';
yearMonth = getYearMonth();

$(document).ready(() => {
    $("#month").attr("max", yearMonth);
    $("#month").attr("value", yearMonth);
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        data: {
            yearMonth: yearMonth
        },
        dataType: "json",
        timeout: 10000,
        success: data => {
            console.log(data);
            fillTransactionView(data);
            initBalanceChart(data);
            initIncomeChart(getTransactionsByType(data, "income"));
            initExpenseChart(getTransactionsByType(data, "expense"));
            window.onresize = function() {
                balanceChart.resize();
                incomeChart.resize();
                expenseChart.resize();
            };
        },
        error: error => {
            swal('Error !!', 'Failed to fetch data from server!!', 'error');
        }
    });
});

function getTransactionsByType(data, type) {
    return data.reduce((incomeTransactions, value) => {
        if (value.type === type) {
            incomeTransactions.push(value)
        }
        return incomeTransactions;
    }, []);
}

function fillTransactionView(data) {

}
