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
            required: "Hãy cung cấp mật khẩu cũ",
            minlength: "Mật khẩu có độ dài ít nhất 5 ký tự"
        },
        "new-password": {
            required: "Hãy cung cấp mật khẩu mới",
            minlength: "Mật khẩu có độ dài ít nhất 5 ký tự"
        },
        "confirm-new-password": {
            required: "Hãy cung cấp xác nhận mật khẩu mới",
            equalTo: "Mật khẩu xác nhận không trùng khớp"
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