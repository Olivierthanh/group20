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
            minlength: 1
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
        "email": "Hãy cung cấp email hợp lệ",
        "name": {
            required: "Hãy cung cấp tên của bạn",
            minlength: "Tên của bạn độ dài ít nhât 1 ký tự"
        },
        "password": {
            required: "Hãy cung cấp mật khẩu",
            minlength: "Mật khẩu có độ dài ít nhất 5 ký tự"
        },
        "confirm-password": {
            required: "Hãy cung cấp mật khẩu xác nhận",
            minlength: "Mật khẩu có độ dài ít nhất 5 ký tự",
            equalTo: "Mật khẩu không trùng khớp"
        },
        "address": "Hãy cung cấp một email hợp lệ",
        "date-of-birth": "Hãy cung cấp ngày sinh hợp lệ",
        "gender": "Hãy cung cấp giới tính"
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

