let defaultR = 2;
let currentR;
let currentPoints;

let canvasPlot;
let ctx;
let canvasPlotWidth;
let canvasPlotHeight;
let xAxis;
let yAxis;
//отступ между границами сетки
const scaleX = 30;
const scaleY = 30;

//коэффициент смещения текста от осей
const shiftNames = 5;
const shiftAxisNames = 20;

window.addEventListener("DOMContentLoaded", () => {
    canvasPlot = document.getElementById("canvas");
    ctx = canvasPlot.getContext("2d");
    //ширина и высота канваса
    //width = 400, height = 250
    canvasPlotWidth = canvasPlot.clientWidth;
    canvasPlotHeight = canvasPlot.clientHeight;
    //значения рисования координатных осей
    //xAxis = 240, yAxis = 160
    xAxis = Math.round(canvasPlotWidth / scaleX / 2) * scaleX;
    yAxis = Math.round(canvasPlotHeight / scaleY / 2) * scaleY;
    //форматирование текста
    ctx.textAlign = "left";
    ctx.textBaseline = "top";
    draw()

    //обработка нажатия на область канваса
    canvasPlot.addEventListener("click", function (event) {
        document.getElementById("validation-info").textContent = "";
        // Получите координаты нажатия мыши относительно canvas
        const x = event.clientX - canvasPlot.getBoundingClientRect().left;
        const y = event.clientY - canvasPlot.getBoundingClientRect().top;

        let tableX = (x - xAxis) / scaleX;
        let tableY = (yAxis - y) / scaleY;

        if (tableX < -3 || tableX > 5) {
            document.getElementById("validation-info").textContent = "Значение X не соответствует указанному диапозону!";
        } else {
            if (tableY < -3 || tableY > 5) {
                document.getElementById("validation-info").textContent = "Значение Y не соответствует указанному диапозону!";
            } else {
                document.getElementById("validation-info").textContent = "";

                // Рисуем точку в месте нажатия
                ctx.beginPath();
                ctx.arc(x, y, 4, 0, 2 * Math.PI);
                ctx.fillStyle = "#2b2d42"; // Цвет точки
                ctx.fill();

                sendData(tableX.toFixed(2), tableY.toFixed(2));
            }
        }
    });

});

//вызывается при загрузке html-страницы и потом после передачи ей значений
function draw(r, points) {
    ctx.clearRect(0, 0, canvasPlotWidth, canvasPlotHeight);
    drawGrid();
    drawAxes();

    currentR = r;
    if (currentR === undefined) {
        currentR = defaultR
        drawText(defaultR);
        drawPolygon(defaultR);
    } else {
        drawText(currentR);
        drawPolygon(currentR);
    }
    if (points === undefined) {
        points = currentPoints;
    } else {
        currentPoints = points;
    }
    if (points !== undefined) {
        points.forEach(function (point) {
            let color = point.status === "Hit!" ? "green" : "red"
            if (point.r === Number(currentR)) {
                drawPoint(r, point.x, point.y, color);
            }
        });
    }
}

//рисование сетки - всегда статично
function drawGrid() {
    ctx.beginPath();
    ctx.strokeStyle = "#ced0ce";
    //Горизонтальные линии
    for (let i = 0; i <= canvasPlotWidth; i = i + scaleX) {
        ctx.moveTo(i, 0);
        ctx.lineTo(i, canvasPlotHeight);
    }
    //Вертикальные линии
    for (let i = 0; i <= canvasPlotHeight; i = i + scaleY) {
        ctx.moveTo(0, i);
        ctx.lineTo(canvasPlotWidth, i);
    }

    ctx.stroke();
    ctx.closePath();
}

//рисование главных осей - всегда статично
function drawAxes() {
    ctx.font = `${Math.round(scaleX / 2)}px Arial`;
    ctx.fillStyle = "black";
    ctx.beginPath();
    ctx.strokeStyle = "#000000";

    ctx.moveTo(xAxis, 0);
    ctx.lineTo(xAxis, canvasPlotHeight);
    ctx.fillText("y", xAxis - shiftAxisNames, 0);

    ctx.moveTo(0, yAxis);
    ctx.lineTo(canvasPlotWidth, yAxis);
    ctx.fillText("x", canvasPlotWidth - shiftAxisNames, yAxis - shiftAxisNames);

    ctx.stroke();
    ctx.closePath();
}

//рисование подписей к главным осям - зависит от R
function drawText(r) {
    ctx.fillStyle = "#4f4f4f";
    ctx.font = `${Math.round((r * 10) / 2)}px Arial`;

    //ось x
    ctx.fillText(
        "-R/2",
        xAxis - (scaleX * r) / 2 + shiftNames,
        yAxis + shiftNames
    );
    ctx.fillText(0, xAxis + shiftNames, yAxis + shiftNames);
    ctx.fillText(
        "R/2",
        xAxis + (scaleX * r) / 2 + shiftNames,
        yAxis + shiftNames
    );

    //ось y
    ctx.fillText(
        "R/2",
        xAxis + shiftNames,
        yAxis - (scaleY * r) / 2 + shiftNames
    );
    ctx.fillText(
        "-R/2",
        xAxis + shiftNames,
        yAxis + (scaleY * r) / 2 + shiftNames
    );
    ctx.fillText(
        "-R",
        xAxis + shiftNames,
        yAxis + (scaleY * r) + shiftNames
    );
}

function drawPolygon(r) {
    drawRect(r);
    drawTriangle(r);
    drawArc(r);
}

//рисование прямоугольника - зависит от R
function drawRect(r) {
    ctx.beginPath();
    // ctx.rect(xAxis - scaleX * r, yAxis, scaleX * r, scaleX * (r / 2));
    ctx.rect(xAxis, yAxis, scaleX * r / 2, scaleY * r);
    ctx.closePath();
    ctx.strokeStyle = "#ffba08";
    ctx.fillStyle = "rgba(163, 155, 168, 0.5)";
    ctx.fill();
    ctx.stroke();
}

//рисование круга - зависит от R
function drawArc(r) {
    ctx.beginPath();
    ctx.moveTo(xAxis, yAxis);
    ctx.arc(xAxis, yAxis, scaleX * (r / 2), -Math.PI / 2, 0, false);
    ctx.closePath();
    ctx.strokeStyle = "#ffba08";
    ctx.fillStyle = "rgba(163, 155, 168, 0.5)";
    ctx.fill();
    ctx.stroke();
}

//рисование треугольника - зависит от R
function drawTriangle(r) {
    ctx.beginPath();
    ctx.moveTo(xAxis, yAxis);
    ctx.lineTo(xAxis, yAxis + scaleX * (r / 2));
    ctx.lineTo(xAxis - scaleX * (r / 2), yAxis);
    ctx.closePath();
    ctx.strokeStyle = "#ffba08";
    ctx.fillStyle = "rgba(163, 155, 168, 0.5)";
    ctx.fill();
    ctx.stroke();
}

//рисование точки при наличии значений x, y
function drawPoint(r, x, y, color) {
    ctx.beginPath();
    const scaledX = xAxis + x * scaleX;
    const scaledY = yAxis - y * scaleY;
    ctx.arc(scaledX, scaledY, 4, 0, 2 * Math.PI);
    ctx.fillStyle = color; // Цвет точки, например, синий
    ctx.fill();
    ctx.closePath();
}




