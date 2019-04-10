// Submit Register Form
$("#register-form").on("submit", event => {
    event.preventDefault();
    let action = $("#register-form").attr('action');
    let loginUrl = $("#register-form").attr('login-url');
    let formData = new FormData($("#register-form")[0]);
    data = {};
    formData.forEach((v, k) => {
        data[k] = v;
    });
    console.log(data);
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
            swal('Try again !!', 'There is something wrong happened !!', 'error');
        }
    });
});