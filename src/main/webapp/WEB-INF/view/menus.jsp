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
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}<wj:locate path="css/font-awesome.css" relativeTo="META-INF/resources"/>'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
</head>

<body>

<jsp:include page="navbar.jsp"/>

<div class="container-fluid pb-5">
    <section id="restaurant-content">

        <div class="row m-0">
            <h1><c:out value="${restaurant.name}"/></h1>
        </div>

        <div class="row justify-content-around pt-3">

            <div class="shadow-form col-5 p-2">
                <div id="carouselControls" class="carousel slide mb-3" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img class="d-block w-100"
                                 src="https://images.unsplash.com/photo-1550966871-3ed3cdb5ed0c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80"
                                 alt="restaurant image"/>
                        </div>
                        <div class="carousel-item">
                            <img class="d-block w-100"
                                 src="https://images.unsplash.com/photo-1549488344-1f9b8d2bd1f3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80"
                                 alt="restaurant image"/>
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
                <div class="container pt-1">
                    <div class="row mt-3 justify-content-between">
                        <div class="col-4"><a id="show-reviews" class="btn btn-prm btn-sm">Show Reviews</a></div>
                        <div class="col-4"><a
                                href="${pageContext.request.contextPath}/restaurants/${restaurant.id}/reviews/new"
                                class="btn btn-warning btn-sm">New Review</a></div>
                    </div>
                </div>
            </div>
            <div class="col-7">
                <c:if test="${error != null}">
                    <div class="alert alert-danger" role="alert">${error}</div>
                </c:if>
                <c:if test="${success != null}">
                    <div class="alert alert-success" role="alert">${success}</div>
                </c:if>

                <div class="row justify-content-between m-0 mb-2">
                    <div class="col-auto mr-auto"><h2 class="pb-0">Menus</h2></div>
                    <div class="col-auto pr-0">
                        <sec:authorize access="hasRole('ADMIN')">

                            <c:url value="/restaurants/${restaurant.id}/menus/new" var="addNewMenu"/>
                            <a class="btn btn-prm" href="${fn:escapeXml(addNewMenu)}">New Menu</a>

                        </sec:authorize>
                    </div>
                </div>
                <table class="shadow-form table table-striped text-center">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Date</th>
                        <th scope="col">Votes</th>
                        <th scope="col"></th>

                        <sec:authorize access="hasRole('ADMIN')">
                            <th scope="col"></th>
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
                                <c:url var="deleteLink" value="/restaurants/${restaurant.id}/menus/${menu.id}/delete"/>

                                <c:url var="updateLink" value="/restaurants/${restaurant.id}/menus/${menu.id}/update"/>

                                <td>
                                    <a href="${fn:escapeXml(updateLink)}">Edit</a>
                                    |
                                    <a href="${fn:escapeXml(deleteLink)}">Delete</a>
                                </td>
                            </sec:authorize>
                            <td>
                                <form:form method="GET" action="/restaurants/${restaurant.id}/menus/${menu.id}/vote">
                                    <button class="btn btn-warning">Like!</button>
                                </form:form>
                            </td>
                        </tr>
                        </tbody>
                    </c:forEach>
                </table>
            </div>
            <div id='review' class="row w-100 mt-4 justify-content-start"></div>
        </div>
    </section>

    <script type='text/javascript'
            src='${pageContext.request.contextPath}<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
    <script type='text/javascript'
            src='${pageContext.request.contextPath}<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>
    <script type="text/javascript">
        $("#show-reviews").click(function () {
            let review = $("#review-list");
            if (review.length) {
                review.remove();
            } else {
                $.getJSON("${pageContext.request.contextPath}/restaurants/${restaurant.id}/reviews", function (data) {
                    let header = $("<div id='review-list' class='col-5 px-0'>")
                        .append($("<h2>Reviews</h2>"));
                    data.reviews.forEach(function (review) {
                        header.append($("<div class='card border-secondary mb-3'>")
                            .append($("<div class='card-header text-left'>")
                                .append(review.user.username + ": " + Date.now()))
                            .append($("<div class='card-body text-dark'>")
                                .append("<p class='card-text text-left'>")
                                .append(review.text)
                            )).appendTo("#review");
                    });
                });
            }
        });
    </script>
</body>
</html>
