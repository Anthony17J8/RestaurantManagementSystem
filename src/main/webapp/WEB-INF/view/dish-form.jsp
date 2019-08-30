<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <title>
        Dish form
    </title>
</head>

<body>

<c:choose>
    <c:when test="${dish.id == null}">
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
            <td><form:errors path="description"/></td>
        </tr>

        <tr>
            <td>Price:</td>
            <td><form:input path="price"/></td>
            <td><form:errors path="price"/></td>
        </tr>

        <sec:authorize access="hasRole('ADMIN')">
            <tr>
                <td>
                    <button type="submit">Save</button>
                    <button onclick="window.history.back()" type="button">Cancel</button>
                </td>
            </tr>
        </sec:authorize>

    </form:form>
    </tbody>
</table>

</body>
</html>