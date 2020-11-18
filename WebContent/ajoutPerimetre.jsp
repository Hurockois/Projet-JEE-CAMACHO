<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%-- <c:set var="contextPath" value="${pageContext.request.contextPath}" /> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajout d'un perimetre</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<body>
	<div class="container">
		<center>
			<h1>Ajout d'un perimetre</h1>
		</center>

		<div class="card">
			<div class="card-body">
				<form method="get" action="AjoutPerim">
					
					<div class="form-group row">
						<label for="nom_perim">Nom </label>
						<div class="col-sm-7">
							<input type="text" name="nom_perim" id="nom_perim" />
						</div>
					</div>
					<div>
						<c:forEach var="logiciel" items="${model.getListeL()}">
							<input type="checkbox" id="${logiciel.name}"
								name="${logiciel.name}">
							<label for="logiciel">${logiciel.name}</label>
						</c:forEach>
					</div>
					<input type='hidden' name='login_user' id='login_user' value="${login}" />
					<button type="submit" class="btn btn-primary">Ajouter...</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>