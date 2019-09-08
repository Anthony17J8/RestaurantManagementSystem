<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fnc" uri="http://icoltd.rvs.ru/functions" %>
<html>

<head>
    <title>Reviews</title>
</head>

<body>

<h3><c:out value="${restaurant.name}"/> <br></h3>
<h4>Reviews</h4>

<hr>

<table border="1" cellpadding="20">
    <thead>
    <tr>
        <th>Username</th>
        <th>Date of review</th>
        <th>Text</th>
    </tr>
    <c:forEach items="${reviews}" var="review">
        <tr>
            <th>${fn:escapeXml(review.user.userName)}</th>
            <th>${fn:escapeXml(fnc:formatLocalDateTime(review.createdAt))}</th>
            <th>${fn:escapeXml(review.text)}</th>
        </tr>
    </c:forEach>
    </thead>
</table>

<c:url var="save" value="/review/save">
    <c:param name="restId" value="${restaurant.id}"/>
</c:url>
<br><br>
<form:form action="${save}" method="post" modelAttribute="newReview">
    <form:hidden path="id"/>

    <form:textarea path="text"/>

    <button type="submit">Save</button>
</form:form>
<br/>
<a href="${fn:escapeXml(pageContext.request.contextPath)}/restaurant/list">View all restaurants</a>
</body>

<jsp:include page="footer.jsp"/>
</html>
