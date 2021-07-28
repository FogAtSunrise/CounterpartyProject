<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>

    <title>Поиск контрагента</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="/css/styles.css">

</head>
<body>

<div class="content">
    <div class="top">
        <span style="font-weight:bold">Удаление контрагента по наименованию</span>
    </div>

    <form:form modelAttribute="deleteByName" method="POST" action="/counterparty/deletebyname">

                    <legend></legend>
                    <form:input path="name" placeholder="Наименование" maxlength="20"/>
                    <button type="submit" >Удалить</button>

                           <button type="button" onclick="location.href='/counterparty'">Назад</button>

    </form:form>
</div>


</body>
</html>