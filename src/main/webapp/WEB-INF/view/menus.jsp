<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Menus</title>
</head>

<body>

<h3>Menus:</h3>

<table border="1" cellpadding="20">

    <th>Name</th>
    <th>Date</th>
    <th>Cast</th>

    <c:forEach var="menu" items="${menus}">
        <tr>
            <td>${menu.name}</td>
            <td>${menu.date}</td>
            <td>${menu.cast}</td>
        </tr>
    </c:forEach>

</table>

</body>

</html>
