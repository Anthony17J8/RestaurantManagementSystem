<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnc" uri="http://icoltd.rvs.ru/functions" %>
<html>

<head>
    <title>Dishes</title>
</head>

<body>

<h1><c:out value="${menu.name}"/> <c:out value=" (${menu.date})"/></h1>
<h2>Dishes</h2>

<c:forEach var="dish" items="${dishes}">
    <ul>
        <li><c:out value="${dish.description}"/> : <c:out value="${dish.price}"/>
            <a href="${pageContext.request.contextPath}/restaurant/${menu.restaurant.id}/menu/${menu.id}/dish/${dish.id}/update">Update</a>
            <a href="${pageContext.request.contextPath}/restaurant/${menu.restaurant.id}/menu/${menu.id}/dish/${dish.id}/delete">Delete</a>
        </li>
    </ul>
</c:forEach>

<div>
    <a href="${pageContext.request.contextPath}/restaurant/${menu.restaurant.id}/menu/${menu.id}/dish/addNew">Add New</a>
</div>

<c:url value="/restaurant/${menu.restaurant.id}/menu/showAll" var="redirectLink"/>

<a href="${fn:escapeXml(redirectLink)}">View all menus of restaurant</a>
</body>

<jsp:include page="footer.jsp"/>
</html>