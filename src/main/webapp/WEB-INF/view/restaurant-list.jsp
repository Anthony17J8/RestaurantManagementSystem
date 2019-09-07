<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <th>Reviews</th>
    </tr>

    </thead>
    <form:form modelAttribute="restaurants">
        <c:forEach var="restaurant" items="${restaurants}">

            <c:set var="detail" value="${restaurant.restaurantDetail}"/>
            <tbody>
            <tr>
                <td><c:out value="${restaurant.name}"/></td>
                <td><c:out value="${detail.country}"/><br>
                    <c:out value="${detail.city}"/><br>
                    <c:out value="${detail.site}"/><br>
                    <c:out value="${detail.phone}"/><br>
                    <c:out value="${detail.street}"/>
                </td>
                <td>
                    <c:url var="menuList" value="/restaurant/menus">
                        <c:param name="restId" value="${restaurant.id}"/>
                    </c:url>
                    <a href="${fn:escapeXml(menuList)}">View menus</a>
                </td>

                <sec:authorize access="hasRole('ADMIN')">

                    <td>
                        <c:url var="updateLink" value="/restaurant/update">
                            <c:param name="restId" value="${restaurant.id}"/>
                        </c:url>

                        <c:url var="deleteLink" value="/restaurant/delete">
                            <c:param name="restId" value="${restaurant.id}"/>
                        </c:url>

                        <a href="${fn:escapeXml(updateLink)}">Update</a>
                        |
                        <a href="${fn:escapeXml(deleteLink)}">Delete</a>
                    </td>

                    <td>
                        <c:url var="showReviewLink" value="/restaurant/reviews">
                            <c:param name="restId" value="${restaurant.id}"/>
                        </c:url>
                        <a href="${fn:escapeXml(showReviewLink)}">Show reviews</a>
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