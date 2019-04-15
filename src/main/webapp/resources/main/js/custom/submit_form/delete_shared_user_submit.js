// Delete shared user submit
$(".delete-shared-user-form").on("submit", event => {
    event.preventDefault();
    sharedUserName = $(event.target).find("span.shared-user-name").text();
    swal(
        {
            title: `Are you sure to delete ${sharedUserName} ?`,
            text: "You will not be able to recover this user !!",
            type: "warning",
            showCancelButton: !0,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete  !!",
            closeOnConfirm: !1
        }
    ).then(isConfirmed => {
        if (isConfirmed.value) {
            $(event.target).remove();
            swal("Deleted !!", `Hey, ${sharedUserName} has been deleted from your wallet !!`, "success");
        }
    });
})