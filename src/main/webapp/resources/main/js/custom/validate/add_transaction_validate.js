jQuery.validator.addMethod("dateLessThan",
    function(value, element, params) {
        if (params === "now")
            if (!/Invalid|NaN/.test(new Date(value))) {
                return new Date(value) < new Date();
            }
    }
);

// Add transaction validate

$("#add-transaction-form").validate({
    "rules": {
        "category": {
            required: !0,
        },
        "amount": {
            required: !0,
            number: 5
        },
        "note": {

        },
        "date-transaction": {
            required: !0,
            dateLessThan: "now"
        }
    },
    "messages": {
        "category": "Please select a transaction category",
        "amount": {
            required: "Please provide a amount of transaction",
            number: "Please enter a valid number"
        },
        "date-transaction": {
            required: "Please provide a transaction date",
            dateLessThan: "Transaction date must be less than now"
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