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

</body>
${menu.name}
<br>
<c:forEach var="dish" items="${menu.dishes}">
    <li>
            ${dish.name}
    </li>
</c:forEach>

Votes: ${menu.votes.size()}

</html>