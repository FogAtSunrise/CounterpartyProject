<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html><body>
<head>

    <title>Поиск контрагента</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="/css/styles.css">

</head>
<div class="content">
    <div class="top">

                   <span style="font-weight:bold">Результат поиска:</span>
    </div>

    <table>
        <tr>
            <th align="right">Наименование</th>
            <th align="left">${finderCounter.name}</th>
        </tr>
        <tr>
            <th align="right">ИНН</th>
            <th align="left">${finderCounter.inn}</th>
        </tr>
        <tr>
            <th align="right">КПП</th>
            <th align="left">${finderCounter.kpp}</th>
        </tr>
        <tr>
            <th align="right">Номер счета</th>
            <th align="left">${finderCounter.accountNumber}</th>
        </tr>
        <tr>
            <th align="right">БИК банка</th>
            <th align="left">${finderCounter.bik}</th>
        </tr>
    </table>
<button type="button" onclick="history.back();">Назад</button>
<button type="button" onclick="location.href='/counterparty/delete/${finderCounter.id}'">Удалить</button>
<button type="button" onclick="location.href='/counterparty/updatcounter/${finderCounter.id}'">Изменить</button>
</div>



</body></html>