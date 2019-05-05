// Change password submit
$("#reset-password-form").on("submit", event => {
    event.preventDefault();
    if ($("#reset-password-form").valid()) {
        let action = $("#reset-password-form").attr("action");
        let loginUrl = $("#reset-password-form").attr("login-url");
        let formData = new FormData($("#reset-password-form")[0]);
        data = {}
        formData.forEach((value, key) => {
            data[key] = value;
        });
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: action,
            data: data,
            dataType: "json",
            timeout: 10000,
            success: data => {
                swal(`${data['title']} !!`, `${data['message']} !!`, data['type']).then((value) => {
                    if (data['type'] === 'success') {
                        window.location.replace(loginUrl);
                    }
                });

            },
            error: error => {
                swal('Thử lại !!', 'Có vấn đề đã xảy ra !!', 'error');
            }
        });
    }
});