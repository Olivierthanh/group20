// Pagination View Transaction Click
let cardActive = 0;

$("#view-transaction").find(".card").each((index, day) => {
    if (index != 0) {
        $(day).addClass("d-none");
    }
});

$(".page-link").on("click", event => {
    let cards = $("#view-transaction").find(".card");
    if($(event.target).attr("data-original-title") === "Next") {
        if (cardActive + 1 < cards.length) {
            $(cards[cardActive]).addClass("d-none");
            cardActive = cardActive + 1;
            $(cards[cardActive]).removeClass("d-none");
        }
    }
    else if ($(event.target).attr("data-original-title") === "Previous") {
        if (cardActive > 0) {
            $(cards[cardActive]).addClass("d-none");
            cardActive = cardActive - 1;
            $(cards[cardActive]).removeClass("d-none");
        }
    }
});