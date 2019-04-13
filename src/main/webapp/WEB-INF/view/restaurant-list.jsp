<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>

    <title>
        List Restaurants
    </title>

</head>
<body>

<h3>Restaurant List</h3>
<hr>

<input type="button" value="Add Restaurant"
       onclick="window.location.href='showFormForAdd'; return false"/>
<br><br>

<table border="1" cellpadding="20">
    <thead>
    <tr>
        <th>Restaurant</th>
        <th>Details</th>
        <th>Menu</th>
        <th>Action</th>
    </tr>

    </thead>
    <form:form modelAttribute="restaurants">
        <c:forEach var="restaurant" items="${restaurants}">

            <c:set var="detail" value="${restaurant.restaurantDetail}"/>
            <tbody>
            <tr>
                <td>${restaurant.name}</td>
                <td>    ${detail.country}<br>
                        ${detail.city}<br>
                        ${detail.email}<br>
                        ${detail.phone}<br>
                        ${detail.street}
                </td>
                <td>
                    <c:url var="menuList" value="/restaurant/menus">
                        <c:param name="restId" value="${restaurant.id}"/>
                    </c:url>
                    <a href="${menuList}">Show menu</a>
                </td>
                <td>
                    <c:url var="updateLink" value="/restaurant/update">
                        <c:param name="restId" value="${restaurant.id}"/>
                    </c:url>

                    <c:url var="deleteLink" value="/restaurant/delete">
                        <c:param name="restId" value="${restaurant.id}"/>
                    </c:url>

                    <a href="${updateLink}">Update</a>
                    |
                    <a href="${deleteLink}">Delete</a>
                </td>
            </tr>
            </tbody>

        </c:forEach>
    </form:form>
</table>
</body>

</html>