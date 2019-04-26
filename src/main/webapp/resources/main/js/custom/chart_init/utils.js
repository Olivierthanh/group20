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

function initWalletDataTable() {
    // Init wallet table
    $('#wallet-table tfoot th').each(function () {
        var title = $(this).text();
        $(this).html('<input type="text"  placeholder="' + title + '" />');
    });

// DataTable - individual column searching by text
    var table = $('#wallet-table').DataTable({
        bAutoWidth: false,
        aoColumns: [
            { "width": "15%" },
            { "width": "15%" },
            { "width": "15%" },
            { "width": "15%" },
            { "width": "15%" },
        ]
    });

// Apply the search
    table.columns().every(function () {
        var that = this;

        $('input', this.footer()).on('keyup change', function () {
            if (that.search() !== this.value) {
                that
                    .search(this.value)
                    .draw();
            }
        });
    });
}