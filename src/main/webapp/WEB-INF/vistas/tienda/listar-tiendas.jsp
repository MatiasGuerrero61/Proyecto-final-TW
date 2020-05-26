<%@include file="../shared/header.jsp" %>

<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
  <h1 class="display-4">Encontr&aacute; todo para tus mascotas!</h1>
</div>

<c:if test="${not empty msj}">
    <h4><span class="alert alert-danger">${msj}</span></h4>
    <br>
</c:if>
<div class="card-group ml-1">
<c:if test="${not empty tiendas}">
    <c:forEach var="tienda" items="${tiendas}">

    <div class="card-deck mb-1 mr-1 text-center">
	    <div class="card mb-4 shadow-sm">
			<div class="card-header">
			  <h4 class="my-0 font-weight-normal">${tienda.getRazonSocial()}</h4>
			</div>
			<div class="card-body">			  		
			  <a href=" <c:url value='/tienda/ver/${ tienda.getId() }'/> " class="btn btn-lg btn-success">Ir a la tienda</a>
			</div>
	    </div>
	</div>

    </c:forEach>
</c:if>
</div>
<%@include file="../shared/footer.jsp" %>