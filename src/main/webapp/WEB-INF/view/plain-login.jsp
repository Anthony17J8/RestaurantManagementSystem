<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.webjars.org/tags" prefix="wj" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}<wj:locate path="css/bootstrap.min.css" relativeTo="META-INF/resources"/>'>
    <link rel='stylesheet'
          href='${pageContext.request.contextPath}<wj:locate path="css/font-awesome.css" relativeTo="META-INF/resources"/>'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
</head>
<body>

<div class="container">
    <form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">

        <div class="row mt-5">

            <div class="shadow-form col-6 offset-3 px-0">

                <div id="title" class="row m-0 px-0 py-3">
                    <h1 class="text-center m-0">Log in</h1>
                </div>

                <div id="form-content" class="px-5 py-3">
                    <div class="form-group">
                        <div class="row my-2 mx-0 text-center">
                            <c:if test="${param.error != null}">
                                <span class="form-info error">You entered invalid username/password</span>
                            </c:if>
                        </div>

                        <div class="row my-2 mx-0 text-center">
                            <c:if test="${param.logout != null}">
                                <span class="form-info">You have been logged out</span>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="uName">User name</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text justify-content-center">
                                    <i class="fa fa-user"></i>
                                </div>
                            </div>

                            <input type="text" name="username" id="uName" class="form-control">

                        </div>
                    </div>

                    <div class="form-group">
                        <label for="pass">Password</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <div class="input-group-text justify-content-center">
                                    <i class="fa fa-unlock" aria-hidden="true"></i>
                                </div>
                            </div>

                            <input type="password" name="password" id="pass" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="form-check custom-checkbox">
                            <input type="checkbox" class="form-check-input custom-control-input"
                                   name="remember-me" id="remember-me">
                            <label for="remember-me" class="form-check-label custom-control-label">Remember
                                me?</label>
                        </div>
                    </div>

                    <div class="row mt-3 mx-0">
                        <button type="submit" class="btn btn-block btn-prm">LOGIN</button>
                    </div>

                    <div class="row mt-4 mx-0">
                        <div class="col-6 px-0">
                            <a href="${fn:escapeXml(pageContext.request.contextPath)}/register/showRegistrationForm">
                                Sign up</a>
                        </div>
                        <div class="col-6 px-0 text-right"><a href="#">Forgot password?</a></div>
                    </div>

                </div>
            </div>
        </div>

    </form:form>
    </section>
</div>

<script type='text/javascript' src='${pageContext.request.contextPath}<wj:locate path="jquery.min.js" relativeTo="META-INF/resources"/>'></script>
<script type='text/javascript' src='${pageContext.request.contextPath}<wj:locate path="js/bootstrap.min.js" relativeTo="META-INF/resources"/>'></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/login-page.js"></script>
</body>
</html>
