// Update date password validate
// Change password validate
$("#update-password-form").validate({
    "rules": {
        "old-password": {
            required: !0
        },
        "new-password": {
            required: !0,
            minlength: 5
        },
        "confirm-new-password": {
            required: !0,
            equalTo: "#new-password"
        }
    },
    "messages": {
        "old-password": {
            required: "Please provide old password",
            minlength: "Your password must be at least 5 characters long"
        },
        "new-password": {
            required: "Please provide new password",
            minlength: "Your password must be at least 5 characters long"
        },
        "confirm-new-password": {
            required: "Please provide confirm new password",
            equalTo: "This field must be matched to new password"
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