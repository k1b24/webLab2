function processXSelection(x) {
    const field = $('#x_value');
    if (field.val() === x.replace('_', '.')) {
        field.val("");
        $('#x' + x).removeClass('selected');
    } else {
        if (field.val() !== "") {
            $('#x' + field.val().replace('.', '_')).removeClass('selected');
        }
        field.val(x.replace('_', '.'));
        $('#x' + x).addClass('selected');
    }
}

function limitTextLength(e, count) {
    if (e.target.value.indexOf('.') === -1) { return; }
    if ((e.target.value.length - e.target.value.indexOf('.')) > count) {
        e.target.value = e.target.value.slice(0, e.target.value.indexOf('.') + count + 1);
    }
}

function processSubmit() {
    let x = $('#x_value').val();
    let y = $('#y_value').val();
    receiveUpdate(x, y, $('#r_value').val());
}