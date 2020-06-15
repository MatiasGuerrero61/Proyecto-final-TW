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
<h1>Bienvenidos a la pagina de mascotas</h1>

<div class="d-flex justify-content-around aling-items-between">
    <div class="card section">
        <h3>¿Perdiste a tu mascota?</h3>
        <p>Crea un anuncia de perdida!</p>
        <a href="<c:url value='/creacion-anuncio'/>">
            <button class="btn btn-primary">Postear</button>
        </a>
    </div>
</div>
<div class="d-flex justify-content-around aling-items-between">
    <div class="card section">
        <p>Lista de anuncios de mascotas perdidas!</p>
        <a href="<c:url value='/anuncios'/>">
            <button class="btn btn-primary">Listar</button>
        </a>
    </div>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>