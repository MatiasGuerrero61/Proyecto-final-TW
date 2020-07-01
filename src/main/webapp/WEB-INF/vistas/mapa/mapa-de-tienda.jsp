<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>

    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet"
          type="text/css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/leaflet.css" />
	<script src="${pageContext.servletContext.contextPath}/js/leaflet.js"></script>
	
 
 <style>
  #mapid { 
  height: 500px; }
 </style>
 
    <meta charset="UTF-8">
</head>

<body>
<%@include file="../shared/header.jsp" %>

<div class="container">



<c:if test="${not empty tienda}">

<div class=" px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4 d-inline">Tienda: ${tienda.getRazonSocial()}</h1> 
    <a href="<c:url value='/tiendas/${tienda.getId()}'/>" class="btn btn-outline-success d-inline">Ir a la tienda</a>
</div>


<div id="mapid"></div>


<script>
var mymap = L.map('mapid').setView([${tienda.getUbicacion().getLatitud()},${tienda.getUbicacion().getLongitud()}], 15);
L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoiZHJmbG9yZXMiLCJhIjoiY2tjMmQ3eHp5MTI4eDMwdXI2OXl2dnBjOCJ9.rY1P53_ksprHT6we27Yqww', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'pk.eyJ1IjoiZHJmbG9yZXMiLCJhIjoiY2tjMmQ3eHp5MTI4eDMwdXI2OXl2dnBjOCJ9.rY1P53_ksprHT6we27Yqww'
}).addTo(mymap);

L.marker([${tienda.getUbicacion().getLatitud()},${tienda.getUbicacion().getLongitud()}])
   .bindPopup('<b> ${tienda.getRazonSocial()} </b><br> Direcci&oacute;n: ${tienda.getUbicacion().getCalle()} ${tienda.getUbicacion().getAltura()}').addTo(mymap).openPopup();
</script>
</c:if>

</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>