<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>

    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet"
          type="text/css"/>
    <meta charset="UTF-8">
</head>

<body>
<%@include file="../shared/header.jsp" %>
<h1 class="mt-5 mb-5">Mis mascotas
    <span class="float-right">
            <a href=" <c:url value='/agregar-mascota'/> " class="btn btn-success">Agregar una nueva mascota</a>
        </span>
</h1>
<c:choose>
    <c:when test="${misMascotas.isEmpty()}">
        <h4>No hay mascotas agregadas</h4>

    </c:when>
    <c:otherwise>
        <c:forEach items="${misMascotas}" var="mascota">

            <div class="card mb-3">
                <div class="row no-gutters">
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">Nombre: ${anuncio.getTitulo()}</h5>
                        </div>
                    </div>
                </div>
            </div>

        </c:forEach>
    </c:otherwise>
</c:choose>

<%@include file="../shared/footer.jsp" %>
</body>
</html>
