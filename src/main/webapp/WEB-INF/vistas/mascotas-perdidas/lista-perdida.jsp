<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../shared/header.jsp" %>

			<h1>Lista de anuncios de mascotas perdidas</h1>
			<c:forEach items="${anuncios}" var="anuncio">
				<div class="card">
					<h4><span>Titulo: ${anuncio.getTitulo()}</span></h4>
					<h4><span>Informacion: ${anuncio.getInformacion()}</span></h4>
					<h4><span>Mascota: ${anuncio.getMascota().getNombre()}</span></h4>
					<h4><span>Caracteristicas: ${anuncio.getMascota().getCaracteristica()}</span></h4>
					<h4><span>Animal: ${anuncio.getMascota().getAnimal()}</span></h4>
				</div>
				
			</c:forEach>
			
			<br>
		   
<%@include file="../shared/footer.jsp" %>