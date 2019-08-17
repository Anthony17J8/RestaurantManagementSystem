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
    <c:when test="${restaurantId == null}">
        <a href="${fn:escapeXml(pageContext.request.contextPath)}/restaurant/list">Back to main</a>
    </c:when>
    <c:otherwise>
        <c:url value="/restaurant/menus" var="redirectLink">
            <c:param name="restId" value="${restaurantId}"/>
        </c:url>
        <a href="${fn:escapeXml(redirectLink)}">Back to Menu list</a>
    </c:otherwise>
</c:choose>

</body>
</html>