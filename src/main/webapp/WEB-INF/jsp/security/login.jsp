<%--
  Created by IntelliJ IDEA.
  User: Senchenko Viktor
  Date: 19.09.2016
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="login.title"/></title>
</head>
<body>
<jsp:directive.include file="../header.jsp"/>
<h1><spring:message code="login.title"/></h1>
<c:if test="${!isLogedIn}">
    <form method="post" action="">
        <input name="username" id="username" placeholder="<spring:message code="login.username"/>"/>
        <input type="password" name="password" id="password" placeholder="<spring:message code="login.password"/>"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button id="send" type="submit"><spring:message code="login.submit"/></button>
    </form>
    <%--<jsp:directive.include file="../social.jsp"/>--%>
    <a href="${pageContext.request.contextPath}/registration"><spring:message code="registration.title"/></a>
</c:if>
</body>
</html>
