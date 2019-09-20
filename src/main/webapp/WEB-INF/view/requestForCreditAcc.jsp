
<%--
  Created by IntelliJ IDEA.
  User: negat
  Date: 04/09/2019
  Time: 00:58
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
<form method="post" action="userpage/credit_accoun_request">
    <input name="command" type="hidden" value="creditAccoutRequest">
    <c:out value="Your current credit raiting is ${sessionScope.user.}"/>
    <input name="">
</form>

</body>
</html>
