<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	 <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page d'authentification</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

</head>
<body>
<%-- <jsp:useBean id="model" class="edu.orsys.jee.Model" scope="session"></jsp:useBean> --%>
	<div class="container">
		<center>
			<h1>Authentification</h1>
		</center>
		<div class="card">
			<div class="card-body">
				<form method="get" action="Authentif">

					<div class="form-group row">
						<label for="code">Login </label>
						<div class="col-sm-7">
							<input type="text" name="login" id="login" />
						</div>
					</div>
					<div class="form-group row">
						<label for="code">Mot de passe </label>
						<div class="col-sm-7">
							<input type="password" name="pwd" id="pwd" />
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Se connecter...</button>
				</form>
			</div>
			<div  class="card-body" style="color:red">
			${requestScope.message!=null?requestScope.message:"" }
			</div>
</body>
</html>