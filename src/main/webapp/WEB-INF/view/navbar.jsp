<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<nav class="navbar navbar-expand-md navbar-light bg-light p-0">
    <a class="navbar-brand px-3 py-2 hvr-sweep-to-right" href="${pageContext.request.contextPath}/restaurants">Restaurant Vote App</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navContent"
            aria-controls="navContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-lg-end py-0" id="navContent">
        <div class="navbar-nav">
            <a class="nav-link px-3 py-2 hvr-sweep-to-right" href="#">Sign In</a>

            <form:form method="post" action="${pageContext.request.contextPath}/logout">
                <span class="nav-link px-3 py-2 hvr-sweep-to-right">
                    <input class="btn btn-link" type="submit" value="Logout">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </span>
            </form:form>
        </div>
    </div>
</nav>
