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

<div class="container-fluid">
    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <c:if test="${not empty tienda}">
            <h1 class="display-4 d-inline">${tienda.getRazonSocial()}</h1>
            <a href="<c:url value='/mapa/${tienda.getId()}'/>" class="btn btn-outline-success d-inline">Ver en el mapa</a>
        </c:if>
    </div>

    <div class="row">
        <div id="filtro" class="col-md-3">
            <h3>Busca lo que queres:</h3>
            <form:form action="" method="GET">
                <div class="form-group">
                    <label for="descripcion">Buscar:</label>
                    <input type="search" id="descripcion" name="descripcion" value="${filtros.getDescripcion()}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="categoria">Categorias:</label>
                    <select class="form-control"  id="categoria" name="id_categoria" id="categoria">
                        <option value="0" selected>Todas las categorias</option>
                        <c:forEach var="categoria" items="${categorias}">
                            <option value="${categoria.getId()}"
                                    selected="${filtros.getIdCategoria() == categoria.getId()}">${categoria.getDescripcion()}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="preciomin">Precio minimo:</label>
                    <input type="number" id="preciomin" name="min" value="${filtros.getPrecioMin() }}" class="form-control">
                </div>
                <div class="form-group">
                    <label for="preciomax">Precio maximo:</label>
                    <input type="number" id="preciomax" name="max" value="${filtros.getPrecioMax() }}" class="form-control">
                </div>
                <div>Ordenar:</div>
                <div class="form-group">
                    <div class="form-check ml-3">
                        <input class="form-check-input" type="radio" name="orden" id="asc" value="asc" checked>
                        <label class="form-check-label" for="asc">
                            Ascendente
                        </label>
                    </div>
                    <div class="form-check ml-3">
                        <input class="form-check-input" type="radio" name="orden" id="desc" value="desc">
                        <label class="form-check-label" for="desc">
                            Descendente
                        </label>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary btn-block mt-2">Filtrar</button>
            </form:form>
        </div>
        <div class="card-group col-md-9">
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
                                <c:choose>
                                <c:when test="${producto.getStock() > 0}">
                                    <p class="card-title pricing-card-title">Stock: ${producto.getStock()} </p>
                                    <form action="<c:url value='/cargar-carrito'/>" method="POST">
                                        <input name="idTienda" value="${tienda.getId()}" type="hidden"/>
                                        <input name="idCarrito" value="${idCarrito}" type="hidden"/>
                                        <input name="idProducto" value="${producto.getId()}" type="hidden"/>
                                        <label for="cantidad">Cantidad:</label>
                                        <input type="number" min="1" max="${producto.getStock()}" value="1" name="cantidad" class="form-control" required>
                                        <button type="submit" class="btn btn-lg btn-success">Agregar al carrito</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <p class="card-title pricing-card-title bg-warning">SIN STOCK </p>
                                </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </c:if>
        </div>
    </div>
</div>


    <c:if test="${not empty itemsCarrito}">
    <div>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col" class="text-center">CARRITO:</th>
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
                <c:set var="precioTotalCarrito"
                       value="${precioTotalCarrito + item.getProducto().getImporte() * item.getCantidad()}"/>
                <tr>
                    <td>${item.getProducto().getNombre()}</td>
                    <td>${item.getCantidad()}</td>
                    <td>$ ${item.getProducto().getImporte()}</td>
                    <td>$ ${item.getProducto().getImporte() * item.getCantidad()}</td>
                    <td>
                        <form action="<c:url value='/eliminar-producto-de-carrito'/>" method="POST">
                            <input name="idTienda" value="${tienda.getId()}" type="hidden"/>
                            <input name="idCarrito" value="${idCarrito}" type="hidden"/>
                            <input name="idProducto" value="${item.getProducto().getId()}" type="hidden"/>
                            <button type="submit" class="btn btn-lg btn-danger">Quitar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <p>
            <span class="badge badge-info">IMPORTE TOTAL EN CARRITO: </span> $ ${precioTotalCarrito}
        <form action="<c:url value='/destruir-carrito'/>" method="POST">
            <input name="idTienda" value="${tienda.getId()}" type="hidden"/>
            <input name="idCarrito" value="${idCarrito}" type="hidden"/>
            <button type="submit" class="btn btn-lg btn-danger">Descartar carrito</button>
        </form>
        </p>
        <p>
        <form action="<c:url value='/generar-factura'/>" method="POST">
            <input name="idCarrito" value="${idCarrito}" type="hidden"/>
            <button type="submit" class="btn btn-lg btn-success">Comprar</button>
        </form>
        </p>
    </div>
    </c:if>
<%@include file="../shared/footer.jsp" %>
</body>
</html>