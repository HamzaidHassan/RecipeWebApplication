<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<link href="https://unpkg.com/browse/bootstrap@4.1.1/dist/css/bootstrap.min.css" rel="stylesheet" / >
<body>
	<div class="container">
		<p>${message}</p>
		<button class="btn btn-primary" onclick="window.location.href='views/recipe-add.jsp'">Add Recipe</button>
		<table border = "1" class="table table-striped table-bordered">
		<tr class="thead-dark ">
			<th>Image</th>
			<th>Name</th>
			<th>Ingredient</th>
			<th>Preparation Method</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${list}" var="recipe">
			<tr>
			<td><img src="data:image/jpg;base64,${recipe.base64Image}" width="120" height="150"/></td>
			<td>${recipe.name}</td>
			<td>${recipe.ingredient}</td>
			<td>${recipe.step}</td>
			<td>
				<a href="${pageContext.request.contextPath}/RecipeController?action=EDIT&menuID=${recipe.menuID}">Edit</a>
				|
				<a href="${pageContext.request.contextPath}/RecipeController?action=DELETE&menuID=${recipe.menuID}">Delete</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>