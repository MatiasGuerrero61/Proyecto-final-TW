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
    <h3>Estas seguro de pagar la recompensa?</h3>
    <a class="btn btn-lg btn-info" href="<c:url value='/mis-anuncios'/>">No, volver a mis anuncios</a>
    <a class="btn btn-lg btn-primary" href="${preference.initPoint}">Si, quiero pagar</a>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>