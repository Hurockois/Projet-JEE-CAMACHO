<%-- <%@page import="java.sql.ResultSet"%> --%>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%-- 	pageEncoding="ISO-8859-1"%> --%>

<%-- <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> --%>
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="ISO-8859-1"> -->
<!-- <title>Insert title here</title> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" -->
<!-- 	crossorigin="anonymous"> -->

<!-- </head> -->
<!-- <body> -->
<!-- 	<div class="container"> -->
<!-- 		<h1>Liste des participants</h1> -->
<!-- 	</div> -->
<%-- 	<jsp:useBean id="participant" class="edu.orsys.jee.td1.Participant"></jsp:useBean> --%>
<%-- 	<jsp:setProperty property="*" name="participant" /> --%>

<%-- 	<c:if --%>
<%-- 		test="${participant != null && participant.code>0 && participant.nom!=null}"> --%>
<%-- 			${model.ajouterParticipant(participant)} --%>
<%-- 	</c:if> --%>

<%-- 	<%-- 	<jsp:useBean id="model" class="edu.orsys.jee.Model" scope="session"></jsp:useBean> --%> --%>

<!-- 	<div class="card"> -->
<!-- 		<table class="table table-striped"> -->
<!-- 			<thead> -->
<!-- 				<th>Code</th> -->
<!-- 				<th>Nom</th> -->
<!-- 				<th>Email</th> -->
<!-- 				<th>Groupe</th> -->
<!-- 				<th>Action 1</th> -->
<!-- 				<th>Action 2</th> -->
<!-- 			</thead> -->
<!-- 			<tbody> -->
<%-- 				<c:forEach var="p" items="${model.getListe()}" varStatus="status"> --%>
<!-- 					<tr> -->
<%-- 						<td><c:out value="${p.code}"></c:out></td> --%>
<%-- 						<td><c:out value="${p.nom}"></c:out></td> --%>
<%-- 						<td><c:out value="${p.mail}"></c:out></td> --%>
<%-- 						<td><c:out value="${p.groupe}"></c:out></td> --%>

<%-- 						<c:url value="/afficherParticipant.jsp" var="url_affich"> --%>
<%-- 							<c:param name="code" value="${p.code}"></c:param> --%>
<%-- 							<c:param name="op" value="2"></c:param> --%>
<%-- 						</c:url> --%>
<%-- 						<c:url value="/SupprimerParticipant" var="url_supp"> --%>
<%-- 							<c:param name="code" value="${p.code}"></c:param>							 --%>
<%-- 						</c:url> --%>
<%-- 						<td><a href="${url_affich}">Afficher...</a> --%>
<%-- 						<td><a href="${url_supp}">Supprimer...</a> --%>
<!-- 						<td> -->
<!-- 					</tr> -->
<%-- 				</c:forEach> --%>
<!-- 			</tbody> -->
<!-- 		</table> -->
<!-- 		<div></div> -->
<!-- 	</div> -->
<!-- 	<div> -->
<!-- 		<a href="ajout.jsp"> Ajouter un participant...</a> -->
<!-- 	</div> -->
<!-- 	<div> -->
<!-- 		<a href="Deconnexion">Se deconnecter...</a> -->
<!-- 	</div> -->
<!-- </body> -->
<!-- </html> -->