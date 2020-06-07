<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnc" uri="http://icoltd.rvs.ru/functions" %>
<%@ taglib prefix="wj" uri="http://www.webjars.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dishes</title>
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link rel='stylesheet' href='<wj:locate path="css/font-awesome.css" relativeTo="META-INF/resources"/>'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
</head>

<body>
<jsp:include page="navbar.jsp"/>
<section id="restaurant-content">
    <div class="container-fluid pb-5">
        <h2><c:out value="${menu.name}"/></h2>
        <p><c:out value=" (${menu.date.toLocalDate()})"/></p>
        <div class="row">
            <div class="col-7">
                <div class="list-group pb-3" id="list-tab" role="tablist">
                    <c:forEach varStatus="index" var="dish" items="${dishes}">
                        <a class="list-group-item list-group-item-action" id="${index}" data-toggle="list"
                           href="#list-home" role="tab" aria-controls="home">${dish.description} - ${dish.price}$</a>
                    </c:forEach>
                </div>
                <a href="${pageContext.request.contextPath}/restaurants/${restaurantId}/menus/${menu.id}/dishes/new"
                   class="btn btn-prm">New</a>

                <c:url value="/restaurants/${restaurantId}/menus" var="redirectLink"/>
                <a class="btn btn-prm" href="${fn:escapeXml(redirectLink)}">Back</a>
            </div>
            <div class="col-5">
                <div class="tab-content" id="nav-tabContent">
                    <c:forEach varStatus="index" items="${dishes}" var="dish">
                        <div class="tab-pane fade" id="list-home" role="tabpanel" aria-labelledby="list-home-list">
                            <div class="container shadow-form">
                                <div class="row p-3">
                                    <img src="https://images.unsplash.com/photo-1550966871-3ed3cdb5ed0c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1050&q=80"
                                         alt="restaurant image"/>
                                </div>
                                <div class="row px-3 ">
                                    <p class="m-0"><c:out value="${dish.description}"/></p>
                                </div>
                                <div class="row py-3">
                                    <div class="col">
                                        <a class="btn btn-warning btn-sm"
                                           href="${pageContext.request.contextPath}/restaurants/${restaurantId}/menus/${menu.id}/dishes/${dish.id}/update"
                                           aria-hidden="true">Edit</a>
                                        <a class="btn btn-danger btn-sm"
                                           href="${pageContext.request.contextPath}/restaurants/${restaurantId}/menus/${menu.id}/dishes/${dish.id}/delete"
                                           aria-hidden="true">Delete</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
</section>
<script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
<script type='text/javascript' src='<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>
</body>

</html>