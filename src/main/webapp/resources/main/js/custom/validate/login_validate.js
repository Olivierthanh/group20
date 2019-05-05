// Login Validate

$("#login-form").validate({
    "rules": {
        "email": {
            required: !0,
            email: !0
        },
        "password": {
            required: !0,
            minlength: 5
        }
    },
    "messages": {
        "email": "Hãy cung cấp email hợp lệ",
        "password": {
            required: "Hãy cung cấp mật khẩu",
            minlength: "Mật khẩu có độ dài ít nhất 5 ký tự"
        }
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
});

