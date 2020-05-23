<%@include file="../shared/header.jsp" %>
<style type="text/css">
	    	.section{
	    		width: 30%;
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
					<a href = "./mascota-perdida/postear-perdida"><button class="btn btn-danger">Postear</button></a>
				</div>
				<div class="card section">
					<h3>¿Sos paseador de perros?</h3>
					<p>Subi tu anuncio a nuestra pagina</p>
					<a href = "./paseador/postear-anuncio"><button class="btn btn-primary">Anunciar</button></a>
				</div>
				<div class="card section">
					<h3>¿Queres que el mundo vea a tu mascota?</h3>
					<p>Subi las fotos de tus mascotas</p>
					<a href = "./postear-mascotas"><button class="btn btn-success">Subir</button></a>
				</div>
			</div>
			<div class = "d-flex justify-content-around aling-items-between">
				<div class="card section">
					<p>Lista de anuncios de mascotas perdidas!</p>
					<a href = "./mascota-perdida/lista-perdida"><button class="btn btn-danger">Listar</button></a>
				</div>
				<div class="card section">
					<p>Anuncios de paseadores de perros</p>
					<a href = "./paseador/lista-anuncio"><button class="btn btn-primary">Listar</button></a>
				</div>
				<div class="card section">
					<p>Posteos de mascotas</p>
					<a href = "./postear-mascotas"><button class="btn btn-success">Listar</button></a>
				</div>
			</div>
		</div>
<%@include file="../shared/footer.jsp" %>