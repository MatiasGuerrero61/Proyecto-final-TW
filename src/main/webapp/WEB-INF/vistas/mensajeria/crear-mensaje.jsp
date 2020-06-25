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
<div class="container">

    <form:form action="crear-mensaje?dest=${usuario.getId()}" method="POST" modelAttribute="mensaje" class="formulario">
        <h2>Enviar mensaje a: ${usuario.getNombre()}</h2>
        <div>
            <label for="asunto">Asunto:</label>
            <form:input type="text" id="asunto" name="asusnto" class="form-control"
                        value="RE: ${asunto}" readonly="${not empty asunto}" path="asunto"/>
        </div>
        <div>
            <label for="cuerpo">Cuerpo: </label>
            <form:textarea path="cuerpo" id="cuerpo" class="form-control"/>
        </div>
        <div style="float:right">
            <a href=" <c:url value='/home'/> ">
                <button class="btn btn-lg btn-secondary">Volver</button>
            </a>
            <button class="btn btn-lg btn-danger" Type="submit"/>Enviar</button>
        </div>


    </form:form>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>

