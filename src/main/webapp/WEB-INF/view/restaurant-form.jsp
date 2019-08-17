<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <title>
        Restaurant form
    </title>
</head>

<body>

<c:choose>
    <c:when test="${restaurant.id == null}">
        <h3>Add Restaurant</h3>
    </c:when>
    <c:otherwise>
        <h3>Update Restaurant</h3>
    </c:otherwise>
</c:choose>

<hr>

<form:form action="save" method="post" modelAttribute="restaurant">
    <form:hidden path="id"/>
    <table>
        <tbody>
        <tr>
            <td>Name (*):</td>
            <td><form:input path="name"/></td>
            <td><form:errors path="name"/></td>
        </tr>
        <form:form action="save" method="post" modelAttribute="detail">
            <form:hidden path="detailId"/>
            <tr>
                <td>Country (*):</td>
                <td><form:input path="country"/></td>
                <td><form:errors path="country"/></td>
            </tr>

            <tr>
                <td>City (*):</td>
                <td><form:input path="city"/></td>
                <td><form:errors path="city"/></td>
            </tr>

            <tr>
                <td>Street (*):</td>
                <td><form:input path="street"/></td>
                <td><form:errors path="street"/></td>
            </tr>

            <tr>
                <td>Phone (*):</td>
                <td><form:input path="phone"/></td>
                <td><form:errors path="phone"/></td>
            </tr>

            <td>Email (*):</td>
            <td><form:input path="site"/></td>
            <td><form:errors path="site"/></td>

        </form:form>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save"/></td>
        </tr>

        </tbody>
    </table>

</form:form>

<p><a href="${fn:escapeXml(pageContext.request.contextPath)}/restaurant/list">Back to restaurant list</a></p>

</body>
</html>