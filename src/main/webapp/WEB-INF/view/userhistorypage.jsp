<%--
  Created by IntelliJ IDEA.
  User: negat
  Date: 15/09/2019
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${not empty sessionScope.currentLang}">
    <fmt:setLocale value="${sessionScope.currentLang}" scope="session"/>
</c:if>
<c:if test="${empty sessionScope.currentLang}">
    <fmt:setLocale value="en_EN" scope="session"/>
</c:if>
<fmt:setBundle basename="pages/userhistorypage"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fmt:message key="account.number"/>
<c:out value="${sessionScope.accountNumber}"/>
<br>
<br>
<form action="${pageContext.request.contextPath}workwithaccount" method="post">
    <label>
        <input name="typeofwork" type="radio" value="addmoney" required>
    </label>
    <fmt:message key="addmoney"/>
    <br>
    <label>
        <input name="typeofwork" type="radio" value="sendmoney" required>
    </label>
    <fmt:message key="sending.money"/>
    <br>
    <input type="submit" value="<fmt:message key="button.workwithacc"/>">
</form>
<br>
<table>
    <tr>
        <th><fmt:message key="acc.date.of.transaction"/></th>
        <th><fmt:message key="acc.transaction.amount"/></th>
        <th><fmt:message key="acc.current.balance"/></th>
        <th><fmt:message key="acc.notification"/></th>
    </tr>
    <c:forEach items="${requestScope.history}" var="acc">
        <tr>
            <td>${acc.dateOfTransaction}</td>
            <td>${acc.transactionAmount}</td>
            <td>${acc.currentBalance}</td>
            <td>${acc.notification}</td>
        </tr>
    </c:forEach>
</table>
<br>
<c:out value="${requestScope.exceptionError}"/>
<br>
<br>
<form method="get" action="${pageContext.request.contextPath}userpage">
    <input type="submit" value="<fmt:message key="back.to.userpage"/>">
</form>
</body>
</html>
