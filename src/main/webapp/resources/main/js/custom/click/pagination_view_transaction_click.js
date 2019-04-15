// Pagination View Transaction Click
active = 0;

$("#view-transaction").find(".card").each((index, day) => {
    if (index != 0) {
        $(day).addClass("d-none");
    }
});

$(".page-link").on("click", event => {
    cards = $("#view-transaction").find(".card");
    if($(event.target).attr("data-original-title") === "Next") {
        if (active + 1 < cards.length) {
            $(cards[active]).addClass("d-none");
            active = active + 1;
            $(cards[active]).removeClass("d-none");
        }
    }
    else if ($(event.target).attr("data-original-title") === "Previous") {
        if (active > 0) {
            $(cards[active]).addClass("d-none");
            active = active - 1;
            $(cards[active]).removeClass("d-none");
        }
    }
});