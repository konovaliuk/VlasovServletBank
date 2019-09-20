<%--
  Created by IntelliJ IDEA.
  User: negat
  Date: 12/09/2019
  Time: 20:48
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
<fmt:setBundle basename="pages/pagetext"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<fmt:message key="main.message.index"/>
<br>
<br>
<fmt:message key="choose.language"/><br>
<form action="changeLang" method="post">
    <label>
        <select name="language">
            <option value="ru_RU">русский</option>
            <option value="en_EN">english</option>
        </select>
        <input type="submit" value="<fmt:message key="lang.button"/>"/>

    </label>
</form>
<br>
<form method="post" action="loginuser">

    <label>
        <input name="email" type="email" placeholder="<fmt:message key="users.login"/>" required>
    </label>

    <c:if test="${not empty requestScope.wrongLogin}">
        <fmt:message key="wrong.login"/>
    </c:if>
    <br>

    <label>
        <input name="password" type="password" placeholder="<fmt:message key="users.password"/>" required>
    </label>
    <c:if test="${not empty requestScope.wrongPassword}">
        <fmt:message key="wrong.password"/><br>
    </c:if><br>

    <input type="submit" value="<fmt:message key="login.button"/>"><br>
</form>
<br>
<c:out value="${requestScope.exceptionError}"/><br>
</body>
</html>