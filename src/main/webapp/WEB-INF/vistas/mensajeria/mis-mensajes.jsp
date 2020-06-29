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
    <h2>Mis Mensajes</h2>
    <div class="row">
        <div class="col-3"><a href="<c:url value ='/mis-mensajes?bandeja=entrada'/>">Bandeja de entrada</a></div>
        <div class="col-3"><a href="<c:url value ='/mis-mensajes?bandeja=salida'/>">Bandeja de salida</a></div>
    </div>
    <div>
        <div id="cabecera" class="row">
            <div class="col-2">Fecha</div>
            <div class="col-2">Remitente</div>
            <div class="col-6">Asunto</div>
            <div class="col-2">Leido</div>
        </div>
        <c:forEach items="${mensajes}" var="mensaje">
            <a href="<c:url value ='/mensaje?cod=${mensaje.getId()}'/>" style="text-decoration:none">
                <div class="row <c:if test="${!mensaje.getLeido()}">noleido</c:if>">
                    <div class="col-2">Fecha</div>
                    <div class="col-2">${mensaje.getRemitente().getNombre()}</div>
                    <div class="col-6">${mensaje.getAsunto()}</div>
                    <div class="col-2"><c:if test="${!mensaje.getLeido()}">no</c:if>leido</div>
                </div>
            </a>
        </c:forEach>

    </div>
</div>

<%@include file="../shared/footer.jsp" %>
</body>
</html>
