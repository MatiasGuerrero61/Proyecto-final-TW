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

<div class="px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
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
                    	<a href="<c:url value='/mapa/${tienda.getId()}'/>" class="card-link d-block mb-3">Ver mapa</a>
                        <a href="<c:url value='/tiendas/${tienda.getId()}'/>" class="btn btn-lg btn-success d-block">Ir a la
                            tienda</a>
                    </div>
                </div>
            </div>

        </c:forEach>
    </c:if>
</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>