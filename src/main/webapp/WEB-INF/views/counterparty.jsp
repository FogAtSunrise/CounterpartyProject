<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>

    <title>Справочник контрагентов</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="content">
    <div class="top">

<button type="button" onclick="location.href='/counterparty/findbyname'">Поиск по имени</button>
<button type="button" onclick="location.href='/counterparty/findbybikandaccauntnum'">Поиск по бик и номеру счета</button>
<button type="button" onclick="location.href='/counterparty/deletebyname'">Удалить по имени</button>
<a href="/">Назад</a>
</div></div>
<div class="content">
    <div class="top">
     <span style="font-weight:bold">Справочник контрагентов</span>

        <button type="button" onclick="location.href='/counterparty/addcounter'">Добавить</button>
    </div>
    <table>
        <tr>
            <th>Наименование</th>
            <th>ИНН</th>
            <th>КПП</th>
            <th>Номер счета</th>
            <th>БИК банка</th>
        </tr>

        <c:forEach items="${counterpartyFromServer}" var="counterparty">
            <tr>
                <td>${counterparty.name}</td>
                <td>${counterparty.inn}</td>
                <td>${counterparty.kpp}</td>
                 <td>${counterparty.accountNumber}</td>
                 <td>${counterparty.bik}</td>
                    <td width="auto"><button type="button" onclick="location.href='/counterparty/updatcounter/${counterparty.id}'">Изменить</button></td>
                 <td width="auto"><button type="button" onclick="location.href='/counterparty/delete/${counterparty.id}'">Удалить</button></td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>