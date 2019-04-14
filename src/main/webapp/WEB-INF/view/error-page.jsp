<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Error page</title>
</head>

<body>

<h3>${message}</h3>

<c:choose>
    <c:when test="${restaurantId == null}">
        <a href="${pageContext.request.contextPath}/restaurant/list">Back to main</a>
    </c:when>
    <c:otherwise>
        <c:url value="/restaurant/menus" var="redirectLink">
            <c:param name="restId" value="${restaurantId}"/>
        </c:url>
        <a href="${redirectLink}">Back to Menu details</a>
    </c:otherwise>
</c:choose>

</body>
</html>