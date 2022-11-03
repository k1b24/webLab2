function updateTable(data) {
    let date = new Date(data["date"] * 1000);
    let html_row = "<tr><th>" + data["x"] + "</th><th>" + data["y"]
        + "</th><th>" + data["r"] + "</th><th>" + date.toISOString().slice(0, 19).replace("T", " ")
        + "</th><th>" + data["result"] + "</th></tr>";
    $('#result_table tr:last').after(html_row);
    drawNewPoint(data["x"], data["y"], data["result"])
}