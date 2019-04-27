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

<c:url value="/menu/addVote" var="voteLink">
    <c:param name="menuId" value="${menu.id}"/>
</c:url>

<form:form method="post" action="${voteLink}">
    <input type="submit" value="Vote">
</form:form>
<br><br>

<c:url value="/restaurant/menus" var="redirectLink">
    <c:param name="restId" value="${restaurantId}"/>
</c:url>

<a href="${redirectLink}">Back to Menu list</a>

</body>
</html>