<%@include file="../shared/header.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
<c:if test="${not empty tienda}">
   <h1 class="display-4">${tienda}</h1>
</c:if>  
</div>


<div id="filtro">
	
	<form:form action="" method="GET">
		<label>Buscar: 
			<input type="search" name="buscador" value="${filtros.getNombre()}">
		</label>
		<label>
			<select name="categoria">Categorias:
				<option value="todas">Todas las categorias</option>
				<option value="alimento" >Alimentos</option>
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
<c:if test="${not empty msj}">
    <h4><span class="alert alert-danger">${msj}</span></h4>
    <br>
</c:if>
<c:if test="${not empty productos}">
    <c:forEach var="producto" items="${productos}">

    <div class="card-deck mb-1 mr-1 text-center">
	    <div class="card mb-4 shadow-sm">
			<div class="card-header">
			  <h4 class="my-0 font-weight-normal">${producto.getNombre()}</h4>
			</div>
			<div class="card-body">		
				<h1 class="card-title pricing-card-title">$ ${producto.getImporte()}</h1>	  		
			  <!-- <button type="button" class="btn btn-lg btn-success">Agregar al carrito</button> -->
			</div>
	    </div>
	</div>

    </c:forEach>
</c:if>
</div>
<%@include file="../shared/footer.jsp" %>