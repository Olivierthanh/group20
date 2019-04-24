// Category select click
$("#category-name").on("click", event => {
    $("#add-transaction-view").modal("hide").on('hidden.bs.modal', event => {
        $("#transaction-category-select").modal('show');
        $(event.target).off('hidden.bs.modal');
    });
});

$(".img-category").on("click", event => {
    event.preventDefault();
    let categoryId = $(event.currentTarget).attr("category-id");
    let categoryName = $(event.currentTarget).attr("category-name");
    let transactionType = $(event.currentTarget).attr("transaction-type");
    console.log(categoryId, categoryName);
    $("#category-name").val(categoryName);
    $("#category-id").val(categoryId);
    $("#transaction-type").val(transactionType);
    $("#transaction-category-select").modal('hide').on('hidden.bs.modal', event => {
        $("#add-transaction-view").modal("show");
        $(event.target).off('hidden.bs.modal');
    });

});