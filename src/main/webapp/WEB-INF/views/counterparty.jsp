<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>

    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">

<button type="button" onclick="location.href='/counterparty/findbyname'">Поиск по имени</button>
<button type="button" onclick="location.href='/counterparty/findbybikandaccauntnum'">Поиск по бик и номеру счета</button>
<a href="/">Назад</a>
</div>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Справочник контрагентов
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