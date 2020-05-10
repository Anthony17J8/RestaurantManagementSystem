$('div.form-group').each(function () {
    const span = $(this).children('span');
    console.log($(this))
    if (span.length) {
        $(this).children('input').addClass('is-invalid');
        span.addClass('invalid-feedback')
    } else {
        const input = $(this).children('input');
        input.removeClass('is-invalid');
        input.addClass('is-valid');
    }
});