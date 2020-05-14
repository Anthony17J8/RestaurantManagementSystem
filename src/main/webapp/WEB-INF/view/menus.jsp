<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnc" uri="http://icoltd.rvs.ru/functions" %>
<%@ taglib prefix="wj" uri="http://www.webjars.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menus</title>
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link rel='stylesheet' href='<wj:locate path="css/font-awesome.css" relativeTo="META-INF/resources"/>'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/hover.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
</head>

<body>

<jsp:include page="navbar.jsp"/>

<div class="container-fluid">
    <section id="restaurant-content">

        <div class="row m-0">
            <h1><c:out value="${restaurant.name}"/></h1>
        </div>

        <div class="row justify-content-between pt-3">

            <div class="col-5">
                <div id="carouselControls" class="carousel slide mb-3" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img class="d-block w-100"
                                 src="https://images.unsplash.com/photo-1550966871-3ed3cdb5ed0c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80"
                                 alt=""/>
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100"
                                 src="https://images.unsplash.com/photo-1549488344-1f9b8d2bd1f3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80"
                                 alt=""/>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselControls" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselControls" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
                <p class="text-left"><c:out value="${restaurant.description}"/></p>
            </div>
            <div class="col-7">
                <div class="row justify-content-between m-0 mb-2">
                    <div class="col-auto mr-auto"><h2 class="pb-0">Menus</h2></div>
                    <div class="col-auto pr-0">
                        <sec:authorize access="hasRole('ADMIN')">

                            <c:url value="/restaurants/${restaurant.id}/menus/new" var="addNewMenu"/>
                            <a  class="btn btn-prm" href="${fn:escapeXml(addNewMenu)}">New Menu</a>

                        </sec:authorize>
                    </div>
                </div>
                <table class="shadow-form table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Date</th>
                        <th scope="col">Votes</th>

                        <sec:authorize access="hasRole('ADMIN')">
                            <th scope="col">Action</th>
                        </sec:authorize>
                    </tr>
                    </thead>
                    <c:forEach var="menu" items="${menus}" varStatus="logStatus">
                        <tbody>
                        <tr>
                            <th scope="row">${logStatus.index + 1}</th>

                            <c:url value="/restaurants/${restaurant.id}/menus/${menu.id}/dishes" var="menuDishes"/>
                            <td><a href="${fn:escapeXml(menuDishes)}"><c:out value="${menu.name}"/></a></td>
                            <td><c:out value="${menu.date.toLocalDate()}"/></td>

                            <td><c:out value="${menu.votesAmount}"/></td>

                            <sec:authorize access="hasRole('ADMIN')">
                                <c:url var="deleteLink" value="/restaurant/${restaurant.id}/menu/${menu.id}/delete"/>

                                <c:url var="updateLink" value="/restaurant/${restaurant.id}/menu/${menu.id}/update"/>

                                <td>
                                    <a href="${fn:escapeXml(updateLink)}">Update</a>
                                    |
                                    <a href="${fn:escapeXml(deleteLink)}">Delete</a>
                                </td>
                            </sec:authorize>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </div>
        </div>
    </section>
</div>

<script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
<script type='text/javascript'
        src='<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>
</body>
</html>
