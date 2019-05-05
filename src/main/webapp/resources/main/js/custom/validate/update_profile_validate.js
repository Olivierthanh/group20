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
            minlength: 1,
            maxlength: 45
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
            required: "Hãy cung cấp tên của bạn",
            minlength: "Tên có độ dài ít nhất 1 ký tự",
            maxlength: "Tên có độ dài tối đa 45 ký tự"
        },
        "password": {
            required: "Hãy cung cấp mật khẩu",
            minlength: "Mật khẩu có độ dài ít nhất 5 ký tự"
        },
        "address": "Hãy cung cấp email hợp lệ",
        "date-of-birth": "Hãy cung cấp ngày sinh hợp lệ",
        "gender": "Hãy lựa chọn giới tính"
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