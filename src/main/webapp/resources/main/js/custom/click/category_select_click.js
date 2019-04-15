// Category select click
$("#category").on("click", event => {
    $("#add-transaction-view").modal("hide").on('hidden.bs.modal', event => {
        $("#transaction-category-select").modal('show');
        $(event.target).off('hidden.bs.modal');
    });
});

$(".img-category").on("click", event => {
    event.preventDefault();
    categoryId = $(event.currentTarget).attr("category-id");
    categoryName = $(event.currentTarget).attr("category-name");
    console.log(categoryId, categoryName);
    $("#category").val(categoryName);
    $("#category").attr("category-id", categoryId);
    $("#transaction-category-select").modal('hide').on('hidden.bs.modal', event => {
        $("#add-transaction-view").modal("show");
        $(event.target).off('hidden.bs.modal');
    });

});