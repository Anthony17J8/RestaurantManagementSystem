<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>

<head>
    <title>
        Dish form
    </title>
</head>

<body>

<c:choose>
    <c:when test="${title == 'NEW'}">
        <h3>Add Dish</h3>
    </c:when>
    <c:otherwise>
        <h3>Update Dish</h3>
    </c:otherwise>
</c:choose>

<hr>

<table>
    <tbody>

    <c:url var="saveLink" value="/dish/save">
        <c:param name="menuId" value="${menuId}"/>
    </c:url>

    <form:form action="${saveLink}" method="post" modelAttribute="dish">
        <form:hidden path="id"/>

        <tr>
            <td>Description:</td>
            <td><form:input path="description"/></td>
        </tr>

        <tr>
            <td>Price:</td>
            <td><form:input path="price"/></td>
        </tr>

        <sec:authorize access="hasRole('ADMIN')">
        <tr>
            <td><input type="submit" value="Save" class="save"/></td>
        </tr>
        </sec:authorize>

    </form:form>
    </tbody>
</table>
<br><br>

<c:url value="/menu/update" var="redirectLink">
    <c:param name="menuId" value="${menuId}"/>
</c:url>

<a href="${redirectLink}">Back to Menu</a>

</body>
</html>