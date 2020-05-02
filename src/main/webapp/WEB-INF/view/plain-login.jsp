<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.webjars.org/tags" prefix="wj" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/login-page.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel='stylesheet' href='<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">
    <div class="container">
        <section id="main">
            <div class="row">

                <div class="col-6 offset-3">
                    <div id="login" class="row m-0 px-0 py-3">
                        <h1 class="text-center m-0">LOG IN</h1>
                    </div>
                    <div id="form-content" class="px-5 py-4">

                        <div class="row">
                            <c:if test="${param.error != null}">
                                <span class="form-info error text-center">You entered invalid username/password</span>
                            </c:if>
                        </div>

                        <div class="row">
                            <c:if test="${param.logout != null}">
                                <span class="form-info text-center">You have been logged out</span>
                            </c:if>
                        </div>

                        <div class="row mt-2">
                            <div class="row-group col-12 px-0">
                                <label for="username">User name</label>
                                <input type="text" name="username" id="username" class="form-control">
                            </div>
                        </div>

                        <div class="row mt-2">
                            <div class="row-group col-12 px-0">
                                <label for="password">Password</label>
                                <input type="password" name="password" id="password" class="form-control">
                            </div>
                        </div>

                        <div class="row custom-control custom-checkbox mt-3">
                            <input type="checkbox" class="custom-control-input" name="remember-me" id="remember-me">
                            <label for="remember-me" class="custom-control-label">Remember me?</label>
                        </div>

                        <div class="row mt-3">
                            <button type="submit" class="btn btn-block">LOGIN</button>
                        </div>
                        <div class="row mt-4 px-0">
                            <div class="col-6 px-0">
                                <a href="${fn:escapeXml(pageContext.request.contextPath)}/register/showRegistrationForm">
                                    Sign up</a>
                            </div>
                            <div class="col-6 px-0 text-right"><a href="#">Forgot password?</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</form:form>

<script type='text/javascript' src='<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
<script type='text/javascript' src='<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/login-page.js"></script>
</body>
</html>
