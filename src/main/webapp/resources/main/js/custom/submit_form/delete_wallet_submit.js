// Delete Wallet submit
$("#delete-wallet-form").on("submit", event => {
    event.preventDefault();
    swal(
        {
            title: "Are you sure to delete ?",
            text: "You will not be able to recover this wallet !!",
            type: "warning",
            showCancelButton: !0,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it !!",
            closeOnConfirm: !1
        }
    ).then(isConfirmed => {
        if (isConfirmed.value) {
            swal("Deleted !!", "Hey, your imaginary file has been deleted !!", "success");
        }
    });
})