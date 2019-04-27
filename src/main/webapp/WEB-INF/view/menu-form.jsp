<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>

<head>
    <title>
        Menu form
    </title>
</head>

<body>
<c:choose>
    <c:when test="${title == 'NEW'}">
        <h3>Add Menu</h3>
    </c:when>
    <c:otherwise>
        <h3>Update Menu</h3>
    </c:otherwise>
</c:choose>
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

        <c:if test="${title != 'NEW'}">

            <tr>
                <td><h4>List dishes:</h4></td>
            </tr>
            <c:forEach items="${menu.dishes}" varStatus="status" var="dish">

                <c:url value="/dish/update" var="updateLink">
                    <c:param name="dishId" value="${dish.id}"/>
                </c:url>
                <c:url value="/dish/delete" var="deleteLink">
                    <c:param name="dishId" value="${dish.id}"/>
                </c:url>

                <tr>
                    <td>Dish #${status.index + 1}</td>
                    <td>${dish.description}</td>
                    <td><a href="${updateLink}">Update</a></td>
                    <td><a href="${deleteLink}">Delete</a></td>
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
                <td>Total amount:</td>
                <td>${totalAmount}</td>
            </tr>
        </c:if>
        <tr>
            <td><input type="submit" value="Save" class="save"/></td>
        </tr>

        </tbody>
    </table>

</form:form>

<c:url value="/restaurant/menus" var="redirectLink">
    <c:param name="restId" value="${restaurantId}"/>
</c:url>

<p><a href="${redirectLink}">Back to menu list</a></p>

</body>
</html>