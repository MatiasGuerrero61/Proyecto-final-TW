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

<div class = "container">
	
	<form action="facturacion"  method="POST">
	<label for="start">Selecciones mes:</label>
	<input type="month" id="fecha" name="fecha" value="${periodoDeFacturacion}">
	<button class="btn btn-lg btn-warning" Type="Submit"/>Aplicar</button>
    </form>
    
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <c:if test="${not empty periodoDeFacturacion}">
        <h1 class="display-4 d-inline">Per&iacute;odo de facturaci&oacute;n: ${periodoDeFacturacion}</h1>
    </c:if>
	</div>
    
    <c:if test="${empty  facturaciones}">
        <div class="alert alert-info" role="alert">
	        <h4>No se generaron facturas en este periodo...</h4>
	        <br>
	  </div>
    </c:if>
    <c:if test="${not empty facturaciones}">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="text-center">FACTURA:</th>
            </tr>
            <tr>
                <th scope="col">TIENDA</th>
                <th scope="col">CANTIDAD DE VENTAS</th>
                <th scope="col">IMPORTE TOTAL</th>
                <th scope="col">COMISION 3%</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="facturacion" items="${facturaciones}">
                <tr>
                    <td>${facturacion.getNombreDeTienda()}</td>
                    <td>${facturacion.getCantidadDeVentas()}</td>
                    <td>$ ${facturacion.getImporteTotal()}</td>
                    <c:set var="comision" value="0"/>
                	<c:set var="comision" value="${facturacion.getImporteTotal() *3/100}"/>
                    <td>$ ${comision}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:if>	
</div>

</div>

<%@include file="../shared/footer.jsp" %>
</body>
</html>