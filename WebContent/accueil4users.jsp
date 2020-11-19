<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bienvenue user!</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="http://localhost:8080/Projet-JEE-CAMACHO/style.css">
</head>
<body>
	<div class="container">
		<br>
		<center>
			<h2>
				Liste des bulletins publiés
				</h1>
		</center>
		<br>
	</div>
	<c:set var="login" scope="session" value="${param.login}" />

	<div>
		<a href="Perimetres.jsp"> Vers la gestion de vos perimetres </a>
	</div>
	<jsp:useBean id="model" class="edu.orsys.jee.Model" scope="session"></jsp:useBean>

	<table class="table table-borderless">
		<tbody>
			<c:forEach var="c" items="${model.getListeC()}" varStatus="status">
				<c:set var="gravite" scope="session"
					value="${model.chercherVulnerabilite(c.getId_vulnerabilite()).getNiveau_gravite()}" />
				<tr>
					<c:choose>
						<c:when test="${gravite <= 1}">
							<div class="card border-primary mb-3">
						</c:when>
						<c:when test="${gravite <= 2}">
							<div class="card border-success mb-3">
						</c:when>
						<c:when test="${gravite <= 3}">
							<div class="card border-warning mb-3">
						</c:when>
						<c:when test="${gravite <= 4}">
							<div class="card border-danger mb-3">
						</c:when>
					</c:choose>

					<h5 class="card-title">
						<b> <c:out
								value="${model.chercherVersion(c.getId_version()).getLogiciel_name()}"></c:out></b>

						<c:out
							value=" : ${model.chercherVulnerabilite(c.getId_vulnerabilite()).getTitre()}"></c:out>
					</h5>

					<p class="font-italic">
						<c:out
							value="${model.chercherVulnerabilite(c.getId_vulnerabilite()).getDescription()}"></c:out>
						<br>
					</p>
					<c:out
						value="${model.chercherVulnerabilite(c.getId_vulnerabilite()).getCreated_at()} , ${model.chercherVulnerabilite(c.getId_vulnerabilite()).getReference()}"></c:out>


					</div>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 		</div> -->
	<!-- 	</div> -->
	<div>
		<!-- 	<div><a href="Deconnexion">Se deconnecter...</a></div> -->
	</div>
</body>
</html>