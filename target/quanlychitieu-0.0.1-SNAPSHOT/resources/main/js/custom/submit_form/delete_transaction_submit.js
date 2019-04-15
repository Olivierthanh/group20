// Delete transaction submit
$(".delete-transaction-form").on("submit", event => {
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
            if ($(event.target).closest(".card").find(".card-body").length === 1) {
                cards = $("#view-transaction").find(".card");
                $(event.target).closest(".card").remove();
                if (active > 0) {
                    active = active - 1;
                    $(cards[active]).removeClass("d-none");
                }
                else if (active + 1 < cards.length) {
                    $(cards[active + 1]).removeClass("d-none");
                }
            }
            else {
                $(event.target).closest(".card-body").remove();
            }
            swal("Deleted !!", `Hey, this transaction has been deleted from your wallet !!`, "success");
        }
    });
})