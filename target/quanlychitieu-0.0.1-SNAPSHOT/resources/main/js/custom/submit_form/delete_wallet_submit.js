// Delete Wallet submit
$("#delete-wallet-form").on("submit", event => {
    event.preventDefault();
    swal(
        {
            title: "Bạn chắc chắn muốn xóa ví này ?",
            text: "Bạn không thể phục hồi thao tác này !!",
            type: "warning",
            showCancelButton: !0,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, xóa nó !!",
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
                    swal('Thử lại !!', 'Có vấn đề đã xảy ra !!', 'error');
                }
            });
            // swal("Deleted !!", "Hey, your imaginary file has been deleted !!", "success");
        }
    });
})