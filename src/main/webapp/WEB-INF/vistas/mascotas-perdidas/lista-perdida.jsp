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
            <div class="d-flex flex-wrap">
            <c:forEach items="${anuncios}" var="anuncio">
            <c:if test="${!sessionScope.ID_USUARIO.equals(anuncio.getDuenio().getId())}">
                <div class="col-3 p-1">
                    <%-- CARD --%>
                    <div class="card">
                        <div class="card-image">
                            <img src="${pageContext.servletContext.contextPath}/files/${anuncio.getFotoDeAnuncio().getNombre()}"
                                 alt="imagen del anuncio" class="card-img-top">
                        </div>
                        <div class="card-body">
                            <div class="card-title">${anuncio.getTitulo()}</div>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#anuncio${anuncio.getId()}">
                                Mas informacion
                            </button>
                        </div>
                    </div>
                </div>
                <%-- CARD --%>
                <div class="modal fade" id="anuncio${anuncio.getId()}" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">${anuncio.getTitulo()}</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h4><span>Propietario: ${anuncio.getMascota().getDuenio().getNombre()}</span></h4>
                                <h4><span>Email: ${anuncio.getMascota().getDuenio().getEmail()}</span></h4>
                                <h4><span>Informacion: ${anuncio.getInformacion()}</span></h4>
                                <h4><span>Mascota: ${anuncio.getMascota().getNombre()}</span></h4>
                                <h4><span>Caracteristicas: ${anuncio.getMascota().getCaracteristica()}</span></h4>
                                <h4><span>Animal: ${anuncio.getMascota().getAnimal()}</span></h4>
                              <c:if test="${anuncio.getRecompensa() != 0.0}">
                                <h4><span>Recompensa: $ ${anuncio.getRecompensa()}</span></h4>
                              </c:if>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <c:choose>
                                    <c:when test="${sessionScope.ID_USUARIO.equals(anuncio.getDuenio().getId())}">
                                        <a class="btn btn-danger" href="<c:url value='/home' />">Eliminar post!</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-info" href="<c:url value='/crear-mensaje?usuario=${anuncio.getDuenio().getId()}'/>">Contactar</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
          </c:if>
        </c:forEach>
        </div>
    </c:otherwise>
</c:choose>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>

