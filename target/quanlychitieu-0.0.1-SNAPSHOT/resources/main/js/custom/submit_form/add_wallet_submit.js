$(document).ready(() => {
   $("#add-wallet-form").on("submit", event => {
       event.preventDefault();
       if ($(event.target).valid()) {
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
                           let walletId = data['data']['walletId'];
                           let walletName = data['data']['walletName'];
                           let viewWalletUrl = "/" + action.split("/")[1] + "/viewWallet" + walletId;
                           $("#wallet-list").append($(`<li><a  href="${viewWalletUrl}">${walletName}</a></li>`));
                           $(event.target)[0].reset();
                           if (window.location.pathname.split("/")[2].startsWith("home")) {
                               $("#wallet-table").DataTable().destroy()
                               $("#wallet-table tbody").append(createWalletTableRow(walletName, dataSubmit['currency'], data['data']['createdDate'], viewWalletUrl));
                               initWalletDataTable();
                           }
                       }
                   });
               },
               error: error => {
                   swal('Thử lại !!', 'Có vấn đề đã xảy ra !!', 'error');
               }
           });
       }
   })
});

function createWalletTableRow(walletName, currency, createdDate, viewWalletUrl) {
    node = `
    <tr>
        <td><a href="${viewWalletUrl}">${walletName}</a></td>
        <td class="text-right" style="padding-right: 5%;">0</td>
        <td class="text-center">${currency}</td>
        <td class="text-center">${createdDate}</td>
        <td class="text-center">Cá nhân</td>
    </tr>
    `;
    return $(node)[0];
}
