<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Access Denied</title>
</head>

<body>

<h2>Access Denied - You are not authorized to access this resource.</h2>

<br>
<a href="${fn:escapeXml(pageContext.request.contextPath)}/restaurant/list">Back to Home Page</a>

</body>

</html>