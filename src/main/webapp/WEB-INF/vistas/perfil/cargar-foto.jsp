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

<form method="POST" action="./cargar-foto" enctype="multipart/form-data">
    <div class="form-group">
        <label class="control-label">Seleccione la foto de perfil: </label>
        <input type="file" class="form-control" name="file">
    </div>
    <div class="form-group">
        <input type="submit" class="form-control btn btn-success" value="Subir">
    </div>
</form>

<c:if test="${not empty msj}">
    <h4><span>${msj}</span></h4>
    <br>
</c:if>

<c:if test="${not empty sessionScope.FOTO_DE_PERFIL}">
    <img src="../files/${sessionScope.FOTO_DE_PERFIL}" alt="" class="img-thumbnail">
    <br>
</c:if>

<%@include file="../shared/footer.jsp" %>
</body>
</html>
  