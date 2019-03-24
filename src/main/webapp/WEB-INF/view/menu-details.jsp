<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Menu Details</title>
</head>

<body>

<h3>Menu Details</h3>
<hr>

${menu.name}
<br>
<c:forEach var="dish" items="${menu.dishes}">
    <li>
            ${dish.description}
    </li>
</c:forEach>

<form:form method="post" modelAttribute="menu" action="${pageContext.request.contextPath}/menu/${menu.id}/addVote">
    <form:hidden path="id"/>
    <input type="submit" value="Vote">
</form:form>
<br><br>

<a href="${pageContext.request.contextPath}/restaurant/${menu.restaurant.id}/menus">Back to Menu list</a>

</body>
</html>