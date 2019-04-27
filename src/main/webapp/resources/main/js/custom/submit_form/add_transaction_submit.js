// Add transaction form submit
$(document).ready(() => {
    $("#add-transaction-form").on("submit", event => {
        event.preventDefault();
        if ($(event.target).valid()) {
            swal({
                title: "Accept to submit !!",
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
                                    if (action.split("/")[2] === "addTransaction") {
                                        let deleteTransactionUrl = "/" + action.split("/")[1] + '/deleteTransaction';
                                        updateModel(dataSubmit, data, deleteTransactionUrl);
                                        $(event.target)[0].reset();
                                    }
                                    else {
                                        updateTransaction(dataSubmit);
                                    }
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
    <div class="card-body py-0"
        transaction-id="${dataReturn['attachedData']}"
        transaction-type="${dataSubmit['transaction-type']}"
        category-name="${dataSubmit['category-name']}"
        category-id="${dataSubmit['category-id']}"
        amount="${dataSubmit['amount']}"
        note="${dataSubmit['note']}"
        transaction-date="${dataSubmit['date-transaction']}"
    >
        <hr>
        <div class="row transaction-row">
            <div class="col-2">
                <img class="img-fluid img-category" src="resources/main/icons/category_icon/cat_${dataSubmit['category-id']}.png"
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
    <div class="card w-100 p-4 transaction-day ${!isVisible ? 'd-none': ''}" transaction-date="${dataSubmit['date-transaction']}">
        <div class="card-header">
            <h3 class="card-title m-t-10 transaction-day-date">
                ${new Date(dataSubmit['date-transaction']).toDateString()}
            </h3>
            <div class="float-right">
                <p class="sum">${dataSubmit['transaction-type'] === 'income'? '+' + dataSubmit['amount']: '-' + dataSubmit['amount']}</p>
            </div>
        </div>
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
    let transactionDayAfter = $(document).xpath(`//div[@id='view-transaction']//div[contains(@class, 'card') and (@transaction-date < '${dataSubmit['date-transaction']}')][1]`)[0];
    if (transactionDayAfter){
        let transactionDayNode = createTransactionDayNode(dataSubmit, dataReturn, deleteTransactionUrl, false);
        transactionDayAfter.before(transactionDayNode);
    }
    else {
        let transactionDayNode = createTransactionDayNode(dataSubmit, dataReturn, deleteTransactionUrl, true);
        $("#transaction-day-list").append(transactionDayNode);
    }
}

function addTransactionToModel(transactionDay, dataSubmit, data, action) {
    let username = $("#username").text();
    transactionDay.append(getTransactionNode(dataSubmit, data, username, action));
}

function updateSum(transactionDay, amount, transactionType) {
    let sum = transactionDay.find(".sum");
    let oldSumValue = parseInt(sum.text());
    let newSumValue = transactionType === 'income'? oldSumValue + amount: oldSumValue - amount;
    sum.text(newSumValue);
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

function updateTransaction(dataSubmit) {

    let transactionNode = $(`#view-transaction .card-body[transaction-id=${dataSubmit['transaction-id']}]`);
    let oldTransactionDate = transactionNode.attr("transaction-date");
    let isDateChange = oldTransactionDate !== dataSubmit['date-transaction'];
    updateTransactionNode(transactionNode, dataSubmit, isDateChange);

    let transactionDay = $(`#view-transaction .card[transaction-date=${dataSubmit['date-transaction']}]`);
    if (oldTransactionDate !== dataSubmit['date-transaction']) {
        if (transactionNode.closest(".card").find(".card-body").length > 1) {
            if (transactionDay[0]) {
                console.log(1);
                transactionDay.append(transactionNode);
                let oldSum = parseInt(transactionDay.find('.sum').text());
                let amount = dataSubmit['transaction-type'] === "income" ? parseInt(dataSubmit['amount']): -parseInt(dataSubmit['amount']);
                transactionDay.find('.sum').text(oldSum + amount);
            }
            else {
                console.log(2);
                let action = "/" + window.location.pathname.split("/")[1] + "/deleteWallet";
                let transactionDayNode = createTransactionDayNode(dataSubmit, {attachedData: dataSubmit['transaction-id']}, action, false);
                let transactionDayAfter = $(document).xpath(`//div[@id='view-transaction']//div[contains(@class, 'transaction-day') and (@transaction-date < '${dataSubmit['date-transaction']}')][1]`);
                if (transactionDayAfter[0]) {
                    transactionDayAfter.before(transactionDayNode);
                }
                else {
                    $("#transaction-day-list").append(transactionDayNode);
                }
                transactionNode.remove();
            }
        }
        else {
            if (transactionDay[0]) {
                console.log(3);
                transactionDay.append(transactionNode.clone());
                let oldSum = parseInt(transactionDay.find('.sum').text());
                let amount = dataSubmit['transaction-type'] === "income" ? parseInt(dataSubmit['amount']): -parseInt(dataSubmit['amount']);
                transactionDay.find('.sum').text(oldSum + amount);
                let removeCard = transactionNode.closest(".card");
                if (removeCard.prev().length > 0) {
                    let prev = removeCard.prev();
                    removeCard.remove();
                    prev.removeClass("d-none");
                }
                else if (removeCard.next().length > 0) {
                    let next = removeCard.next();
                    removeCard.remove();
                    next.removeClass("d-none");
                }
            }
            else {
                console.log(4);
                let transactionDayNode = transactionNode.closest(".card");
                transactionDayNode.find('.transaction-day-date').text(new Date(dataSubmit['date-transaction']).toDateString());
                transactionDayNode.attr("transaction-date", dataSubmit['date-transaction']);
                let transactionDayAfter = $(document).xpath(`//div[@id='view-transaction']//div[contains(@class, 'transaction-day') and (@transaction-date < '${dataSubmit['date-transaction']}')][1]`);
                console.log(transactionDayAfter);
                if (transactionDayAfter[0]) {
                    transactionDayAfter.before(transactionDayNode.clone().addClass("d-none"));
                    if (transactionDayNode.prev().length > 0) {
                        let prev = transactionDayNode.prev();
                        transactionDayNode.remove();
                        prev.removeClass("d-none");
                    }
                    else if (transactionDayNode.next().length > 0) {
                        let next = transactionDayNode.next();
                        transactionDayNode.remove();
                        next.removeClass("d-none");
                    }
                }
                else {
                    $("#transaction-day-list").append(transactionDayNode);
                    transactionDayNode.remove();
                }
            }

        }
    }
    updateChart(dataSubmit['date-transaction']);
}

function updateTransactionNode(transactionNode, dataSubmit, isDateChange) {
    let oldTransactionType = transactionNode.attr("transaction-type");
    let oldAmount = oldTransactionType === "income" ? parseInt(transactionNode.attr("amount")): -parseInt(transactionNode.attr('amount'));
    let newAmount = dataSubmit['transaction-type'] === "income" ? parseInt(dataSubmit['amount']): -parseInt(dataSubmit['amount']);
    let oldSum = parseInt(transactionNode.closest(".card").find(".sum").text());
    let newSum = isDateChange ? oldSum - oldAmount : oldSum + newAmount - oldAmount;
    transactionNode.closest(".card").find(".sum").text(newSum);

    let oldInflow = parseInt($("#inflow").text());
    let oldOutflow = parseInt($('#outflow').text());
    let oldBalanceAmount = parseInt($("#balance-amount").text());
    let newInflow = oldInflow;
    let newOutflow = oldOutflow;
    if (oldTransactionType === "income") {
        newInflow -= oldAmount;
    }
    else {
        newOutflow -= oldAmount;
    }

    if (dataSubmit['transaction-type'] === "income") {
        newInflow += newAmount;
    }
    else {
        newOutflow += newAmount;
    }

    let newBalanceAmount = oldBalanceAmount - oldAmount + newAmount;

    $("#inflow").text(newInflow);
    $("#outflow").text(newOutflow);
    $("#balance-amount").text(newBalanceAmount);

    let imgCategoryUrl = transactionNode.find(".img-category").attr("src").replace(/\d+/, dataSubmit['category-id']);
    transactionNode.find(".img-category").attr("src", imgCategoryUrl);
    transactionNode.find(".category-name").text(dataSubmit['category-name']);
    transactionNode.find(".transaction-date").text(new Date(dataSubmit['date-transaction']).toDateString());
    transactionNode.find(".note").text(dataSubmit['note']);
    transactionNode.find(".transaction-amount").text((dataSubmit['transaction-type'] === 'income' ? "+ ": "- ") + dataSubmit['amount']);

    transactionNode.attr("transaction-id", dataSubmit['transaction-id']);
    transactionNode.attr("transaction-type", dataSubmit['transaction-type']);
    transactionNode.attr("category-name", dataSubmit['category-name']);
    transactionNode.attr("category-id", dataSubmit['category-id']);
    transactionNode.attr("amount", dataSubmit['amount']);
    transactionNode.attr("note", dataSubmit['note']);
    transactionNode.attr("transaction-date", dataSubmit['date-transaction']);
}


