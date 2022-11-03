 function receiveUpdate(x_value, y_value, r_value) {
    if (validateInputedValues(x_value, y_value, r_value)) {
        $.ajax({
            type: "GET",
            url: "controller-servlet",
            async: false,
            dataType: "json",
            data: {"x_value": x_value.trim(), "y_value": y_value.trim(), "r_value": r_value.trim(), "timezone": new Date().getTimezoneOffset()},
            success: function(data) {
                updateTable(data);
            },
            error: function(data) {
                alert(data);
            }
        });
    }
}

function initTable() {
    $.ajax({
        type: "POST",
        url: "controller-servlet",
        async: false,
        dataType: "json",
        success: function(data) {
            data.forEach(item => updateTable(item));
        },
        error: function(data) {
            alert(data);
        }
    });
}

function sendCleanTableRequest() {
    $.ajax({
        type: "PUT",
        url: "controller-servlet",
        async: false,
    });
}
