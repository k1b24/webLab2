<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Web 1st lab</title>
    <link rel="icon" href="favicon.ico" type="image/x-icon">
    <script src="js/jquery-3.6.0.js"></script>
    <script src="js/validator.js"></script>
    <script src="js/requests.js"></script>
    <script src="js/cleaner.js"></script>
    <script src="js/graph_visualizer.js"></script>
    <script src="js/user_input_workers.js"></script>
    <script src="js/result_table_updater.js" defer></script>
    <script type="text/javascript" charset="UTF-8"
            src="https://cdn.jsdelivr.net/npm/jsxgraph/distrib/jsxgraphcore.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/jsxgraph/distrib/jsxgraph.css" />
    <style>
        body {
            margin: 0;
            padding: 0;
            background-color: #cbf3ea;
            color: rgba(0, 0, 0, 0.92);
            width: 100%;
        }

        .form_table {
            border-spacing: 0;
            width: 100%;
            height: 100%;
            table-layout: fixed;
        }


        .header {
            background-color: #8ac6d1;
            font-family: monospace;
            font-size: 200%;
            font-weight: 700;
            text-align: center;
        }

        .form_table tr td {
            vertical-align: top;
            padding: 0;
            margin: 0;
        }

        /* Селектор псевдоэлемента */
        ::selection {
            background: #cbf3ea;
        }

        .main_table {
            width: 100vw;
        }

        .main_table tr td {
            padding: 0;
            margin: 0;
            vertical-align: top;
            text-align: center;
        }

        .plot_column {
            width: 20%;
            align-items: center;
        }

        .form_column {
            width: 20%;
        }

        .result_table_column {
            width: 60%;
            min-width: 450px;
            min-height: 200px;
        }

        .jxgbox {
            width: 300px;
            height: 300px;
            margin: 10%;
        }


        .clean_button {
            margin-top: 60px;
            flex-direction: column;
            justify-content: center;
            text-align: center;
        }

        .clean_button input {
            border-radius: 10px 10px 10px 10px;
        }

        #result_table {
            justify-content: center;
            border-radius: 20px 20px 20px 20px;
            font-family: 'Courier New', Courier, monospace;
            font-size: 90%;
            background-color: #ffffff;
            border: 3px solid #ffd4d5;
            margin: 2% auto auto;
            width: 90%;
        }

        #result_table td,
        th {
            padding: 2%;
        }

        .user_input {
            display: flex;
            justify-content: center;
            height: 350px;
            background: #ffffff;
            border: 5px solid #ffb6b9;
            border-radius: 20px 20px 20px 20px;
            margin: 60px 50px 50px;
            font-family: 'Courier New', Courier, monospace;
        }

        .user_input .values {
            display: flex;
            flex-direction: column;
            justify-content: center;
            text-align: center;
        }

        .user_input .values div {
            justify-content: center;
            display: flex;
            flex-direction: column;
            padding: 7px;
            font-size: 130%;
        }

        .user_input .x_value .btn-group {
            flex-direction: row;
            display: flex;
            justify-content: center;
            font-size: 70%;
        }

        .selected {
            background: #ffd4d5;
            border-style: solid;
        }

        .user_input .values input {
            width: 60%;
            align-self: center;
            border-radius: 10px 10px 10px 10px;
            height: 1.5em;
        }

        .user_input .values select {
            width: 50%;
            align-self: center;
            border-radius: 10px 10px 10px 10px;
            height: 2em;
        }

        /* Селектор дочернего элемента */
        .user_input > #form > .buttons {
            display: flex;
            flex-direction: row;
            justify-content: center;
            text-align: center;
            margin-top: 20px;
            height: 25%;
        }

        /* Селектор атрибутов */
        .user_input .buttons [type="submit"], .points_clean {
            margin: 10%;
            border-radius: 10px 10px 10px 10px;
        }

        /* Селектор атрибутов */
        .user_input .buttons [type="reset"] {
            margin: 10%;
            border-radius: 10px 10px 10px 10px;
        }

        .validation_info {
            display: flex;
            flex-direction: column;
            justify-content: center;
            text-align: center;
            align-items: center;
            width: 300px;
            background: #ffffff;
            border: 5px solid #ffb6b9;
            border-radius: 20px 20px 20px 20px;
            margin: 50px;
            font-family: 'Courier New', Courier, monospace;
            opacity: 0;
            transition: opacity 0.7s;
        }

        .validation_info.show {
            opacity: 1;
            transition: opacity 0.7s;
        }
    </style>
</head>

<%--<body onload="initTable()">--%>
<body onload="drawPlot(); initTable();">
<table class="form_table">
    <tr>
        <td class="header">
            <span>Лазеев Сергей P32111 1131</span>
        </td>
    </tr>
    <tr>
        <td>
            <table class="main_table">
                <tr>
                    <td class="plot_column">
                        <div id="jxgbox" class="jxgbox"></div>
                        <button class="points_clean" id="points_clean" type="button" onclick="drawPlot()">Удалить точки</button>
                    </td>
                    <td class="form_column">
                        <section class="user_input">
                            <form id="form" onsubmit="processSubmit(); return false;">
                                <div class="values">
                                    <div class="x_value">
                                        <label for="x_value">Введите X:</label>
                                        <div class="btn-group" data-toggle="buttons">
                                            <button type="button" class="x-button" id="x-2" onclick="processXSelection('-2')">-2</button>
                                            <button type="button" class="x-button" id="x-1_5" onclick="processXSelection('-1_5')">-1.5</button>
                                            <button type="button" class="x-button" id="x-1" onclick="processXSelection('-1')">-1</button>
                                            <button type="button" class="x-button" id="x-0_5" onclick="processXSelection('-0_5')">-0.5</button>
                                            <button type="button" class="x-button" id="x0" onclick="processXSelection('0')">0</button>
                                            <button type="button" class="x-button" id="x0_5" onclick="processXSelection('0_5')">0.5</button>
                                            <button type="button" class="x-button" id="x1" onclick="processXSelection('1')">1</button>
                                            <button type="button" class="x-button" id="x1_5" onclick="processXSelection('1_5')">1.5</button>
                                            <button type="button" class="x-button" id="x2" onclick="processXSelection('2')">2</button>
                                            <input type="hidden" name="x_value" id="x_value">
                                        </div>
                                    </div>

                                    <div class="y_value">
                                        <label for="y_value">Введите Y:</label>
                                        <input type="text" id="y_value" name="y_value" oninput="limitTextLength(event, 13)">
                                    </div>

                                    <div class="r_value">
                                        <label for="r_value">Введите R:</label>
                                        <input type="text" id="r_value" name="r_value" oninput="limitTextLength(event, 13)">
                                    </div>

                                </div>

                                <div class="buttons">
                                    <input id="reset" type="reset" value="Сбросить" onclick="cleanInput()">
                                    <input id="submit" type="submit" value="Проверить">
                                </div>
                            </form>
                        </section>

                        <section class="validation_info">

                        </section>

                    </td>
                    <td class="result_table_column">
                        <div class="clean_button">
                            <input type="button" value="Очистить таблицу" onclick="cleanTable()">
                        </div>
                        <table id="result_table">
                            <tr>
                                <th>X</th>
                                <th>Y</th>
                                <th>R</th>
                                <th>Текущее время</th>
                                <th>Результат</th>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>