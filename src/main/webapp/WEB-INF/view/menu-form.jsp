<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <title>
        Menu form
    </title>
</head>

<body>
<c:choose>
    <c:when test="${menu.id == null}">
        <h3>Add Menu</h3>
    </c:when>
    <c:otherwise>
        <h3>Update Menu</h3>
    </c:otherwise>
</c:choose>
<hr>

<c:url var="save" value="/restaurant/${restaurant.id}/menu/save"/>

<form:form action="${save}" method="post" modelAttribute="menu">

    <form:hidden path="id"/>
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name"/></td>
        </tr>

        <tr>
            <td>Date:</td>
            <td><form:input type="date" path="date"/></td>
            <td><form:errors path="date"/></td>
        </tr>
        <sec:authorize access="hasRole('ADMIN')">
            <tr>
                <td>
                    <button type="submit">Save</button>
                    <button onclick="window.history.back()" type="button">Cancel</button>
                </td>
            </tr>
        </sec:authorize>

        </tbody>
    </table>

</form:form>

</body>
</html>