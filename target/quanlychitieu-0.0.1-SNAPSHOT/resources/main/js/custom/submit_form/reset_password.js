// Forgot password
$("a#forgot-password").on("click", (event) => {
    event.preventDefault();
    swal({
        title: 'Input email address',
        input: 'email',
        inputPlaceholder: 'Enter your email address'
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
                    swal('Try again !!', 'There is something wrong happened !!', 'error');
                }
            });
        }
    })
})