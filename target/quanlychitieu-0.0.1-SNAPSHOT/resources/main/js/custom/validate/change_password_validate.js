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
            required: "Hãy cung cấp mật khẩu hợp lệ",
            minlength: "Mật khẩu có độ dài ít nhất 5 ký tự"
        },
        "confirm-password": {
            required: "Hãy cung cấp xác nhận mật khẩu",
            minlength: "Mật khẩu xác nhận có độ dài ít nhất 5 ký tự",
            equalTo: "Mật khẩu xác nhận không khớp"
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