function create_rectangle(board, r) {
    let rectanglePoint1 = board.create('point', [0, 0], {name: '', fixed: true, size: 0});
    let rectanglePoint2 = board.create('point', [-r / 2, 0], {name: '', fixed: true, size: 0});
    let rectanglePoint3 = board.create('point', [-r / 2, r], {name: '', fixed: true, size: 0});
    let rectanglePoint4 = board.create('point', [0, r], {name: '', fixed: true, size: 0});
    return board.create('polygon', [rectanglePoint1, rectanglePoint2, rectanglePoint3, rectanglePoint4],
        {fillColor: '#ffb6b9', fillOpacity: 1, borders : {strokeColor: "#000000"}});
}

function create_triangle(board, r) {
    let trianglePoint1 = board.create('point', [0, 0], {name: '', fixed: true, size: 0});
    let trianglePoint2 = board.create('point', [0, r / 2], {name: '', fixed: true, size: 0});
    let trianglePoint3 = board.create('point', [r, 0], {name: '', fixed: true, size: 0});
    return board.create('polygon', [trianglePoint1, trianglePoint2, trianglePoint3], {fillColor: '#ffb6b9', fillOpacity: 1, borders : {strokeColor: "#000000"}});
}

function createCircle(board, r) {
    let p1 = board.create('point', [0, 0], {name: '', fixed: true, size: 0});
    let p2 = board.create('point', [0, -r], {name: '', fixed: true, size: 0});
    let p3 = board.create('point', [r, 0], {name: '', fixed: true, size: 0});
    return board.create('sector', [p1, p2, p3], {fillColor: '#ffb6b9', fillOpacity: 1, strokeColor:"#000000"});
}

var board = null;

function drawPlot() {
    let drawnObjects = []
    board = JXG.JSXGraph.initBoard('jxgbox', {boundingbox: [-5, 5, 5, -5], axis: true, showCopyright: false, showNavigation: false});

    drawnObjects = drawNewFigures($('#r_value').val(), board, drawnObjects);

    document.getElementById('jxgbox').addEventListener("click", function (event) {
        if (check_r()) {
            let coords = board.getUsrCoordsOfMouse(event);
            receiveUpdate(coords[0].toString(), coords[1].toString(), $('#r_value').val())
        }
    });


    $('input[type="text"][name="r_value"]').change(function () {
        drawnObjects = drawNewFigures(this.value, board, drawnObjects);
    });
}

function drawNewPoint(x, y, result) {
    let color = result ? "#32CD32" : "#FF0000";
    board.create("point", [x, y], {name: '', fixed: true, visible: true, fillColor: color, fillOpacity: 1,
        strokewidth: 0});

}

function drawNewFigures(r_value, board, drawnObjects) {
    if (check_r()) {
        for (const object of drawnObjects) {
            board.removeObject(object);
        }
        const r = parseFloat(r_value);
        let rectangle = create_rectangle(board, r);
        let triangle = create_triangle(board, r);
        let circle = createCircle(board, r);
        return [rectangle, triangle, circle];
    }
    return drawnObjects;
}
