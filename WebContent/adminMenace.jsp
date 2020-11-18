<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<h1>Administration des users</h1>
	</div>
	<jsp:useBean id="user" class="edu.orsys.jee.projet.User"></jsp:useBean>
	<jsp:setProperty property="*" name="user" />

	<c:if
		test="${user != null && user.nom!=null}">
			${model.ajouterUser(user)}
	</c:if>
	<br>
	<div>
		<a href="ajoutUser.jsp"> Ajouter un user...</a>
	</div>
	<br>
	<jsp:useBean id="model" class="edu.orsys.jee.Model" scope="session"></jsp:useBean>

	<div class="card">
		<table class="table table-striped">
			<thead>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Email</th>
				<th>Login</th>
				<th>Password</th>
				<th>Role</th>
			</thead>
			<tbody>
				<c:forEach var="m" items="${model.getListeU()}" varStatus="status">
					<tr>
						<td><c:out value="${u.nom}"></c:out></td>
						<td><c:out value="${u.prenom}"></c:out></td>
						<td><c:out value="${u.email}"></c:out></td>
						<td><c:out value="${u.login}"></c:out></td>
						<td><c:out value="${u.password}"></c:out></td>
						<td><c:out value="${u.role}"></c:out></td>

						<%-- 						<c:url value="/afficherParticipant.jsp" var="url_affich"> --%>
						<%-- 							<c:param name="code" value="${p.code}"></c:param> --%>
						<%-- 							<c:param name="op" value="2"></c:param> --%>
						<%-- 						</c:url> --%>
						<c:url value="/SupprimerUser" var="url_supp">
							<c:param name="id" value="${u.id}"></c:param>
						</c:url>
						<%-- 						<td><a href="${url_affich}">Afficher...</a> --%>
						<td><a href="${url_supp}">Supprimer...</a> <!-- 						<td> -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div></div>
		<!-- 	</div> -->
		<!-- 	<div> -->
		<!-- 		<a href="ajout.jsp"> Ajouter un participant...</a> -->
		<!-- 	</div> -->
		<!-- 	<div> -->
		<!-- 		<a href="Deconnexion">Se deconnecter...</a> -->
		<!-- 	</div> -->
</body>
</html>