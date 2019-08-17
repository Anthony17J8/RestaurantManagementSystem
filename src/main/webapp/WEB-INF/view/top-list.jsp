<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://icoltd.rvs.ru/functions" %>
<html>

<head>
    <title>Menus</title>
</head>

<body>

<h3>Top Menu List</h3>
<hr>

<form:form method="get" action="${pageContext.request.contextPath}/menu/toplist">
    <dl>
        <dd><input type="date" name="startDate" value="${param.startDate}"/></dd>
    </dl>
    <dl>
        <dd><input type="date" name="endDate" value="${param.endDate}"/></dd>
    </dl>
    <button type="submit">Search</button>
</form:form>
<br/>

<table border="1" cellpadding="20">
    <thead>
    <tr>
        <th>Restaurant</th>
        <th>Name</th>
        <th>Date</th>
        <th>Votes</th>
    </tr>
    </thead>
    <c:forEach var="menu" items="${menus}">
        <tbody>
        <tr>
            <td>${menu.restaurant.name}</td>
            <c:url value="/menu/showDetails" var="menuDetailsLink">
                <c:param name="menuId" value="${menu.id}"/>
            </c:url>
            <td><a href="${menuDetailsLink}">${menu.name}</a></td>
            <td>${fn:formatZonedDateTime(menu.date)}</td>
            <td>${menu.voteCount}</td>

        </tr>
        </tbody>
    </c:forEach>
</table>
<br/>
<a href="${pageContext.request.contextPath}/restaurant/list">Restaurant List</a>
<br/><br/>

<form:form method="post" action="${pageContext.request.contextPath}/logout">
    <input type="submit" value="Logout">
</form:form>

</body>

</html>
