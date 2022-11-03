function validateInputedValues(x, y, r) {

    let validation_info_box = document.querySelector('.validation_info');
    validation_info_box.classList.remove("show");

    let validation_info = "";
    let x_validation_success = false;
    let y_validation_success = false;
    let r_validation_success = false;
    let validation_success = false;

    if (x === undefined || x === "") {
        validation_info += "<span>Не выбрано значение X!</span>";
    } else {
        x_validation_success = true;
    }

    if (!(y.trim() === "")) {
        if (/^([-\+]?\d+[\.,]\d+|[-\+]?\d+|[-\+]?\d+([\.,]\d+)?e[-\+]?\d+)$/.test(y.trim())) {
            if ((parseFloat(y) > -3) && (parseFloat(y) < 5)) {
                y_validation_success = true;
            } else validation_info += "<span>Координата Y задается числом в промежутке (-3..5)!</span>";
        } else validation_info += "<span>Координата Y задается числом!</span>";
    } else validation_info += "<span>Не введена координата Y!</span>";

    if (!(r.trim() === "")) {
        if (/^([-\+]?\d+[\.,]\d+|[-\+]?\d+|[-\+]?\d+([\.,]\d+)?e[-\+]?\d+)$/.test(r.trim())) {
            if ((parseFloat(r) > 1) && (parseFloat(r) < 4)) {
                r_validation_success = true;
            } else validation_info += "<span>Координата R задается числом в промежутке (1..4)!</span>";
        } else validation_info += "<span>Координата R задается числом!</span>";
    } else validation_info += "<span>Не введена координата R!</span>";

    validation_success = x_validation_success && y_validation_success && r_validation_success;

    if (!validation_success) {
        $(".validation_info").html("<br>" + validation_info + "<br>");
        validation_info_box.classList.add("show");
    }

    return validation_success;
}

function check_r() {
    let r = $('#r_value').val();
    if (!(r.trim() === "")) {
        if (/^([-\+]?\d+[\.,]\d+|[-\+]?\d+|[-\+]?\d+([\.,]\d+)?e[-\+]?\d+)$/.test(r.trim())) {
            if ((parseFloat(r) > 1) && (parseFloat(r) < 4)) {
                return true
            }
        }
    }
    return false;
}
