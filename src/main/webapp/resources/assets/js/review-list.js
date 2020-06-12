$("#show-reviews").click(function () {
    let review = $("#review-list");
    if (review.length) {
        review.remove();
    } else {
        $.getJSON("/restaurants/1/reviews", function (data) {
            let header = $("<div id='review-list' class='col-5 px-0'>")
                .append($("<h2>Reviews</h2>"));
            data.reviews.forEach(function (review) {
                header.append($("<div class='card border-secondary mb-3'>")
                    .append($("<div class='card-header text-left'>")
                        .append(review.user.username + ": " + Date.now()))
                    .append($("<div class='card-body text-dark'>")
                        .append("<p class='card-text text-left'>")
                        .append(review.text)
                    )).appendTo("#review");
            });
        });
    }
});
