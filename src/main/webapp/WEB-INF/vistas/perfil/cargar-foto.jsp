<%@include file="../shared/header.jsp" %>

     <form method="POST" action="./cargar-foto" enctype="multipart/form-data">
      <div class="form-group">
       <label class="control-label">Seleccione la foto de perfil: </label>
       <input type="file" class="form-control" name="file"> 
      </div>
      <div class="form-group">
       <input type="submit" class="form-control btn btn-success" value="Subir">
      </div>
     </form>

   <c:if test="${not empty msj}">
			        <h4><span>${msj}</span></h4>
			        <br>
		        </c:if>	

<%@include file="../shared/footer.jsp" %>
  