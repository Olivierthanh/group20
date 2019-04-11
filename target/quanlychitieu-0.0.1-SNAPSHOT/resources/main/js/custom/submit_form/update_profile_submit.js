// Update profile submit

$("#update-profile-form").on("change", event => {
    $("#update-profile-submit-button").attr("disabled", false);
    // $("#cancel-update-profile-button").attr("disabled", false);
});

$("#update-profile-form").on("submit", event => {
    event.preventDefault();
    if ($("#update-profile-form").valid()) {
        swal({
            title: "Accept to update your profile !!",
            text: "Submit Ok to update, Cancel to cancel !!",
            type: "info",
            showCancelButton: true,
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
        }).then((isConfirmed) => {
            let formData = new FormData($("#update-profile-form")[0]);
            formData.append("email", $("#email").val());
            let action = $("#update-profile-form").attr("action");
            let dataSubmit = {};
            formData.forEach((value, key) => {
                dataSubmit[key] = value;
            });
            console.log(dataSubmit);
            if (isConfirmed.value) {
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
                                updateModel(dataSubmit);
                            }
                        });
                    },
                    error: error => {
                        swal('Try again !!', 'There is something wrong happened !!', 'error');
                    }
                });
            }
        })
    }

});


function updateModel(data) {
    $("div.user-intro p small").text(`@ ${data['name']}`);
    $("div.contact-addresses ul.contact-address-list li.email p").text(`${data['email']}`);
    $("div.contact-addresses ul.contact-address-list li.address p").text(`${data['address']}`);
    $("#update-profile-submit-button").attr("disabled", true);
    // $("#cancel-update-profile-button").attr("disabled", true);
}