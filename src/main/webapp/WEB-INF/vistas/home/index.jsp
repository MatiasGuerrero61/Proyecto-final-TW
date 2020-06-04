<%@include file="../shared/header.jsp" %>

			<h1>Bienvenidos a la pagina de mascotas</h1>
			
			<div class = "d-flex justify-content-around aling-items-between">
				<div class="card section">
					<h3>¿Perdiste a tu mascota?</h3>
					<p>Crea un anuncia de perdida!</p>
					<a href = "<c:url value='/mascotas-perdidas/postear-perdida'/>"><button class="btn btn-danger">Postear</button></a>
				</div>
			</div>
			<div class = "d-flex justify-content-around aling-items-between">
				<div class="card section">
					<p>Lista de anuncios de mascotas perdidas!</p>
					<a href = "<c:url value='/mascotas-perdidas/lista-perdida'/>"><button class="btn btn-danger">Listar</button></a>
				</div>
			</div>
<%@include file="../shared/footer.jsp" %>