<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
            <td><form:errors path="name"/></td>
        </tr>

        <tr>
            <td>Date:</td>
            <td><form:input type="date" path="date"/></td>
            <td><form:errors path="date"/></td>
        </tr>

        <c:if test="${menu.id != null}">

            <tr>
                <td><h4>List dishes:</h4></td>
            </tr>
            <c:forEach items="${menu.dishes}" varStatus="status" var="dish">

                <tr>
                    <td>Dish #${status.index + 1}</td>
                    <td><c:out value="${dish.description}"/></td>

                    <sec:authorize access="hasRole('ADMIN')">

                        <c:url value="/dish/update" var="updateLink">
                            <c:param name="dishId" value="${dish.id}"/>
                        </c:url>
                        <c:url value="/dish/delete" var="deleteLink">
                            <c:param name="dishId" value="${dish.id}"/>
                        </c:url>

                        <td><a href="${fn:escapeXml(updateLink)}">Update</a></td>
                        <td><a href="${fn:escapeXml(deleteLink)}">Delete</a></td>

                    </sec:authorize>
                </tr>
            </c:forEach>

            <sec:authorize access="hasRole('ADMIN')">

                <c:url var="showFormLink" value="/dish/showFormForAdd">
                    <c:param name="menuId" value="${menu.id}"/>
                </c:url>
                <tr>
                    <td></td>
                    <td><a href="${fn:escapeXml(showFormLink)}">Add new dish</a></td>
                </tr>

            </sec:authorize>
            <tr>
                <td>Total amount:</td>
                <td><c:out value="${totalAmount}"/></td>
            </tr>
        </c:if>

        <sec:authorize access="hasRole('ADMIN')">
            <tr>
                <td><input type="submit" value="Save" class="save"/></td>
            </tr>
        </sec:authorize>

        </tbody>
    </table>

</form:form>

<c:url value="/restaurant/menus" var="redirectLink">
    <c:param name="restId" value="${restaurantId}"/>
</c:url>

<p><a href="${fn:escapeXml(redirectLink)}">Back to menu list</a></p>

</body>
</html>