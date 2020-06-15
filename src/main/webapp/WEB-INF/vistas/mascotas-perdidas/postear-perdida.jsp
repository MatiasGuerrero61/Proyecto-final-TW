<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>

    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet"
          type="text/css"/>
    <meta charset="utf-8">
</head>

<body>
<%@include file="../shared/header.jsp" %>

<form:form action="anuncios" method="POST" modelAttribute="guardarAnuncio" class="formulario">
    <h2>Publicar anuncio de mascota perdida:</h2>
    <p>Complete los siguientes datos:</p>
    <div>
        <label for="titulo">Titulo: </label>
        <form:input path="titulo" id="titulo" type="text" class="form-control"/>
    </div>
    <div>
        <select name="mascotaId">
            <option value="default">Seleccione una mascota</option>
            <c:forEach var="mascota" items="${mascotas}">
                <option value="${mascota.getId()}">${ mascota.getNombre() }</option>
            </c:forEach>
        </select>
    </div>

    <div>
        <label for="Informacion">Mas informacion: </label>
        <form:textarea path="informacion" id="informacion" rows="5" cols="50" class="form-control"/>
    </div>
    <div style="float:right">
        <a href=" <c:url value='/home/index'/> ">
            <button class="btn btn-lg btn-secondary">Vovler</button>
        </a>
        <button class="btn btn-lg btn-danger" Type="submit"/>
        Postear</button>
    </div>


</form:form>

<%@include file="../shared/footer.jsp" %>
</body>
</html>
