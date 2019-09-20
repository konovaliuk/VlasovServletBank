<%--
  Created by IntelliJ IDEA.
  User: negat
  Date: 17/09/2019
  Time: 13:32
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
<fmt:setBundle basename="pages/addmoney"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/userpage/refill">
    <input type="text" name="summ" placeholder="<fmt:message key="summ"/>" pattern="^[0-9]*[.,]?[0-9]+$" required>
    <fmt:message key="positive.number"/><br>
    <input type="submit" value="<fmt:message key="button.refill"/> ">
</form>
<c:out value="${requestScope.exception}"/>
</body>
</html>
