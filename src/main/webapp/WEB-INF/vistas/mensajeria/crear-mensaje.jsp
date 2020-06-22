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

<form:form action="crear-mensaje?dest=${usuario.getId()}" method="POST" modelAttribute="mensaje" class="formulario">
    <h2>Enviar mensaje a: ${usuario.getNombre()}</h2>
    <div>
        <label for="cuerpo">Informacion: </label>
        <form:textarea path="cuerpo" id="cuerpo" class="form-control"/>
    </div>
    <div style="float:right">
        <a href=" <c:url value='/home'/> ">
            <button class="btn btn-lg btn-secondary">Volver</button>
        </a>
        <button class="btn btn-lg btn-danger" Type="submit"/>Enviar</button>
    </div>


</form:form>

<%@include file="../shared/footer.jsp" %>
</body>
</html>

