<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Menus</title>
</head>

<body>

<h3>Menus</h3>
<hr>

<input type="button" value="Add Menu"
       onclick="window.location.href='showAddMenuForm'; return false"/>
<br><br>

<table border="1" cellpadding="20">
    <thead>
    <tr>
        <th>Name</th>
        <th>Date</th>
        <th>Cast</th>
        <th>Votes</th>
        <th>Action</th>
    </tr>
    </thead>
    <c:forEach var="menu" items="${menus}">
        <tbody>
        <tr>
            <td><a href="${pageContext.request.contextPath}/menu/${menu.id}/showDetails">${menu.name}</a></td>
            <td>${menu.date.toLocalDate()} ${menu.date.toLocalTime()}</td>
            <td>${menu.cost}</td>
            <td>${menu.voteCount}</td>

            <c:url var="deleteLink" value="/menu/delete">
                <c:param name="menuId" value="${menu.id}"/>
            </c:url>

           <%-- <c:url var="updateLink" value="/menu/update">
                <c:param name="menuId" value="${menu.id}"/>
            </c:url>--%>

            <td>
                <%--<a href="${updateLink}">Update</a>--%>
                <%--|--%>
                <a href="${deleteLink}">Delete</a>
            </td>
        </tr>
        </tbody>
    </c:forEach>

</table>
<br><br>
<a href="${pageContext.request.contextPath}/restaurant/list">Back to Restaurant list</a>
</body>

</html>
