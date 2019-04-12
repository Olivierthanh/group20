// Change password validate
$("#reset-password-form").validate({
    "rules": {
        "password": {
            required: !0,
            minlength: 5
        },
        "confirm-password": {
            required: !0,
            equalTo: "#password"
        }
    },
    "messages": {
        "password": {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long"
        },
        "confirm-password": {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long",
            equalTo: "Please enter the same password as above"
        }
    },
    ignore: [],
    errorClass: "invalid-feedback animated fadeInUp",
    errorElement: "div",
    errorPlacement: function(e, a) {
        jQuery(a).parents(".form-group").append(e)
    },
    highlight: function(e) {
        jQuery(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
    },
    success: function(e) {
        jQuery(e).closest(".form-group").removeClass("is-invalid"), jQuery(e).remove()
    },
});