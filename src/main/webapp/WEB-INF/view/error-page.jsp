<!doctype html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Error page</title>
</head>

<body>

<h3>${message}</h3>

<a href="${pageContext.request.contextPath}/restaurant/${restId}/menus">Back to Menu details</a>

</body>
</html>