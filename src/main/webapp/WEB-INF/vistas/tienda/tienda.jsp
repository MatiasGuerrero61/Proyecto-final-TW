<%@include file="../shared/header.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
<c:if test="${not empty tienda}">
   <h1 class="display-4">${tienda.getRazonSocial()}</h1>
</c:if>  
</div>

<c:if test="${not empty msj}">
    <h4><span class="alert alert-danger">${msj}</span></h4>
    <br>
</c:if>
<div id="filtro">
	
	<form:form action="" method="GET">
		<label>Buscar: 
			<input type="search" name="buscador" value="${filtros.getNombre()}">
		</label>
		<label>
			<select name="categoria">Categorias:
				<option value="todas">Todas las categorias</option>
				<option value="alimento">Alimentos</option>
				<option value="juguete">Juguetes</option>
				<option value="medicina">Medicina</option>
			</select>
		</label>
		<label>Precio minimo:
			<input type="number" name="min" value="${filtros.getPrecioMin() }">
		</label>
		<label>Precio maximo:
			<input type="number" name="max" value="${filtros.getPrecioMax() }">
		</label>
		<label>Ordenar
			<input type="radio" name="orden" value="asc" checked>Ascendente
			<input type="radio" name="orden" value="desc">Descendente
		</label>
		<button type="submit">Filtrar</button>
	</form:form>
</div>
<div class="card-group ml-1">
<c:if test="${not empty productos}">
    <c:forEach var="producto" items="${productos}">

    <div class="card-deck mb-1 mr-1 text-center">
	    <div class="card mb-4 shadow-sm">
			<div class="card-header">
			  <h4 class="my-0 font-weight-normal">${producto.getNombre()}</h4>
			</div>
			<div class="card-body">		
				<h1 class="card-title pricing-card-title">$ ${producto.getImporte()}</h1>	  	
				<form action="<c:url value='/tienda/cargar-carrito'/>" method="POST">	
					<input name="idTienda" value="${tienda.getId()}" type="hidden"/>
					<input name="idProducto" value="${producto.getId()}" type="hidden"/>
					<input type="number" name="cantidad" class="form-control" required>
				   <button type="submit" class="btn btn-lg btn-success">Agregar al carrito</button>
			   </form>
			</div>
	    </div>
	</div>

    </c:forEach>
</c:if>
</div>

<div>
<c:if test="${not empty itemsCarrito}">
    <table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col"  class="text-center">CARRITO:</th>
    </tr>
    <tr>
        <th scope="col">PRODUCTO</th>
        <th scope="col">CANTIDAD</th>
        <th scope="col">PRECIO UNITARIO</th>
        <th scope="col">IMPORTE TOTAL</th>
    </tr>
    </thead>
    <tbody>
    
	<c:set var="precioTotalCarrito" value="0"/>
    <c:forEach var="item" items="${itemsCarrito}">
    	<c:set var="precioTotalCarrito" value="${precioTotalCarrito + item.getProducto().getImporte() * item.getCantidad()}"/>
        <tr>
            <td>${item.getProducto().getNombre()}</td>
            <td>${item.getCantidad()}</td>
            <td>$ ${item.getProducto().getImporte()}</td>
            <td>$ ${item.getProducto().getImporte() * item.getCantidad()}</td>
        </tr>
    </c:forEach>

    </tbody>
    </table>
    <p>
    	<span class="badge badge-info">IMPORTE TOTAL EN CARRITO: </span> $ ${precioTotalCarrito}
	</p>
</c:if>
</div>
<%@include file="../shared/footer.jsp" %>