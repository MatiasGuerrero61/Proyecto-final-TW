<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<form:form action="guardar-post" method="POST" modelAttribute="guardarPost">
			
			<form:input path="titulo" id="titulo" type="text" class="form-control" />
			<form:input path="raza" type="raza" id="raza" class="form-control"/>
			<form:textarea path="descripcion" rows="5" cols="50"/>
			<form:textarea path="masInformacion" rows="5" cols="50"/>
			<button class="btn btn-lg btn-primary btn-block" Type="Submit"/>Login</button>
			
			</form:form>
		</div>
		
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
	</body>
</html>
