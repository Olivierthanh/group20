// Add shared user validate
$("#add-shared-user-form").validate({
    "rules": {
        "shared-user": {
            required: !0,
            email: !0
        },
    },
    "messages": {
        "shared-user": "Bạn hãy điền một email hợp lệ",
    },
    ignore: [],
    errorClass: "invalid-feedback animated fadeInUp",
    errorElement: "div",
    errorPlacement: function(e, a) {
        jQuery(a).closest(".col").append(e)
    },
    highlight: function(e) {
        jQuery(e).closest(".col").removeClass("is-invalid").addClass("is-invalid")
    },
    success: function(e) {
        jQuery(e).closest(".col").removeClass("is-invalid"), jQuery(e).remove()
    },
})