// Update profile validate
jQuery.validator.addMethod("dateLessThan",
    function(value, element, params) {
        if (params === "now")
            if (!/Invalid|NaN/.test(new Date(value))) {
                return new Date(value) < new Date();
            }
    }
);
$("#update-profile-form").validate({
    "rules": {
        "name": {
            required: !0,
            minlength: 3
        },
        "password": {
            required: !0,
            minlength: 5
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
        "name": {
            required: "Please provide your name",
            minlength: "Your name must be at least 5 characters long"
        },
        "password": {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long"
        },
        "address": "Please enter a valid address",
        "date-of-birth": "Please enter a valid date",
        "gender": "Please select gender"
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