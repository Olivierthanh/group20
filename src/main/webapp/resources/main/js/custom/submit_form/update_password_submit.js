// Update password submit
$(document).ready(() => {
   $("#update-password-form").on("submit", event => {
       event.preventDefault();
       if ($(event.target).valid()) {
           swal(
               {
                   title: `Bạn chắc chắn muốn đổi mật khẩu ?`,
                   // text: "You will not be able to recover this user !!",
                   type: "warning",
                   showCancelButton: !0,
                   confirmButtonColor: "#DD6B55",
                   confirmButtonText: "Có",
                   closeOnConfirm: !1
               }
           ).then(isConfirmed => {
               if (isConfirmed.value) {
                   let action = $(event.target).attr("action");
                   let formData = new FormData(event.target);
                   let dataSubmit = {};
                   formData.forEach((value, key) => {
                       dataSubmit[key] = value;
                   });
                   $.ajax({
                       type: "GET",
                       contentType: "application/json",
                       url: action,
                       data: dataSubmit,
                       dataType: "json",
                       timeout: 10000,
                       success: data => {
                           swal(`${data['title']} !!`, `${data['message']} !!`, data['type']).then((value) => {
                               if (data['type'] === 'success') {
                                   $(event.target)[0].reset();
                               }

                           });
                       },
                       error: error => {
                           swal('Thử lại !!', 'Có vấn đề đã xảy ra!!', 'error');
                       }
                   });
               }
           });
       }
   })
});