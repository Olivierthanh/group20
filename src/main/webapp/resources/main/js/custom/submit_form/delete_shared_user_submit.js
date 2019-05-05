// Delete shared user submit
$("body").on("submit", ".delete-shared-user-form", event => {
    event.preventDefault();
    let sharedUserName = $(event.target).find("span.shared-user-name").text();
    swal(
        {
            title: `Bạn chắc chắn muốn xóa ${sharedUserName} khỏi ví?`,
            text: "Bạn không thể phục hồi thao tác này !!",
            type: "warning",
            showCancelButton: !0,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Có, xóa  !!",
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
                    console.log(data);
                    swal(`${data['title']} !!`, `${data['message']} !!`, data['type']).then((value) => {
                        if (data['type'] === 'success') {
                            $(event.target).closest('span.d-block').remove();
                            if ($("#shared-user-view span.d-block").length === 1) {
                                $("#shared-user-view").prepend($(`<span id="no-user">Không có</span>`));
                                $("#wallet-type").text("Ví cá nhân");
                            }
                        }

                    });
                },
                error: error => {
                    swal('Thử lại !!', 'Có vấn đề đã xảy ra !!', 'error');
                }
            });

            // swal("Deleted !!", `Hey, ${sharedUserName} has been deleted from your wallet !!`, "success");
        }
    });
});