<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">

    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet"
          type="text/css"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/leaflet.css"/>
    <style>
        #map {
            height: 500px;
        }
    </style>

</head>

<body>
<%@include file="../shared/header.jsp" %>

<div class="container">

    <c:choose>
        <c:when test="${not empty paseadores}">
            <div class=" px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                <h1 class="display-4 d-inline">Mapa de paseadores</h1>
            </div>
            <div id="map"></div>

        </c:when>
        <c:otherwise>
            <h1>En estos momentos no hay datos disponibles :(</h1>
        </c:otherwise>
    </c:choose>

    <div class="modal fade" id="modal-map-error" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">Error!</h4>
                </div>
                <div class="modal-body">
                    <p id="modal-error-content"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

</div>
<%@include file="../shared/footer.jsp" %>
<script src="${pageContext.servletContext.contextPath}/js/leaflet.js"></script>
<script type="text/javascript">

  /*Si el usuario tiene bloqueada la geolocalizacion, parte desde las coordenadas de la Unlam*/
  var latitudPorDefecto = -34.6705129;
  var longitudPorDefecto = -58.5650539;
  var map = L.map('map').setView([latitudPorDefecto, longitudPorDefecto], 15);

  /*intentamos geolocalizar y enfocamos el mapa en ese punto*/
  map.locate({setView: true, maxZoom: 15});
  let accessToken = "pk.eyJ1IjoiZHJmbG9yZXMiLCJhIjoiY2tjMmQ3eHp5MTI4eDMwdXI2OXl2dnBjOCJ9.rY1P53_ksprHT6we27Yqww";

  L.tileLayer(
      "https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=" + accessToken,
      {
        maxZoom: 18,
        id: 'mapbox/streets-v11',
        tileSize: 512,
        zoomOffset: -1,
        accessToken: accessToken
      }).addTo(map);

  let marcadores = []

  <c:forEach var="paseador" items="${paseadores}">

  marcadores.push({
    nombre: "${paseador.getNombre()}",
    telefono: "${paseador.getTelefono()}",
    tarifa: "${paseador.getTarifa()}",
    latLng: L.latLng(${paseador.getUbicacion().getLatitud()}, ${paseador.getUbicacion().getLongitud()})
  });

  </c:forEach>

  /*Si pudimos geolocalizar al visitante, lo marcamos en el mapa con un circulo*/
  function onLocationFound(e) {
    let radius = 21000;
    L.marker(e.latlng).addTo(map).bindPopup("Tu ubicaci&oacute;n actual").openPopup();
    L.circle(e.latlng, radius).addTo(map);

    let caminadorIcon = new L.icon({iconUrl: '${pageContext.servletContext.contextPath}/files/caminador_icon.png'})

    marcadores.filter(marcador => e.latlng.distanceTo(marcador.latLng) < radius).
        forEach(marcador => L.marker(marcador.latLng, {icon: caminadorIcon}).
            bindPopup("Paseador: <b>" + marcador.nombre + "</b><br>" + "Tel&eacute;fono: <b>" + marcador.telefono +
                "</b><br>" + "Tarifa: <b>$" + marcador.tarifa + "</b><br>").
            addTo(map))

  }

  map.on('locationfound', onLocationFound);

  function showModal() {

    let modal = $('#modal-map-error');
    modal.modal('show');
    modal.find('#modal-error-content').text('Hubo un error');

  }

  map.on('locationerror', showModal);


</script>
</body>
</html>