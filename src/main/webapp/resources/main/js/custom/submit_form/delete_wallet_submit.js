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
            let action = $(event.target).attr("action");
            let formData = new FormData(event.target);
            let dataSubmit = {};
            formData.forEach((value, key) => {
                dataSubmit[key] = value;
            });
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
                            let homeUrl = $(event.target).attr("home");
                            window.location.replace(homeUrl);
                        }
                    });
                },
                error: error => {
                    swal('Try again !!', 'There is something wrong happened !!', 'error');
                }
            });
            // swal("Deleted !!", "Hey, your imaginary file has been deleted !!", "success");
        }
    });
})