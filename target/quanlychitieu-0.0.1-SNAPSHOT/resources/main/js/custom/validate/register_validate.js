// Register Validate
jQuery.validator.addMethod("dateLessThan",
    function(value, element, params) {
        if (params === "now")
            if (!/Invalid|NaN/.test(new Date(value))) {
                return new Date(value).toLocaleDateString() <= new Date().toLocaleDateString();
            }
    }
);
$("#register-form").validate({
    "rules": {
        "email": {
            required: !0,
            email: !0
        },
        "name": {
            required: !0,
            minlength: 3
        },
        "password": {
            required: !0,
            minlength: 5
        },
        "confirm-password": {
            required: !0,
            equalTo: "#password"
        },
        "address": {
            required: !0
        },
        "date-of-birth": {
            required: !0,
            dateLessThan: "now"
        },
        "gender": {
            required: !0
        }
    },
    "messages": {
        "email": "Please enter a valid email address",
        "name": {
            required: "Please provide your name",
            minlength: "Your name must be at least 5 characters long"
        },
        "password": {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long"
        },
        "confirm-password": {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long",
            equalTo: "Please enter the same password as above"
        },
        "address": "Please enter a valid address",
        "date-of-birth": "Please enter a valid date",
        "gender": "Please select gender"
    },
    ignore: [],
    errorClass: "invalid-feedback animated fadeInUp",
    errorElement: "div",
    errorPlacement: function(e, a) {
        jQuery(a).parents(".form-group > div").append(e)
    },
    highlight: function(e) {
        jQuery(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
    },
    success: function(e) {
        jQuery(e).closest(".form-group").removeClass("is-invalid"), jQuery(e).remove()
    },
    // submitHandler: form => {
    //     $(form).submit();
    // }
});

