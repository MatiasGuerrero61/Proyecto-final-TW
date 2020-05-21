<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class = "container">
			<form:form action="guardar-anuncio" method="POST" modelAttribute="guardarAnuncio" class="formulario">
			<h2>Publicar anuncio de paseador de perro:</h2>
			<p>Complete los siguientes datos:</p>
			<div>
				<label for="zona">Disponibilidad: </label>
				<form:input path="zona" id="zona" type="text" class="form-control" />
			</div>
			<div>
				<label for="zona">Zona de Tabajo: </label>
				<form:input path="zona" id="zona" type="text" class="form-control" />
			</div>
			<div>
				<label for="preferencia">Raza: </label>
				<form:input path="preferencia" type="text" id="preferencia" class="form-control"/>
			</div>
			<div>
				<label for="descripcion">Descripcion: </label>
				<form:textarea path="descripcion" id="descripcion" rows="5" cols="50" class="form-control"/>
			</div>
			<div style="float:right">
				<a href="./"><button class="btn btn-lg btn-secondary" >Vovler</button></a>
				<button class="btn btn-lg btn-danger" Type="submit"/>Postear</button>
			</div>
			
			
			</form:form>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>