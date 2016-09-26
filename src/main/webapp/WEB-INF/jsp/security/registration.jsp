<%--
  Created by IntelliJ IDEA.
  User: Senchenko Viktor
  Date: 19.09.2016
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="registration.title"/></title>
</head>
<body>
<jsp:directive.include file="../header.jsp"/>
<h1><spring:message code="registration.title"/></h1>

<form:form modelAttribute="user" method="post" action="">
    <input name="username" id="username" placeholder="<spring:message code="login.username"/>"/>
    <form:errors path="username"></form:errors>
    <input type="email" name="email" id="email" placeholder="<spring:message code="registration.email"/>"/>
    <form:errors path="email"></form:errors>
    <input type="password" name="password" id="password" placeholder="<spring:message code="login.password"/>"/>
    <form:errors path="password"></form:errors>
    <input type="password" name="confirmPassword" id="confirmPassword"
           placeholder="<spring:message code="registration.confirmPassword"/>"/>
    <form:errors path="confirmPassword"></form:errors>
    <input type="checkbox" name="registrationTerms" id="registrationTerms">
    <form:errors path="registrationTerms"></form:errors>
    <label><spring:message code="registration.registrationTerms"/>
        <a href="#" onclick="alert('<spring:message code="registration.terms"/>')">
            <spring:message code="registration.registrationTerms.terms"/></a></label>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button id="send" type="submit"><spring:message code="registration.submit"/></button>
</form:form>

<%--<a href="#" onclick="alert('<spring:message code="registration.terms"/>')">--%>
    <%--<spring:message code="registration.registrationTerms.terms"/></a>--%>
<a href="${pageContext.request.contextPath}/login"><spring:message code="login.submit"/></a>
</body>
</html>
