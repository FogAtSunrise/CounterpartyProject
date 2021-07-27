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
        <h2>Удаление контрагента по наименованию</h2>
    </div>

    <form:form modelAttribute="deleteByName" method="POST" action="/counterparty/deletebyname">

    <fieldset>
                    <legend></legend>
                    <form:input path="name" placeholder="Наименование" maxlength="20"/>
                    <button type="submit" >Удалить</button>
                </fieldset>
                           <button type="button" onclick="location.href='/counterparty'">Назад</button>

    </form:form>
</div>


</body>
</html>