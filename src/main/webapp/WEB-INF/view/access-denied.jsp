<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>

<head>
    <title>Access Denied</title>
</head>

<body>

<h2>Access Denied - You are not authorized to access this resource.</h2>

<br>
<a href="${fn:escapeXml(pageContext.request.contextPath)}/restaurant/showAll">Back to Home Page</a>

</body>

</html>