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
<div class="container-fluid">
    <h1>Bienvenidos a la p&aacute;gina de mascotas</h1>
    <div class="d-flex justify-content-around">

        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">&iquest;Perdiste a tu mascota?</h5>
                <p class="card-text">&iexcl;Crea un anuncia de perdida!</p>
            </div>
            <div class="card-body">
                <a href="/creacion-anuncio">Crear anuncio</a>
            </div>
        </div>

        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">Mir&aacute; la lista de mascotas</h5>
                <p class="card-text">Lista de anuncios de mascotas perdidas</p>
            </div>
            <div class="card-body">
                <a href="/anuncios">Ver lista</a>
            </div>
        </div>
    </div>


</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>