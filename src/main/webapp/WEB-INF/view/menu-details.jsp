<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Menu Details</title>
</head>

<body>

<h3>${restaurant.name}. <br>Menu Details</h3>
<hr>

${menu.name}
<br>
<c:forEach var="dish" items="${menu.dishes}">
    <ul>
        <li> ${dish.description} : ${dish.price}</li>
    </ul>
</c:forEach>
<p>Total amount: ${totalAmount}</p>

<c:url value="/menu/addVote" var="voteLink">
    <c:param name="menuId" value="${menu.id}"/>
</c:url>

<form:form method="post" action="${voteLink}">
    <input type="submit" value="Vote">
</form:form>
<br/>

<c:url value="/restaurant/menus" var="redirectLink">
    <c:param name="restId" value="${restaurant.id}"/>
</c:url>

<a href="${redirectLink}">View menu list of restaurant</a>
</body>

<jsp:include page="footer.jsp"/>
</html>