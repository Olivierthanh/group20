$("#add-wallet-form").validate({
    "rules": {
        "wallet-name": {
            required: !0,
            minlength: 5
        },
        "currency": {
            required: !0,
        }
    },
    "messages": {
        "wallet-name": {
            required: "Please enter a valid email address",
            minlength: "Wallet name contains at least 5 characters"
        },
        "currency": {
            required: "Please provide a currency",
        }
    },
    ignore: [],
    errorClass: "invalid-feedback animated fadeInUp",
    errorElement: "div",
    errorPlacement: function(e, a) {
        jQuery(a).parents(".col").append(e)
    },
    highlight: function(e) {
        jQuery(e).closest(".col").removeClass("is-invalid").addClass("is-invalid")
    },
    success: function(e) {
        jQuery(e).closest(".col").removeClass("is-invalid"), jQuery(e).remove()
    },
});