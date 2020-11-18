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
<title>Ajout d'un utilisateur</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<%-- <link href="${contextPath}/resource/bootstrap.min.css" rel="stylesheet"> --%>
</head>
<body>
	<div class="container">
		<center>
			<h1>Ajout d'un user</h1>
		</center>

		<div class="card">
			<div class="card-body">
				<form method="get" action="adminUsers.jsp">
					<div class="form-group row">
						<label for="code">Nom </label>
						<div class="col-sm-7">
							<input type="text" name="nom" id="nom" />
						</div>
					</div>
					<div class="form-group row">
						<label for="nom">Prenom </label>
						<div class="col-sm-7">
							<input type="text" name="prenom" id="prenom" />
						</div>
					</div>
					<div class="form-group row">
						<label for="mail">Email </label>
						<div class="col-sm-7">
							<input type="text" name="email" id="email" />
						</div>
					</div>

					<div class="form-group row">
						<label for="role">Role </label>
						<div class="col-sm-7">
							<select name="role">
								<option value="">--Please choose an option--</option>
								<option value="user">User</option>
								<option value="admin">Admin</option>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label for="code">Login </label>
						<div class="col-sm-7">
							<input type="text" name="login" id="login" />
						</div>
					</div>
					<div class="form-group row">
						<label for="code">Mot de passe </label>
						<div class="col-sm-7">
							<input type="password" name="password" id="password" />
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Ajouter...</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>