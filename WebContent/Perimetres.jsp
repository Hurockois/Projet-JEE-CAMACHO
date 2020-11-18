<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des perimetres</title>
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
				Bienvenue sur votre page de gestion des périmètres.
				</h1>
		</center>
		<br>
	</div>
	<div class="card" style="width: 38rem;">
		<form method="get" action="ModifierParametre">
			<!-- 		<img class="card-img-top" src="https://static.vecteezy.com/system/resources/previews/000/499/838/non_2x/notification-icon-design-vector.jpg" alt="Card image cap"> -->
			<div class="card-body">
				<h5 class="card-title">Gérer vos alertes &#128227</h5>

				<label for="cars">Selectionner une fréquence pour vos
					alertes:</label> <select name="frequence" id="frequence">
					<option value="immediat">Immédiat</option>
					<option value="mois">Fin de mois</option>
					<option value="jour">Fin de journée</option>
				</select> <label for="cars">Selectionner un format pour vos rapport:</label>
				<select name="format" id="format">
					<option value="pdf">PDF</option>
					<option value="texte">Texte</option>
				</select> <label for="cars">Selectionner une gravite pour vos
					alertes:</label> <select name="gravite" id="gravite">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select> <input type='hidden' name='login_user' id='login_user'
					value="${login}" />
				<button type="submit" class="btn btn-primary">Modifier les
					paramètres</button>
		</form>
	</div>
	</div>

	<div>
		<a href="ajoutPerimetre.jsp"> Ajouter un perimetre...</a>
	</div>
	<div class="card">
		<table class="table table-striped">
			<!-- 			<thead> -->
			<!-- 				<th>Logiciel</th> -->
			<!-- 				<th>Vulnerabilite</th> -->
			<!-- 				<th>Reference</th> -->
			<!-- 				<th>Synthese</th> -->
			<!-- 				<th>Date de sortie</th> -->
			<!-- 			</thead> -->
			<tbody>
				<c:forEach var="p" items="${model.getListeP()}" varStatus="status">

					<c:if test="${login == model.chercherUser(p.id_user).login}">
						<tr>
							<td>
								<div class="card border-dark mb-3">
									<h5 class="card-title">
										<b> <c:out value="${p.nom}"></c:out></b>
									</h5>
									<p class="font-italic">
										<c:out value="${p.logiciel_list}"></c:out>
									</p>
								</div>
							</td>
							<td><c:url value="/AfficherRapport.jsp" var="url_gen">
									<c:param name="nom_perim" value="${p.nom}"></c:param>
									<c:param name="id_user" value="${p.id_user}"></c:param>
								</c:url> <a href="${url_gen}">Générer le rapport au format HTML</a></td>
							<td><c:url value="/SupprimerPerimetre" var="url_supp">
									<c:param name="nom" value="${p.nom}"></c:param>
									<c:param name="login_user" value="${login}"></c:param>
								</c:url> <a href="${url_supp}">Supprimer...</a></td>
						</tr>

					</c:if>
				</c:forEach>
			</tbody>
		</table>

	</div>
	<div>
		<!-- 	<div><a href="Deconnexion">Se deconnecter...</a></div> -->
	</div>
</body>
</html>