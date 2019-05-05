$(document).ready(() => {
    $("#month").on("change", event => {
       let yearMonth = $(event.target).val();
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
                initBalanceChart(data);
                initIncomeChart(getTransactionsByType(data, "income"));
                initExpenseChart(getTransactionsByType(data, "expense"));
            },
            error: error => {
                swal('Lỗi !!', 'Lỗi lấy dữ liệu từ server!!', 'error');
            }
        });
    });
});