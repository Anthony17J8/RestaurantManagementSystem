<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnc" uri="http://icoltd.rvs.ru/functions" %>
<html>

<head>
    <title>Menus</title>
</head>

<body>

<h3><c:out value="${restaurant.name}"/> <br></h3>
<h4>Menus</h4>
<hr>

<sec:authorize access="hasRole('ADMIN')">

    <c:url value="/menu/showFormForAdd" var="formForAdd">
        <c:param name="restId" value="${restaurant.id}"/>
    </c:url>
    <a href="${fn:escapeXml(formForAdd)}">Add menu</a>

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
            <td><a href="${fn:escapeXml(menuDetailsLink)}"><c:out value="${menu.name}"/></a></td>
            <td><c:out value="${fnc:formatZonedDateTime(menu.date)}"/></td>
            <td><c:out value="${menu.voteCount}"/></td>

            <sec:authorize access="hasRole('ADMIN')">
                <c:url var="deleteLink" value="/menu/delete">
                    <c:param name="menuId" value="${menu.id}"/>
                </c:url>

                <c:url var="updateLink" value="/menu/update">
                    <c:param name="menuId" value="${menu.id}"/>
                </c:url>

                <td>
                    <a href="${fn:escapeXml(updateLink)}">Update</a>
                    |
                    <a href="${fn:escapeXml(deleteLink)}">Delete</a>
                </td>
            </sec:authorize>
        </tr>
        </tbody>
    </c:forEach>

</table>
<br/>
<a href="${fn:escapeXml(pageContext.request.contextPath)}/restaurant/list">View all restaurants</a>
</body>

<jsp:include page="footer.jsp"/>
</html>
