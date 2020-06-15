<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar  navbar-dark bg-dark navbar-expand-lg">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <ul class="navbar-nav mr-auto">
        <li class="nav-item mr-auto"><a class="nav-link" href="#"></a></li>
        <li class="nav-item mr-auto"><a class="nav-link" href="<c:url value='/home'/> ">Home</a></li>
        <li class="nav-item mr-auto"><a class="nav-link" href="<c:url value='/tiendas'/>">Tiendas</a>
        </li>
        <li class="nav-item mr-auto"><a class="nav-link" href="<c:url value='/perfil/cargar-foto'/>">Actualizar
            foto de perfil</a></li>
    </ul>
    <span>
        <c:choose>
            <c:when test="${not empty sessionScope.EMAIL}">

                <span class="glyphicon glyphicon-user"></span>
                <span
                        class="text-white nav-item">${sessionScope.EMAIL} </span>
                <a class="nav item text-white" href=" <c:url value='/logout'/> "><span
                        class="glyphicon glyphicon-log-out"></span> Salir</a>

            </c:when>
            <c:otherwise>
                <span><a class="nav-link text-white" href=" <c:url value='/login'/> "><span
                        class="glyphicon glyphicon-log-out"></span> Iniciar sesi&oacute;n</a></span>
            </c:otherwise>
        </c:choose>
    </span>
</nav>

