
<%--
  Created by IntelliJ IDEA.
  User: negat
  Date: 21/08/2019
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.currentLang}" scope="session"/>
<fmt:setBundle basename="pages/userpage"/>

<html>
<head>
    <title>Title</title>
    <style type="text/css">
        table.t_example {
            background-color: #cccccc;
            width: 400px
        }
        .t_example tr {
            background-color: #ffffff;
            height: 100px
        }
    </style>
</head>
<body>
<fmt:message key="greeting.user"/> <!--Обьеденить 2 тега в1 кастомный!!!-->
<c:out value="${sessionScope.user.userFirstname} ${sessionScope.user.userLastname}))"/>
<br>
<br>
<c:if test="${empty requestScope.accounts}">
    <fmt:message key="no.accounts"/>
</c:if>
<fmt:message key="customer.deposit.accounts"/><br>
<table class="t_example">
    <tr>
        <th><fmt:message key="account.number"/></th>
        <th><fmt:message key="account.type"/></th>
        <th><fmt:message key="current.balance"/></th>
        <th><fmt:message key="interest.rate"/></th>
        <th><fmt:message key="account.validity"/></th>
    </tr>
    <c:forEach items="${requestScope.depositAccounts}" var="depAcc">
        <tr>
            <td>${depAcc.accountNumber}</td>
            <td>${depAcc.accountType}</td>
            <td>${depAcc.currentBalance}</td>
            <td>${depAcc.interestRate}</td>
            <td>
                <fmt:message key="account.validity.message"/>
                    ${depAcc.accountValidity}
            </td>
            <td>
                <form action="/userpage/accountoperation" method="post">
                    <input name="accountNumber" type="hidden" value="${depAcc.accountNumber}">
                    <input type="submit" value="<fmt:message key="account.details"/>"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${not empty requestScope.creditAccounts}">
    <fmt:message key="customer.credit.accounts"/><br>
    <table class="t_example">
        <tr>
            <th><fmt:message key="account.number"/></th>
            <th><fmt:message key="account.type"/></th>
            <th><fmt:message key="current.balance"/></th>
            <th><fmt:message key="limit.for.credit"/></th>
            <th><fmt:message key="interest.rate"/></th>
            <th><fmt:message key="account.validity"/></th>
        </tr>
        <c:forEach items="${requestScope.creditAccounts}" var="credAcc">
            <tr>
                <td>${credAcc.accountNumber}</td>
                <td>${credAcc.accountType}</td>
                <td>${credAcc.currentBalance}</td>
                <td>${credAcc.creditLimit}</td>
                <td>${credAcc.interestRate}</td>
                <td>
                    <fmt:message key="account.validity.message"/>
                        ${credAcc.accountValidity}
                </td>
                <td>
                    <form action="/userpage/accountoperation" method="post">
                        <input name="accountNumber" type="hidden" value="${credAcc.accountNumber}">
                        <input type="submit" value="<fmt:message key="account.details"/>"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br>
<br>
<form action="userpage/createcreditacc" method="get">
    <input type="submit" value="<fmt:message key="account.request"/>"/>
</form>

<form action="/logout" method="get">
    <input type="submit" value="<fmt:message key="button.logout"/>"/>
</form>
</body>
</html>
