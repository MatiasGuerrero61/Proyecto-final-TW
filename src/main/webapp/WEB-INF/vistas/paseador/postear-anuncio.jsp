<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="../css/bootstrap4.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="../css/bootstrap-theme.min.css" rel="stylesheet">
	    <style>
	    	.formulario{
	    		width: 50%;
	    		margin: 0 auto;
	    	}
	    	button{
	    		margin-top: 2em;
	    	}
	    </style>
	</head>
	<body>
		<div class = "container">
			<h1>Postear Anuncio</h1>
		</div>
		<div class = "container">
			<form:form action="crear-anuncio" method="POST" modelAttribute="crearAnuncio" class="formulario">
			<h2>Publicar anuncio paseador:</h2>
			<p>Complete los siguientes datos:</p>
			<div>
				<label for="titulo">Titulo: </label>
				<form:input path="titulo" id="titulo" type="text" class="form-control" />
			</div>
			<div>
				<label for="descripcion">Descripcion: </label>
				<form:textarea path="descripcion" id="descripcion" rows="5" cols="50" class="form-control"/>
			</div>
			<div style="float:right">
				<a href="../home"><button class="btn btn-lg btn-secondary" >Volver</button></a>
				<button class="btn btn-lg btn-danger" Type="submit"/>Anunciar</button>
			</div>
			
			
			</form:form>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>