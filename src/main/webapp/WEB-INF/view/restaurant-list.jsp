<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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

<sec:authorize access="hasRole('ADMIN')">

    <input type="button" value="Add Restaurant"
           onclick="window.location.href='showFormForAdd'; return false"/>

</sec:authorize>
<br><br>

<table border="1" cellpadding="20">
    <thead>
    <tr>
        <th>Restaurant</th>
        <th>Details</th>
        <th>Menu</th>
        <sec:authorize access="hasRole('ADMIN')">
            <th>Action</th>
        </sec:authorize>
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
                        ${detail.site}<br>
                        ${detail.phone}<br>
                        ${detail.street}
                </td>
                <td>
                    <c:url var="menuList" value="/restaurant/menus">
                        <c:param name="restId" value="${restaurant.id}"/>
                    </c:url>
                    <a href="${menuList}">View menus</a>
                </td>

                <sec:authorize access="hasRole('ADMIN')">

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

                </sec:authorize>

            </tr>
            </tbody>

        </c:forEach>
    </form:form>
</table>
</body>

<jsp:include page="footer.jsp"/>
</html>