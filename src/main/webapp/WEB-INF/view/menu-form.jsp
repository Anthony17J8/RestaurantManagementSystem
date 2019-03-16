<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>

<head>
    <title>
        Add Menu
    </title>
</head>

<body>

<h3>Add Menu</h3>
<hr>

<c:url var="addMenu" value="/menu/addMenu">
    <c:param name="restId" value="${restaurantId}"/>
</c:url>

<form:form action="${addMenu}" method="post" modelAttribute="menu">

    <form:hidden path="id"/>
    <table>
        <tbody>
        <tr>
            <td>Name:</td>
            <td><form:input path="name"/></td>
        </tr>

        <tr>
            <td>Date:</td>
            <td><form:input type="date" path="date"/></td>
        </tr>

        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save" class="save"/></td>
        </tr>

        </tbody>
    </table>

</form:form>
<hr>
<p>Total cost: </p>

<p><a href="${pageContext.request.contextPath}/restaurant/${restaurantId}/menus">Back to restaurant list</a></p>

</body>
</html>