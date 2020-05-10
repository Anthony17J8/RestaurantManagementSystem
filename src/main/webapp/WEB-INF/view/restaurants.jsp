<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="wj" uri="http://www.webjars.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Restaurants</title>
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link rel='stylesheet' href='<wj:locate path="css/font-awesome.css" relativeTo="META-INF/resources"/>'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/hover.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
</head>
<body>

<jsp:include page="navbar.jsp"/>


<div class="container">

    <div class="jumbotron">
        <h1 class="text-center">Restaurant Vote Service</h1>
        <hr class="my-4">
        <p class="lead text-center">
            This app will allow you to choose the tastiest menu from existing restaurants. Vote for menu and book a
            table. It's up to where will you have a meal!
        </p>
    </div>

    <sec:authorize access="hasRole('ADMIN')">
        <a class="btn btn-info confirm mt-4 mb-3" href="${pageContext.request.contextPath}/restaurants/new">Add
            Restaurant</a>
    </sec:authorize>

    <div class="row justify-content-between">
        <c:forEach var="restaurant" items="${restaurants}">
            <div class="col-lg-4">
                <div class="card">
                    <img src="" class="card-img-top" alt="">
                    <div class="card-body">
                        <h5 class="card-title"><c:out value="${restaurant.name}"/></h5>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Url: <c:out value="${restaurant.restaurantDetail.url}"/></li>
                        <li class="list-group-item">Country: <c:out value="${restaurant.restaurantDetail.country}"/></li>
                        <li class="list-group-item">City: <c:out value="${restaurant.restaurantDetail.city}"/></li>
                        <li class="list-group-item">Address: <c:out value="${restaurant.restaurantDetail.street}"/></li>
                        <li class="list-group-item">Phone: <c:out value="${restaurant.restaurantDetail.phoneNumber}"/></li>
                    </ul>
                </div>
            </div>
        </c:forEach>
    </div>
<%--  --%>
<%--            <c:url var="menuList" value="/restaurants/${restaurant.id}/menus"/>--%>
<%--            <a href="${fn:escapeXml(menuList)}">View menus</a>--%>

<%--        <sec:authorize access="hasRole('ADMIN')">--%>

<%--        <td>--%>
<%--            <c:url var="updateLink" value="/restaurants/${restaurant.id}/update"/>--%>

<%--            <c:url var="deleteLink" value="/restaurants/${restaurant.id}/delete"/>--%>

<%--            <a href="${fn:escapeXml(updateLink)}">Update</a>--%>
<%--            |--%>
<%--            <a href="${fn:escapeXml(deleteLink)}">Delete</a>--%>
<%--        </td>--%>
<%--        </sec:authorize>--%>
<%--            <c:url var="showReviewLink" value="/restaurants/${restaurant.id}/reviews"/>--%>
<%--            <a href="${fn:escapeXml(showReviewLink)}">Show reviews</a>--%>
</div>

<script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
<script type='text/javascript'
        src='<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>

<%--<script type="text/javascript"--%>
<%--        src="${pageContext.request.contextPath}/resources/assets/js/form.js"></script>--%>
</body>
</html>