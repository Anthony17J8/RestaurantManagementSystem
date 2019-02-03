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
<form:form modelAttribute="restaurants">
    <c:forEach var="restaurant" items="${restaurants}">
        <td>Restaurant :</td>

        <td>${restaurant.name}</td>
        <br>
        <td>Details:</td>
        <c:set var="detail" value="${restaurant.restaurantDetail}"/>
        <tr>${detail.country}</tr>
        <tr>${detail.city}</tr>
        <tr>${detail.email}</tr>
        <tr>${detail.phone}</tr>
        <tr>${detail.street}</tr>
        <br><br>
    </c:forEach>
</form:form>
</body>

</html>