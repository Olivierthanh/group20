jQuery.validator.addMethod("dateLessThan",
    function(value, element, params) {
        if (params === "now")
            if (!/Invalid|NaN/.test(new Date(value))) {
                return new Date(value).toLocaleDateString() <= new Date().toLocaleDateString();
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
            number: !0,
            min: 1
        },
        "note": {

        },
        "date-transaction": {
            required: !0,
            dateLessThan: "now"
        }
    },
    "messages": {
        "category": "Hãy chọn loại giao dịch",
        "amount": {
            required: "Hãy cung cấp lượng giao dịch",
            number: "Hãy điền một số hợp lệ",
            min: "Hãy điền một số lớn hơn 0"
        },
        "date-transaction": {
            required: "Hãy cung cấp ngày giao dịch",
            dateLessThan: "Ngày giao dịch không được sau ngày hôm nay"
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