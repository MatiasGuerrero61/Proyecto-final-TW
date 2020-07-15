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



<c:if test="${not empty tiendas}">

<div class=" px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4 d-inline">Encontr&aacute; todo para tus mascotas!</h1> 
</div>


<div id="mapid"></div>
<div id="msj"></div>

<script>

/*Si el usuario tiene bloqueada la geolocalizacion, parte desde las coordenadas de la Unlam*/
var latitudPorDefecto = -34.6705129;
var longitudPorDefecto = -58.5650539;
var mymap = L.map('mapid').setView([latitudPorDefecto,longitudPorDefecto], 15);

/*intentamos geolocalizar y enfocamos el mapa en ese punto*/
mymap.locate({setView: true, maxZoom: 15});

L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoiZHJmbG9yZXMiLCJhIjoiY2tjMmQ3eHp5MTI4eDMwdXI2OXl2dnBjOCJ9.rY1P53_ksprHT6we27Yqww', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'pk.eyJ1IjoiZHJmbG9yZXMiLCJhIjoiY2tjMmQ3eHp5MTI4eDMwdXI2OXl2dnBjOCJ9.rY1P53_ksprHT6we27Yqww'
}).addTo(mymap);

/*Si pudimos geolocalizar al visitante, lo marcamos en el mapa con un circulo*/
function onLocationFound(e) {
    var radius = e.accuracy;
    L.marker(e.latlng).addTo(mymap)
        .bindPopup("Tu ubicaci&oacute;n actual").openPopup();
    L.circle(e.latlng, radius).addTo(mymap);
}
mymap.on('locationfound', onLocationFound);

/*En caso de no poder acceder a la ubicacion lo informamos mediante una alerta*/
function onLocationError() {
    alert("No se ha podido acceder a su ubicacion");
}
mymap.on('locationerror', onLocationError);

/*Agregamos todas las tiendas al mapa*/
<c:forEach var="tienda" items="${tiendas}">
L.marker([${tienda.getUbicacion().getLatitud()},${tienda.getUbicacion().getLongitud()}])
   .bindPopup('<b> ${tienda.getRazonSocial()} </b><br> Direcci&oacute;n: ${tienda.getUbicacion().getCalle()} ${tienda.getUbicacion().getAltura()} <a href="<c:url value='/tiendas/${tienda.getId()}'/>" class="card-link d-block">Ver tienda</a>').addTo(mymap);
   
</c:forEach>

</script>
</c:if>

</div>
<%@include file="../shared/footer.jsp" %>
</body>
</html>