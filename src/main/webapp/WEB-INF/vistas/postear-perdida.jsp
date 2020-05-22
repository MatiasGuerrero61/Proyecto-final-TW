<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
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
			<form:form action="guardar-post" method="POST" modelAttribute="guardarPost" class="formulario">
			<h2>Publicar anuncio de mascota perdida:</h2>
			<p>Complete los siguientes datos:</p>
			<div>
				<label for="titulo">Titulo: </label>
				<form:input path="titulo" id="titulo" type="text" class="form-control" />
			</div>
			<div>
				<label for="raza">Raza: </label>
				<form:input path="raza" type="text" id="raza" class="form-control"/>
			</div>
			<div>
				<label for="descripcion">Descripcion: </label>
				<form:textarea path="descripcion" id="descripcion" rows="5" cols="50" class="form-control"/>
			</div>
			<div>
				<label for="masInformacion">Mas informacion: </label>
				<form:textarea path="masInformacion" id="masInformacion" rows="5" cols="50" class="form-control"/>
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