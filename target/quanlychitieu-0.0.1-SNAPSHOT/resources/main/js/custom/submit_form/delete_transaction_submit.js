// Delete transaction submit
$("body").on("submit", ".delete-transaction-form", event => {
    event.preventDefault();

    swal(
        {
            title: `Are you sure to delete this transaction ?`,
            text: "You will not be able to recover this user !!",
            type: "warning",
            showCancelButton: !0,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete  !!",
            closeOnConfirm: !1
        }
    ).then(isConfirmed => {
        if (isConfirmed.value) {
            let action = $(event.target).attr("action");
            let data = new FormData(event.target);
            console.log(data);
            let transactionId = data.get("transaction-id");
            let walletId = data.get("wallet-id");
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: action,
                data: {
                    transactionId: transactionId,
                    walletId: walletId
                },
                dataType: "json",
                timeout: 10000,
                success: data => {
                    swal(`${data['title']} !!`, `${data['message']} !!`, data['type']).then((value) => {
                        if (data['type'] === "success") {
                            let amount = parseInt($($(event.target).find(".transaction-amount")[0]).text().replace('/\s+/g', ''));
                            let transactionDate = $(event.target).closest(".card").attr("transaction-date");
                            if ($(event.target).closest(".card").find(".card-body").length === 1) {
                                cards = $("#view-transaction").find(".card");
                                $(event.target).closest(".card").remove();
                                if (cardActive > 0) {
                                    cardActive = cardActive - 1;
                                    $(cards[cardActive]).removeClass("d-none");
                                }
                                else if (cardActive + 1 < cards.length) {
                                    $(cards[cardActive + 1]).removeClass("d-none");
                                }

                                updateModelAfterDeleteTransaction(event, transactionDate, amount, false);
                            }
                            else {
                                $(event.target).closest(".card-body").remove();
                                updateModelAfterDeleteTransaction(event, transactionDate, amount, true);
                            }
                        }
                    });
                },
                error: error => {
                    swal('Try again !!', 'There is something wrong happened !!', 'error');
                }
            });
        }
    });
});


function updateModelAfterDeleteTransaction(event, transactionDate, amount, isTransactionDaySumUpdate) {
    if (isTransactionDaySumUpdate) {
        let sumNode = $(event.target).closest('.card').find('.sum')[0];
        let oldSum = parseInt($(sumNode).text());
        let newSum = oldSum - amount;
        $(sumNode).text(newSum);
    }

    let balanceAmount = $("#balance-amount")[0];
    $(balanceAmount).text(parseInt($(balanceAmount).text()) - amount);

    if (amount > 0) {
        let inflow = $("#inflow")[0];
        $(inflow).text(parseInt($(inflow).text()) - amount);
    }
    else {
        let outflow = $("#outflow")[0];
        $(outflow).text(parseInt($(outflow).text()) - amount);
    }

    updateChart(transactionDate);
}