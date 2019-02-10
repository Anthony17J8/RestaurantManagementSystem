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

<table border="1" cellpadding="20">
    <thead>
    <tr>
        <th align="left">Restaurant</th>
        <th align="left">Details</th>
        <th align="left">Menu</th>
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
                        ${detail.street}</td>
                <td><a href="${pageContext.request.contextPath}/restaurant/${restaurant.id}/showMenus">Show menu</a>
                </td>
            </tr>
            </tbody>

        </c:forEach>
    </form:form>
</table>
</body>

</html>