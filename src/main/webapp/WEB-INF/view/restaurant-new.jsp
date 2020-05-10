<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="wj" uri="http://www.webjars.org/tags" %>
<!DOCTYPE html>
<html>

<head>
    <title>Restaurant form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link rel='stylesheet' href='<wj:locate path="css/font-awesome.css" relativeTo="META-INF/resources"/>'>
</head>

<body>

<div class="container">
    <section id="main">
        <form:form action="${pageContext.request.contextPath}/restaurants" method="POST"
                   modelAttribute="restaurant">

            <div class="row">
                <div class="shadow-form col-6 offset-3 px-0">
                    <div id="title" class="row m-0 px-0 py-3">
                        <h1 class="text-center m-0">
                            <c:choose>
                                <c:when test="${restaurant.id == null}">
                                    Adding New Restaurant
                                </c:when>
                                <c:otherwise>
                                    Updating Restaurant
                                </c:otherwise>
                            </c:choose>
                        </h1>
                    </div>

                    <form:hidden path="id"/>

                    <div id="form-content" class="px-5 py-3">
                        <div class="form-group">
                            <label for="rName">Restaurant Name</label>
                            <form:input path="name" id="rName" cssClass="form-control"/>
                            <form:errors path="name"/>
                        </div>

                        <div class="form-group">
                            <label for="url">Url</label>
                            <form:input path="restaurantDetail.url" id="url" cssClass="form-control"/>
                            <form:errors path="restaurantDetail.url"/>
                        </div>

                        <div class="form-group">
                            <label for="country">Country</label>
                            <form:input path="restaurantDetail.country" id="country" cssClass="form-control"/>
                            <form:errors path="restaurantDetail.country"/>
                        </div>

                        <div class="form-group">
                            <label for="city">City</label>
                            <form:input path="restaurantDetail.city" id="city" cssClass="form-control"/>
                            <form:errors path="restaurantDetail.city"/>
                        </div>

                        <div class="form-group">
                            <label for="street">Street</label>
                            <form:input path="restaurantDetail.street" id="street" cssClass="form-control"/>
                            <form:errors path="restaurantDetail.street"/>
                        </div>

                        <div class="form-group">
                            <label for="phoneNum">Phone Number</label>
                            <form:input path="restaurantDetail.phoneNumber" id="phoneNum" cssClass="form-control"/>
                            <form:errors path="restaurantDetail.phoneNumber"/>
                        </div>

                        <div class="row">
                            <div class="col">
                                <button class="btn btn-block" type="submit">Save</button>
                            </div>
                            <div class="col">
                                <button id="btn-cancel" class="btn btn-block" onclick="window.history.back()">Cancel
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </section>
</div>

<script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
<script type='text/javascript' src='<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/assets/js/form.js"></script>
</body>
</html>