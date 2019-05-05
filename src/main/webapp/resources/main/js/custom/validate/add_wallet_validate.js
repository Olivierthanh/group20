$("#add-wallet-form").validate({
    "rules": {
        "wallet-name": {
            required: !0,
            minlength: 3,
            maxlength: 45
        },
        "currency": {
            required: !0,
        }
    },
    "messages": {
        "wallet-name": {
            required: "Hãy nhập một email hợp lệ",
            minlength: "Tên ví ít nhất 3 ký tự",
            maxlength: "Tên ví tối đa 45 ký tự"
        },
        "currency": {
            required: "Hãy cung cấp loại tiền tệ",
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