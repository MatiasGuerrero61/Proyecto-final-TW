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
    <div class="row align-items-center">
        <div class="col-md-4 p-5">
            <c:choose>
                <c:when test="${not empty sessionScope.FOTO_DE_PERFIL}">
                    <img src="../files/${sessionScope.FOTO_DE_PERFIL}" alt="" class="img-thumbnail img-fluid">
                </c:when>
                <c:otherwise>
                    <img src="../files/perfil-default.jpg" alt="" class="img-thumbnail img-fluid">
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-md-8">
            <form method="POST" action="./cargar-foto" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="control-label">Seleccione la foto de perfil: </label>
                    <input type="file" class="form-control" name="file">
                </div>
                <div class="form-group">
                    <input type="submit" class="form-control btn btn-success" value="Actualizar foto de perfil">
                </div>
            </form>
        </div>
    </div>
</div>



<c:if test="${not empty msj}">
    <h4 class="text-center"><span>${msj}</span></h4>
    <br>
</c:if>



<%@include file="../shared/footer.jsp" %>
</body>
</html>
  