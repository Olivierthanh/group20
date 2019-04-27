// Pagination View Transaction Click
$("#view-transaction").find(".card").each((index, day) => {
    if (index != 0) {
        $(day).addClass("d-none");
    }
});

$(".page-link").on("click", event => {
    if($(event.target).attr("data-original-title") === "Next") {
        if ($("#view-transaction .card:not(.d-none)").next().length > 0) {
            let nextCard = $("#view-transaction .card:not(.d-none)").next();
            $("#view-transaction .card:not(.d-none)").addClass("d-none");
            nextCard.removeClass("d-none");
        }
    }
    else if ($(event.target).attr("data-original-title") === "Previous") {
        if ($("#view-transaction .card:not(.d-none)").prev().length > 0) {
            let previousCard = $("#view-transaction .card:not(.d-none)").prev();
            $("#view-transaction .card:not(.d-none)").addClass("d-none");
            previousCard.removeClass("d-none");
        }
    }
});