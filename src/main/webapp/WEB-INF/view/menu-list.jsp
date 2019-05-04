<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Menus</title>
</head>

<body>

<h3>Menus</h3>
<hr>

<sec:authorize access="hasRole('ADMIN')">

<c:url value="/menu/showFormForAdd" var="formForAdd">
    <c:param name="restId" value="${restId}"/>
</c:url>
<a href="${formForAdd}">Add menu</a>

</sec:authorize>
<br><br>

<table border="1" cellpadding="20">
    <thead>
    <tr>
        <th>Name</th>
        <th>Date</th>
        <th>Votes</th>

        <sec:authorize access="hasRole('ADMIN')">
            <th>Action</th>
        </sec:authorize>
    </tr>
    </thead>
    <c:forEach var="menu" items="${menus}">
        <tbody>
        <tr>
            <c:url value="/menu/showDetails" var="menuDetailsLink">
                <c:param name="menuId" value="${menu.id}"/>
            </c:url>
            <td><a href="${menuDetailsLink}">${menu.name}</a></td>
            <td>${menu.date}</td>
            <td>${menu.voteCount}</td>

            <sec:authorize access="hasRole('ADMIN')">
                <c:url var="deleteLink" value="/menu/delete">
                    <c:param name="menuId" value="${menu.id}"/>
                </c:url>

                <c:url var="updateLink" value="/menu/update">
                    <c:param name="menuId" value="${menu.id}"/>
                </c:url>

                <td>
                    <a href="${updateLink}">Update</a>
                    |
                    <a href="${deleteLink}">Delete</a>
                </td>
            </sec:authorize>
        </tr>
        </tbody>
    </c:forEach>

</table>
<br><br>
<a href="${pageContext.request.contextPath}/restaurant/list">Back to Restaurant list</a>
</body>

</html>
