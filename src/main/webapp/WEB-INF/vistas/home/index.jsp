<%@include file="../shared/header.jsp" %>
<style type="text/css">
	    	.section{
	    		width: 45%;
	    		padding: 1em;
	    		height: 215px;
	    		position: relative;
	    	}
	    	.btn{
	    		position: absolute;
	    		bottom: 1em;
	    		right: 1em;
	    	}
	    </style>
	<body>
	
		<div class = "container">
			<h1>Bienvenidos a la pagina de mascotas</h1>
			
			<div class = "d-flex justify-content-around aling-items-between">
				<div class="card section">
					<h3>¿Perdiste a tu mascota?</h3>
					<p>Crea un anuncia de perdida!</p>
					<a href = "<c:url value='/mascota-perdida/postear-perdida'/>"><button class="btn btn-danger">Postear</button></a>
				</div>
				<div class="card section">
					<h3>¿Sos paseador de perros?</h3>
					<p>Subi tu anuncio a nuestra pagina</p>
					<a href = "<c:url value='/paseador/postear-anuncio'/>"><button class="btn btn-primary">Anunciar</button></a>
				</div>
			</div>
			<div class = "d-flex justify-content-around aling-items-between">
				<div class="card section">
					<p>Lista de anuncios de mascotas perdidas!</p>
					<a href = "<c:url value='/mascota-perdida/lista-perdida'/>"><button class="btn btn-danger">Listar</button></a>
				</div>
				<div class="card section">
					<p>Anuncios de paseadores de perros</p>
					<a href = "<c:url value='/paseador/lista-anuncio'/>"><button class="btn btn-primary">Listar</button></a>
				</div>
			</div>
		</div>
<%@include file="../shared/footer.jsp" %>