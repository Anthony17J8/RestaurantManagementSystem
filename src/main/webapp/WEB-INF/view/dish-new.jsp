<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="wj" uri="http://www.webjars.org/tags" %>
<!DOCTYPE html>
<html>

<head>
    <title>Dish form</title>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}<wj:locate path="css/font-awesome.css" relativeTo="META-INF/resources"/>'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
</head>

<body>
<jsp:include page="navbar.jsp"/>

<div class="container">
    <c:url var="saveLink" value="/restaurants/${restaurantId}/menus/${menu.id}/dishes"/>
    <form:form action="${saveLink}" method="POST" modelAttribute="dish">

    <div class="row pt-5 mt-5">
        <div class="shadow-form col-6 offset-3 px-0">
            <div id="title" class="row m-0 px-0 py-3">
                <h1 class="text-center m-0">
                    <c:choose>
                        <c:when test="${dish.id == null}">
                            Add Dish
                        </c:when>
                        <c:otherwise>
                            Edit Dish
                        </c:otherwise>
                    </c:choose>
                </h1>
            </div>

            <form:hidden path="id"/>

            <div id="form-content" class="px-5 py-4">
                <div class="form-group">
                    <label for="dDesc">Description</label>
                    <form:input path="description" id="dDesc" cssClass="form-control"/>
                    <form:errors path="description"/>
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <form:input type="number" path="price" cssClass="form-control"/>
                    <form:errors path="price"/>
                </div>

                <div class="row">
                    <sec:authorize access="hasRole('ADMIN')">
                        <div class="col">
                            <button class="btn btn-block btn-prm" type="submit">Save</button>
                        </div>
                    </sec:authorize>
                    <div class="col">
                        <button id="btn-cancel" class="btn btn-block" onclick="window.history.back()">Cancel
                        </button>
                    </div>
                </div>

            </div>
            </form:form>
        </div>
    </div>
</div>
<script type='text/javascript' src='${pageContext.request.contextPath}<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/assets/js/form.js"></script>
</body>

</html>