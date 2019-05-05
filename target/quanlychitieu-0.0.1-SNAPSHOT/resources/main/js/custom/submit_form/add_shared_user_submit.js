$(document).ready(() => {
    $("#add-shared-user-form").on("submit", event => {
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
                            let addSharedUserUrl = "/" + action.split("/")[1] + '/deleteSharedUser';
                            updateSharedUserView(dataSubmit['wallet-id'], data['data']['sharedUserName'], data['data']['sharedUserId'], addSharedUserUrl);
                            $(event.target)[0].reset();
                        }
                    });
                },
                error: error => {
                    swal('Thử lại !!', 'Có sự cố đã xảy ra !!', 'error');
                }
            });
        }
    })
});

function updateSharedUserView(walletId, sharedUserName, shareUserId, addSharedUserUrl) {
    let sharedUserView = $("#shared-user-view");
    if (0 === sharedUserView.find('span.d-block.shared-user').length) {
        sharedUserView.find("#no-user").remove();
        $("#wallet-type").text("Ví dùng chung");
    }
    sharedUserView.find('span.d-block:last-child')[0].before(createShareUserNode(walletId, sharedUserName, shareUserId, addSharedUserUrl));
}

function createShareUserNode(walletId, sharedUserName, shareUserId, addSharedUserUrl) {
    let node = `
    <span class="d-block shared-user">
        <form action="${addSharedUserUrl}" class="delete-shared-user-form">
            <span class="shared-user-name">${sharedUserName}</span>
            <input type="text" name="wallet-id" value="${walletId}" hidden/>
            <input type="text" name="shared-user-id" value="${shareUserId}" hidden/>
            <button type="submit" class="text-danger btn-primary-outline"
                    data-toggle="tooltip"
                    data-placement="right" title
                    data-original-title="Delete ${sharedUserName}">
                <i class="fa fa-times"></i>
            </button>
        </form>
    </span>
    `;
    return $.parseHTML(node)[1];
}