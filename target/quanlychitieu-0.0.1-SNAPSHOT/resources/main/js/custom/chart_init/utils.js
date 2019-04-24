function getSeriesData(transactions) {
    let seriesData = transactions.reduce((data, transaction) => {
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

function updateChart(transactionDate) {
    let presentYearMonthDisplay = $("#month").val();
    if (transactionDate.substr(0, 7) === presentYearMonthDisplay) {
        $("#month").change();
    }
}