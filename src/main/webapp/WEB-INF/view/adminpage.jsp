<%--
  Created by IntelliJ IDEA.
  User: negat
  Date: 21/08/2019
  Time: 22:59
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
<fmt:setBundle basename="pages/adminpage"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<!--зафигачить useBeanБ взяв имя фамилию юзера из сессии для приветствия-->
<!--setProperty, getProperty и т.д.-->
<!--jstl fmt - идеален для локализации (Надписи, кнопки все остальное)!!!!!
экзепшен будет с кодом. код, - в проперти файле и перебрасывается из джава кода сам КОД, а мессага будет подби\раться из проперти файла-->
<fmt:message key="greeting"/>
<c:out value="${sessionScope.user.userFirstname} ${sessionScope.user.userLastname}))"/><br>

<c:forEach items="${sessionScope.allUserRequests}" var="request">
    <c:out value="${request}"/>
    <form method="get" action="/adminpage/confirmrequest">
        <input type="hidden" value="${request.requestID}"/>
        <input type="submit" value="confirm request">
    </form>

    <form method="get" action="/adminpage/deleterequest">
        <input type="hidden" value="${request.requestID}"/>
        <input type="submit" value="delete request">
    </form>
    <br>
</c:forEach>
<br>
<br>
<br>
<form method="get" action="/adminpage/newuserpage">
    <input type="submit" value="<fmt:message key="newUser.button" />">
</form>


<form action="logout" method="get">
    <input type="submit" value="<fmt:message key="logout.button"/>"/>
</form>
</body>
</html>
