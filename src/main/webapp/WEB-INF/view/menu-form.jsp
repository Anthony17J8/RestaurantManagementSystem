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

<c:url var="save" value="/menu/save">
    <c:param name="restId" value="${restaurantId}"/>
</c:url>

<form:form action="${save}" method="post" modelAttribute="menu">

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
            <td><h4>List dishes:</h4></td>
        </tr>
        <c:forEach items="${menu.dishes}" varStatus="status" var="dish">
            <tr>
                <td>Dish #${status.index}</td>
                <td>${dish.description}</td>
                <td><a href="${pageContext.request.contextPath}/dish/update">Update</a></td>
                <td><a href="${pageContext.request.contextPath}/dish/delete}">Delete</a></td>
            </tr>
        </c:forEach>

        <c:url var="showFormLink" value="/dish/showFormForAdd">
            <c:param name="menuId" value="${menu.id}"/>
        </c:url>
        <tr>
            <td></td>
            <td><a href="${showFormLink}">Add new dish</a></td>
        </tr>

        <tr>
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