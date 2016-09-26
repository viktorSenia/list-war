<%--
  Created by IntelliJ IDEA.
  User: Senchenko Viktor
  Date: 21.09.2016
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${isLogedIn}">
    <form method="post" action="${pageContext.request.contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button><spring:message code="login.logout"/></button>
    </form>
</c:if>
<ul>
    <li><a href="?lang=en">English</a></li>
    <li><a href="?lang=ru">Русский</a></li>
</ul>