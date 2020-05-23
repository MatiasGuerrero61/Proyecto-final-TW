<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
<<<<<<< HEAD
		
=======
>>>>>>> df0c906d3ff8ddcdc5dfdf541fe5323893174cf1
		<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="../css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
		<meta charset="UTF-8">
	</head>
	
	<body>
	<div class="container-fluid">
    
    <nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"></a>
			</div>
			<ul class="nav navbar-nav">
			  <li ><a href=" <c:url value='/perfil/cargar-foto'/> ">Actualizar foto de perfil</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${not empty sessionScope.EMAIL}">
						<li><a href="#"><span class="glyphicon glyphicon-user"></span> ${sessionScope.EMAIL}</a></li>
						<li><a href=" <c:url value='/logout'/> "><span class="glyphicon glyphicon-log-out"></span> Salir</a></li>
					</c:when>
					<c:otherwise>
						<li><a href=" <c:url value='/login'/> "><span class="glyphicon glyphicon-log-out"></span> Iniciar sesi&oacute;n</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>