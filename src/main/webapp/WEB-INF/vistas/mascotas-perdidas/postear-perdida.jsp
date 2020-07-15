<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>

    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <meta charset="utf-8">
</head>

<body>
<%@include file="../shared/header.jsp" %>
<div class="container">


<form:form action="anuncios" method="POST" modelAttribute="guardarAnuncio" class="formulario" enctype="multipart/form-data">
    <h2>Publicar anuncio de mascota perdida:</h2>
    <p>Complete los siguientes datos:</p>
    <div class="mt-3">
        <label for="titulo">Titulo: </label>
        <form:input path="titulo" id="titulo" type="text" class="form-control"/>
    </div>
    <div class="row mt-3">
        <div class="col-4">
            <div>
                <label for="mascota">Seleccione una mascota:</label>
                <select name="mascotaId" id="mascota">
                    <option value="default">Seleccione una mascota</option>
                    <c:forEach var="mascota" items="${mascotas}">
                        <option value="${mascota.getId()}">${ mascota.getNombre() }</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label for="imagen">Imagen:</label>
                <input id="imagen" type="file" class="form-control" name="imagen">
            </div>
        </div>
        <div class="col-8">
            <div>
                <label for="Informacion">Mas informacion: </label>
                <form:textarea path="informacion" id="informacion" rows="5" cols="50" class="form-control"/>
            </div>
        </div>
    </div>
    <div class="row mt-3">
      <div class="col-8">
          <div class="row mt-3">
              <div class="col-4">
                  <label for="recompensa-check">¿Ofrecer Recompensa?</label>
                  <input type="checkbox" id="recompensa-check" onchange="checkRecompensa()">
              </div>
              <div class="col-4">
                  <label class="sr-only" for="recompensa"></label>
                  <div class="input-group mb-2">
                      <div class="input-group-prepend">
                          <div class="input-group-text">Monto:</div>
                      </div>
                      <form:input type="number" class="form-control" id="recompensa"
                             path="recompensa" value="0.00" readonly="true" />
                  </div>
              </div>
          </div>

      </div>
        <div class="col-4">
            <div style="float:right">
                <a href=" <c:url value='/home'/> ">
                    <button class="btn btn-lg btn-secondary">Vovler</button>
                </a>
                <button class="btn btn-lg btn-danger" Type="submit"/>
                Postear</button>
            </div>
        </div>
    </div>





</form:form>
</div>

<%@include file="../shared/footer.jsp" %>
<script>
    function checkRecompensa()
    {
        let recompensa = document.getElementById('recompensa');
        let check = document.getElementById("recompensa-check")
        recompensa.readOnly = !check.checked;
        if(recompensa.readOnly){recompensa.value= 0.00}
    }
</script>
</body>
</html>
