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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container">

    <div class="jumbotron py-5">
        <h1 class="text-center">Restaurant Vote App</h1>
        <hr class="my-4">
        <p class="lead text-center">
            This app will allow you to choose the tastiest menu from existing restaurants. Vote for menu and book a
            table. It's up to you where will you have a meal!
        </p>
    </div>

    <sec:authorize access="hasRole('ADMIN')">
        <a class="btn btn-prm mt-4 mb-3" href="${pageContext.request.contextPath}/restaurants/new">Add
            Restaurant</a>
    </sec:authorize>

    <div class="row row-cols-1 row-cols-md-3">
        <c:forEach var="restaurant" items="${restaurants}">
            <div class="col mb-4">
                <div class="card h-100">
                    <img src="" class="card-img-top" alt="">
                    <div class="card-body">
                        <div class="row px-0">
                            <div class="col-9 pr-0">
                                <h5 class="card-title">
                                    <a href="${pageContext.request.contextPath}/restaurants/${restaurant.id}/menus">
                                        <c:out value="${restaurant.name}"/>
                                    </a>
                                </h5>
                            </div>
                            <div class="col pl-0 text-right">
                                <a class="fa fa-pencil"
                                   href="${pageContext.request.contextPath}/restaurants/${restaurant.id}/update"
                                   aria-hidden="true"></a>
                                <a class="fa fa-trash"
                                   href="${pageContext.request.contextPath}/restaurants/${restaurant.id}/delete"
                                   aria-hidden="true"></a>
                            </div>
                        </div>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">Url: <c:out value="${restaurant.restaurantDetail.url}"/></li>
                        <li class="list-group-item">Country: <c:out
                                value="${restaurant.restaurantDetail.country}"/></li>
                        <li class="list-group-item">City: <c:out value="${restaurant.restaurantDetail.city}"/></li>
                        <li class="list-group-item">Address: <c:out value="${restaurant.restaurantDetail.street}"/></li>
                        <li class="list-group-item">Phone: <c:out
                                value="${restaurant.restaurantDetail.phoneNumber}"/></li>
                    </ul>
                    <div class="d-flex align-items-center card-body">
                        <a href="${pageContext.request.contextPath}/restaurants/${restaurant.id}/menus"
                           class="btn btn-prm">More info</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
<script type='text/javascript'
        src='<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>
</body>
</html>