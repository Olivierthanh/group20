// Forgot password
$("a#forgot-password").on("click", (event) => {
    event.preventDefault();
    swal({
        title: 'Nhập email của bạn',
        input: 'email',
        inputPlaceholder: 'Nhập email của bạn vào đây'
    }).then(email => {
        let url = $("a#forgot-password").attr("href");
        if (email.value) {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: url,
                data: {
                    email: email.value
                },
                dataType: "json",
                timeout: 30000,
                success: data => {
                    swal(`${data['title']} !!`, `${data['message']} !!`, data['type']);

                },
                error: error => {
                    swal('Thử lại !!', 'Có vấn đề đã xảy ra !!', 'error');
                }
            });
        }
    })
})