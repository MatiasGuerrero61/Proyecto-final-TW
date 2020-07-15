<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
    .perfil {
        width: 35px;
        border-radius: 0.2em;
        border: 2px solid;
    }
    .btn-primary:hover{
        border: none;
    }
</style>
<nav class="navbar  navbar-dark bg-dark navbar-expand-lg">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <span>
        <c:choose>
            <c:when test="${not empty sessionScope.EMAIL}">
                <div class="dropdown">
                    <button type="button" class="btn btn-secondary" data-toggle="dropdown">
                        <span>
                            <img src="<c:url value='/files/${sessionScope.FOTO_DE_PERFIL}'/>" alt="" class="perfil">
                        </span>
                        <span style="text-transform:capitalize;font-weight:bold">
                                ${sessionScope.NOMBRE}
                        </span>
                    </button>
                    <div class="dropdown-menu">
                        <div class="dropdown-header" style="text-transform:uppercase">${sessionScope.EMAIL}</div>
                        <a class="dropdown-item" href="<c:url value ='/mis-mensajes?bandeja=entrada'/>">Mis Mensajes</a>
                        <a class="dropdown-item" href="<c:url value='/perfil/cargar-foto'/>">Actualizar foto de perfil</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href=" <c:url value='/logout'/> ">Salir</a>
                    </div>
                </div>

            </c:when>
            <c:otherwise>
                <span><a class="nav-link text-white" href=" <c:url value='/login'/> "><span
                        class="glyphicon glyphicon-log-out"></span> Iniciar sesi&oacute;n</a></span>
            </c:otherwise>
        </c:choose>
    </span>

    <ul class="navbar-nav mr-auto">
        <li class="nav-item mr-auto"><a class="nav-link" href="#"></a></li>
        <li class="nav-item mr-auto"><a class="nav-link" href="<c:url value='/home'/> ">Home</a></li>
        <li class="nav-item mr-auto"><a class="nav-link" href="<c:url value='/tiendas'/>">Tiendas</a></li>
        <li class="nav-item mr-auto"><a class="nav-link" href="<c:url value='/mapa'/>">Mapa</a></li>
        <li class="nav-item mr-auto"><a class="nav-link" href="<c:url value='/paseadores'/>">Paseadores</a></li>
        <li class="nav-item mr-auto"></li>
    </ul>
</nav>

