<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>

    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet"
          type="text/css"/>
    <meta charset="utf-8">
    <title>Mis Mensajes</title>
    <style>
        #cabecera{
            border-bottom: 1px solid #80808094;
            margin-bottom: 0.2em;
            font-weight: bold;
        }
        .noleido {
            background-color: #6eb8f954;
            font-weight: 500;
            border-radius: 0.3em;
        }
        .row{
            padding: 0.3em 0;
            margin: 0.3em 0;
        }
    </style>
</head>

<body>
<%@include file="../shared/header.jsp" %>
<div class="container">

    <div class="card">
        <div class="card-header">
            <h5>Asunto: ${mensaje.getAsunto()}</h5>
            <p>De: [${mensaje.getRemitente().getEmail()}] ${mensaje.getRemitente().getNombre()}</p>
        </div>
        <div class="card-body">
            <p>${mensaje.getCuerpo()}</p>
        </div>
        <div class="card-footer">
            <a class="btn btn-info" href="<c:url value='/crear-mensaje?usuario=${mensaje.getRemitente().getId()}'/>">Responder</a>
        </div>
    </div>
</div>

<%@include file="../shared/footer.jsp" %>
</body>
</html>
