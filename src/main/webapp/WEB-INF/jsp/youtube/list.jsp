<%--
  Created by IntelliJ IDEA.
  User: Senchenko Viktor
  Date: 19.09.2016
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="list.title"/></title>
</head>
<body>
<jsp:directive.include file="../header.jsp"/>
<h1><spring:message code="list.title"/></h1>

<form id="form">
    <input name="url" id="url" placeholder="<spring:message code="list.url"/>"/>
    <button id="send" type="submit"><spring:message code="list.submit"/></button>
</form>
<table id="table" style="display: none;">
    <thead>
    <tr>
        <th><spring:message code="list.table.title"/></th>
        <th><spring:message code="list.table.quality"/></th>
    </tr>
    </thead>
    <tbody></tbody>
    <tfoot>
    <tr>
        <th><spring:message code="list.table.title"/></th>
        <th><spring:message code="list.table.quality"/></th>
    </tr>
    </tfoot>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
    function send(e) {
        e.preventDefault();
        var data = {};
        data['url'] = $('#url').val();
        $.ajax({
            type: "POST",
            url: "",
            data: JSON.stringify(data),
            headers: {"${_csrf.headerName}": "${_csrf.token}"},
            beforeSend: function () {
                $("#table").hide();
                disable(true);
            },
            success: function (e) {
                fillTable(e);
                disable(false);
            },
            error: function () {
                disable(false);
            },
            dataType: "json",
            contentType: "application/json"
        });
    }
    function disable(yes) {
        if (!yes) {
            $('#send').prop('disabled', false);
            $('#url').prop('disabled', false);
        }
        else {
            $('#send').prop('disabled', true);
            $('#url').prop('disabled', true);
        }
    }
    function fillTable(data) {
        var body = "";
        for (i = 0; i < data.length; i++) {
            body += "<tr><th>" + data[i].title + "</th><td><ul>";
            for (j = 0; j < data[i].urls.length; j++) {
                body += "<li><a href=\"" + data[i].urls[j].url + "\">" + data[i].urls[j].type + "</a></li>";
            }
            body += "</ul></td></tr>";
        }
        $("#table tbody").html(body);
        $("#table").show();
    }
    $("#form").submit(send);
</script>
</body>
</html>
