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

	<c:if test="${not empty msj}">
	  <div class="alert alert-${tipoDeMsj}" role="alert">
	        <h4>${msj}</h4>
	        <br>
	  </div>
	</c:if>	
	
	<c:if test="${empty factura.getDescuento()}">
	<form action="aplicar-descuento"  method="POST">
		<label>Aplicar c&oacute;digo de descuento: </label>
		<input name="codigo" type="text" class="form-control"/>   
		<input name="idFactura" type="hidden" value="${factura.getId()}"/>  		  

		<button class="btn btn-lg btn-warning" Type="Submit"/>Aplicar Descuento</button>
	</form>
	</c:if>
    
    <div>
    	<c:if test="${not empty itemsFactura}">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="text-center">FACTURA:</th>
            </tr>
            <tr>
                <th scope="col">PRODUCTO</th>
                <th scope="col">CANTIDAD</th>
                <th scope="col">PRECIO UNITARIO</th>
                <th scope="col">IMPORTE TOTAL</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="item" items="${itemsFactura}">
                <tr>
                    <td>${item.getProducto().getNombre()}</td>
                    <td>${item.getCantidad()}</td>
                    <td>$ ${item.getProducto().getImporte()}</td>
                    <td>$ ${item.getProducto().getImporte() * item.getCantidad()}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <p class="bg-light">SUBTOTAL:      $ ${factura.getSubtotalSinDescuentos()} </p>
        <p class="bg-light">DESCUENTOS:  - $ ${factura.getSubtotalDescuentos()} </p>
        <p class="bg-info">IMPORTE FINAL: $ ${factura.getImporteFinal()} </p>

        <c:if test="${not empty factura.getDescuento()}">   
        <br>     
            <span class="badge badge-secondary">Descuento aplicado: </span> ${factura.getDescuento().getPorcentajeDeDescuento()} %
            <span class="badge badge-secondary">Tope de descuento:  </span> $ ${factura.getDescuento().getTope()} 
         </c:if>

    </c:if>
    <br>
    <a class="btn btn-lg btn-primary" href="${preference.initPoint}">Pagar</a>

</div>

</div>

<%@include file="../shared/footer.jsp" %>
</body>
</html>