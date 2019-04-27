// Transaction row click
$(document).ready(() => {
    $("#add-transaction-button").on("click", event => {
        let action = "/" + $("#add-transaction-form").attr("action").split("/")[1] + "/addTransaction";
        $("#add-transaction-form")[0].reset();
        $("#add-transaction-form").attr("action", action);
        $("#add-transaction-view .modal-title").text("Add Transaction");
        $("#add-transaction-view button[type=submit]").text("Add");

        $("#add-transaction-view").modal("show");
    });

   $("body").on("click", ".transaction-row", event => {
       let node = $(event.currentTarget).closest(".card-body");
       let addTransactionForm = $("#add-transaction-form");
       let categoryName = node.attr("category-name");
       let categoryId = node.attr("category-id");
       let transactionType = node.attr("transaction-type");
       let transactionId = node.attr("transaction-id");
       let amount = node.attr("amount");
       let note = node.attr("note");
       let transactionDate = node.attr("transaction-date");
       let action = "/" + addTransactionForm.attr("action").split("/")[1] + "/updateTransaction";

       $("#category-name").val(categoryName);
       $("#category-id").val(categoryId);
       $("#transaction-type").val(transactionType);
       $("#transaction-id").val(transactionId);
       $("#amount").val(amount);
       $("#note").val(note);
       $("#date-transaction").val(transactionDate);

       addTransactionForm.attr("action", action);
       $("#add-transaction-view .modal-title").text("Update Transaction");
       $("#add-transaction-view button[type=submit]").text("Update");

       $("#add-transaction-view").modal("show");
   })
});