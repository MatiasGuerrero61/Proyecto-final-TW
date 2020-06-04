<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../shared/header.jsp" %>

			<form:form action="guardar-anuncio" method="POST" modelAttribute="guardarAnuncio" class="formulario">
			<h2>Publicar anuncio de mascota perdida:</h2>
			<p>Complete los siguientes datos:</p>
			<div>
				<label for="titulo">Titulo: </label>
				<form:input path="titulo" id="titulo" type="text" class="form-control" />
			</div>
			<div>
				<select name="mascotaId">
					<option value="default">Seleccione una mascota</option>
					<c:forEach var="mascota" items="${mascotas}">
						<option value="${mascota.getId()}">${ mascota.getNombre() }</option>
					</c:forEach>
				</select>
			</div>
			
			<div>
				<label for="masInformacion">Mas informacion: </label>
				<form:textarea path="informacion" id="informacion" rows="5" cols="50" class="form-control"/>
			</div>
			<div style="float:right">
				<a href="./"><button class="btn btn-lg btn-secondary" >Vovler</button></a>
				<button class="btn btn-lg btn-danger" Type="submit"/>Postear</button>
			</div>
			
			
			</form:form>

<%@include file="../shared/footer.jsp" %>
