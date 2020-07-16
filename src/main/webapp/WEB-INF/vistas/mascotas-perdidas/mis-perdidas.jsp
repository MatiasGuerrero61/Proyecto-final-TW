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

    <h1 class="mt-5 mb-5">Mis publicaciones
        <span class="float-right">
            <a href=" <c:url value='/creacion-anuncio'/> " class="btn btn-success">Crear anuncio</a>
        </span>
    </h1>
    <c:choose>
        <c:when test="${anuncios.isEmpty()}">
            <h4>No hay anuncios disponibles</h4>

        </c:when>
        <c:otherwise>
            <c:forEach items="${anuncios}" var="anuncio">

                <div class="card mb-3">
                    <div class="row no-gutters">
                        <div class="col-md-3 m-1">
                            <img src="${pageContext.servletContext.contextPath}/files/${anuncio.getFotoDeAnuncio().getNombre()}"
                                 alt="" class="img-fluid rounded">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title">Titulo: ${anuncio.getTitulo()}</h5>
                                <p class="card-text">Informacion: ${anuncio.getInformacion()}</p>
                                <p class="card-text">Mascota: ${anuncio.getMascota().getNombre()}</p>
                                <c:choose>
                                    <c:when test="${anuncio.getRecompensa() != 0.0}">
                                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#anuncio${anuncio.getId()}-recompensa">
                                            Eliminar post!
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#anuncio${anuncio.getId()}">
                                            Eliminar post!
                                        </button>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="anuncio${anuncio.getId()}" data-backdrop="static" data-keyboard="false"
                     tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">${anuncio.getTitulo()}</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h4>Estas seguro que quieres eliminar el posteo?</h4>
                                <a class="btn btn-danger" href="<c:url value='/eliminar-anuncio?idanuncio=${anuncio.getId()}' />">Si, quiero eliminar</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="anuncio${anuncio.getId()}-recompensa" data-backdrop="static" data-keyboard="false"
                     tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel2" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel2">${anuncio.getTitulo()}</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <h4>El anuncio posee una recompensa. Desea pagar la recompensa?</h4>
                                <a class="btn btn-warning" href="<c:url value='/pagar-anuncio?idanuncio=${anuncio.getId()}' />">Si, quiero pagar y eliminar</a>
                                <a class="btn btn-danger" href="<c:url value='/eliminar-anuncio?idanuncio=${anuncio.getId()}' />">No, solo eliminar</a>
                            </div>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>
