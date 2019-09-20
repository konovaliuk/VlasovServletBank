<%--
  Created by IntelliJ IDEA.
  User: negat
  Date: 19/09/2019
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.currentLang}" scope="session"/>
<fmt:setBundle basename="pages/currentBankOperation"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <fmt:message key="send.money"/>
    <c:out value="${sessionScope.accountNumber}"/>
    <form action="/userpage/sendToAnAcc" method="post">
        <input type="radio" name="typeOfTransaction" value="currentBank" checked>
        <fmt:message key="current.bank"/>
        <br>
        <input type="radio" name="typeOfTransaction" value="anotherbank">
        <fmt:message key="another.bank"/>
        <input type="text" name="account" pattern="^[0-9]*[.,]?[0-9]+$"
               placeholder="<fmt:message key="recipient.account"/>" required>
        <c:if test="${requestScope.noEnoughMoney == true}">
            <fmt:message key="dont.have.money"/>
        </c:if>
        <br>
        <input type="text" name="money" pattern="^[0-9]*[.,]?[0-9]+$"
               placeholder="<fmt:message key="money.count"/>" required>
        <c:if test="${requestScope.noEnoughMoney == true}">
            <fmt:message key="wrong.rec.acc"/>
        </c:if>
        <br>
        <input type="submit" value="<fmt:message key="button.sendmoney"/>">
    </form>
    <br>
    <c:out value="${requestScope.exceptionError}"/>

</body>
</html>
