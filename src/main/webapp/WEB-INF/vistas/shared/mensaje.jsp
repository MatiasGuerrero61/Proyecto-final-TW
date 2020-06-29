<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="es">
<head>

    <link href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.servletContext.contextPath}/css/bootstrap-theme.min.css" rel="stylesheet"
          type="text/css"/>
    <meta charset="UTF-8">
</head>

<body>
<%@include file="../shared/header.jsp" %>

<div class = "container">	
	
	<c:if test="${not empty msj}">
	  <div class="alert alert-${tipoDeMsj}" role="alert">
	        <h4>${msj}</h4>
	        <br>
	  </div>
	</c:if>	

</div>

<%@include file="../shared/footer.jsp" %>
</body>
</html>