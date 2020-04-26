<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <title>Error page</title>
</head>

<body>

<h3><c:out value="${message}"/></h3>

<c:choose>
    <c:when test="${restaurant.id == null}">
        <a href="${fn:escapeXml(pageContext.request.contextPath)}/restaurant/showAll">
            Go Home page
        </a>
    </c:when>
    <c:otherwise>
        <c:url value="/restaurant/${restaurant.id}/menu/showAll" var="redirectLink"/>
        <a href="${fn:escapeXml(redirectLink)}">Back to menu list of restaurant</a>
    </c:otherwise>
</c:choose>

</body>
</html>