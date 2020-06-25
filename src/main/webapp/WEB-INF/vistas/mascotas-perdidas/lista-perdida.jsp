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
<div class="container">

    <h1>Lista de anuncios de mascotas perdidas</h1>
    <c:choose>
        <c:when test="${anuncios.isEmpty()}">
            <h4>No hay anuncios disponibles</h4>
        </c:when>
        <c:otherwise>
            <c:forEach items="${anuncios}" var="anuncio">
                <div class="d-flex"></div>
                <div class="card col-4">
                    <h4><span>Titulo: ${anuncio.getTitulo()}</span></h4>
                    <h4><span>Propietario: ${anuncio.getMascota().getDuenio().getNombre()}</span></h4>
                    <h4><span>Email: ${anuncio.getMascota().getDuenio().getEmail()}</span></h4>
                    <h4><span>Informacion: ${anuncio.getInformacion()}</span></h4>
                    <h4><span>Mascota: ${anuncio.getMascota().getNombre()}</span></h4>
                    <h4><span>Caracteristicas: ${anuncio.getMascota().getCaracteristica()}</span></h4>
                    <h4><span>Animal: ${anuncio.getMascota().getAnimal()}</span></h4>
                    <c:choose>
                        <c:when test="${sessionScope.ID_USUARIO.equals(anuncio.getMascota().getDuenio().getId())}">
                            <a class="btn btn-danger" href="<c:url value='/home' />">Eliminar post!</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-info" href="<c:url value='/crear-mensaje?usuario=${anuncio.getMascota().getDuenio().getId()}'/>">Enviar Mensaje</a>
                        </c:otherwise>
                    </c:choose>


                </div>

            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>

