<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html><body>

<div class="content">
    <div class="top">
           <h2>Результат поиска:</h2>
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
    <hr/>
    <input type="button" onclick="history.back();" value="Назад"/>
<button type="button" onclick="location.href='/counterparty/delete/${finderCounter.id}'">Удалить</button>
<button type="button" onclick="location.href='/counterparty/updatcounter/${finderCounter.id}'">Изменить</button>
</div>



</body></html>