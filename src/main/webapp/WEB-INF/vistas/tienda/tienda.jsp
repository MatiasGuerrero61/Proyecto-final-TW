<%@include file="../shared/header.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
<c:if test="${not empty tienda}">
   <h1 class="display-4">${tienda}</h1>
</c:if>  
</div>

<c:if test="${not empty msj}">
    <h4><span class="alert alert-danger">${msj}</span></h4>
    <br>
</c:if>
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
			  <!-- <button type="button" class="btn btn-lg btn-success">Agregar al carrito</button> -->
			</div>
	    </div>
	</div>

    </c:forEach>
</c:if>
</div>
<%@include file="../shared/footer.jsp" %>