// Add transaction form submit
$(document).ready(() => {
    $("#add-transaction-form").on("submit", event => {
        event.preventDefault();
        if ($(event.target).valid()) {
            swal({
                title: "Accept to add transaction !!",
                text: "Submit Ok to add, Cancel to cancel !!",
                type: "info",
                showCancelButton: true,
                confirmButtonText: 'OK',
                cancelButtonText: 'Cancel',
            }).then((isConfirmed) => {
                let formData = new FormData(event.target);
                let action = $(event.target).attr("action");
                let dataSubmit = {};
                formData.forEach((value, key) => {
                    dataSubmit[key] = value;
                });
                // console.log(dataSubmit);
                if (isConfirmed.value) {
                    $.ajax({
                        type: "GET",
                        contentType: "application/json",
                        url: action,
                        data: dataSubmit,
                        dataType: "json",
                        timeout: 10000,
                        success: data => {
                            swal(`${data['title']} !!`, `${data['message']} !!`, data['type']).then((value) => {
                                if (data['type'] === 'success') {
                                    let deleteTransactionUrl = "/" + action.split("/")[1] + '/deleteTransaction';
                                    updateModel(dataSubmit, data, deleteTransactionUrl);
                                    $(event.target)[0].reset();
                                }
                            });
                        },
                        error: error => {
                            swal('Try again !!', 'There is something wrong happened !!', 'error');
                        }
                    });
                }
            })
        }
    })
});


function getTransactionNode(dataSubmit, dataReturn, username, action) {
    let node = `
    <div class="card-body py-0">
        <hr>
        <div class="row">
            <div class="col-2">
                <img class="img-fluid" src="resources/main/icons/category_icon/cat_${dataSubmit['category-id']}.png"
                     alt='des' />
            </div>
            <div class="col-6">
                <div class="row">
                    <p class="w-100">
                        <span class="d-block">${dataSubmit['category-name']}</span> <span>${username}</span>
                        - <span>${dataSubmit['date-transaction']}</span>
                    </p>
                    <p class="w-100">${dataSubmit['note']}</p>
                </div>
            </div>
            <div class="col-4">
                <div class="row float-right w-75">
                    <form action="${action}" method="get" class="delete-transaction-form">
                        <p class="w-100 transaction-amount">
                            ${(dataSubmit['transaction-type'] === 'income' ? '+ ': '- ') + dataSubmit['amount']}
                        </p>
                        <input type="text" name="transaction-id" value="${dataReturn['attachedData']}" hidden />
                        <input type="text" name="wallet-id" value="${dataSubmit['wallet-id']}" hidden />
                        <button type="submit" class="btn btn-circle btn-danger"
                                data-toggle="tooltip" data-placement="right" title
                                data-original-title="Delete transaction">
                            <i class="fa fa-trash"></i>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    `;
    return $.parseHTML(node)[1];
}

function createTransactionDayNode(dataSubmit, dataReturn, deleteTransactionUrl, isVisible) {
    let node = `
    <div class="card w-100 p-4 ${!isVisible ? 'd-none': ''}" transaction-date="${dataSubmit['date-transaction']}">
        <div class="card-header">
            <h3 class="card-title m-t-10">
                ${dataSubmit['date-transaction']}
            </h3>
            <div class="float-right">
                <p class="sum">${dataSubmit['transaction-type'] === 'income'? '+' + dataSubmit['amount']: '-' + dataSubmit['amount']}</p>
            </div>
        </div>
        <!-- One transaction -->

        
        <!-- End one transaction-->
    </div>
    `;
    let username = $("#username").text();
    let transactionDay = $.parseHTML(node)[1];
    let transactionNode = getTransactionNode(dataSubmit, dataReturn, username, deleteTransactionUrl);
    console.log(transactionDay);
    console.log(transactionNode);
    transactionDay.append(transactionNode);
    return transactionDay;
}

function updateModel(dataSubmit, dataReturn, deleteTransactionUrl) {
    let transactionDay = $(`#view-transaction .card[transaction-date='${dataSubmit['date-transaction']}']`);
    if (transactionDay.length > 0) {
        addTransactionToModel(transactionDay[0], dataSubmit, dataReturn, deleteTransactionUrl);
        updateSum(transactionDay, parseInt(dataSubmit['amount']), dataSubmit['transaction-type']);
    }
    else {
        addTransactionDayNode(dataSubmit, dataReturn, deleteTransactionUrl);
    }
    updateOverview(parseInt(dataSubmit['amount']), dataSubmit['transaction-type']);
    updateChart(dataSubmit['date-transaction']);

}

function addTransactionDayNode(dataSubmit, dataReturn, deleteTransactionUrl) {

    let activeDate = $("#view-transaction .card:not(.d-none)").attr("transaction-date");
    // console.log(transactionDayNode)
    let transactionDayAfter = $(document).xpath(`//div[@id='view-transaction']//div[contains(@class, 'card') and (@transaction-date < '${dataSubmit['date-transaction']}')][1]`)[0]
    if (transactionDayAfter){
        let transactionDayNode = createTransactionDayNode(dataSubmit, dataReturn, deleteTransactionUrl, false);
        transactionDayAfter.before(transactionDayNode);
    }
    else {
        let transactionDayNode = createTransactionDayNode(dataSubmit, dataReturn, deleteTransactionUrl, true);
        $("#transaction-day-list").append(transactionDayNode);
    }
    if (activeDate < dataSubmit['date-transaction']) {
        cardActive += 1;
    }
}

function addTransactionToModel(transactionDay, dataSubmit, data, action) {
    let username = $("#username").text();
    transactionDay.append(getTransactionNode(dataSubmit, data, username, action));
}

function updateSum(transactionDay, amount, transactionType) {
    let sum = transactionDay.find(".sum")[0];
    let oldSumValue = parseInt($(sum).text());
    let newSumValue = transactionType === 'income'? oldSumValue + amount: oldSumValue - amount;
    $(sum).text(newSumValue);
}

function updateOverview(amount, transactionType) {
    let balanceAmount = $("#balance-amount")[0];
    if (transactionType === "income") {
        let inflow = $("#inflow")[0];
        $(inflow).text(parseInt($(inflow).text()) + amount);
        $(balanceAmount).text(parseInt($(balanceAmount).text()) + amount);
    }
    else {
        let outflow = $("#outflow")[0];
        console.log($(outflow).text());
        $(outflow).text(parseInt($(outflow).text()) - amount);
        $(balanceAmount).text(parseInt($(balanceAmount).text()) - amount);
    }
}
