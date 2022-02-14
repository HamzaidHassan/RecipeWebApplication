<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Recipe</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<link href="https://unpkg.com/browse/bootstrap@4.1.1/dist/css/bootstrap.min.css" rel="stylesheet" />
<body>
		<div class="container">
			<form action="${pageContext.request.contextPath}/RecipeController" method="post" >
				<div class="card">
					<div class="card-header">
						Add New Recipe
					</div>
					<div class="card-body">
						<div class="form-group">
							Enter name of Recipe: <input type = "text" name="name" value="${recipe.name}"/><br/>
						</div>
						<div class="form-group">
							Enter ingredient of Recipe: <input type = "text" name="ingredient" value="${recipe.ingredient}"/><br/>
						</div>	
						<div class="form-group">
							Enter a preparation method : <input type = "text" name="step" value="${recipe.step}"/><br/>
						</div>	
						<div class="form-group">
							Enter a photo: <input type = "file" name="image" value="${recipe.base64Image}"/><br/>
						</div>	
						<div class="form-group">
							<input type="hidden" value = "${recipe.menuID}" name="menuid" />
						</div>	
					</div>
					<div class="card-footer">
						<button class="btn btn-primary" type="submit">Save the Recipe</button>
					</div>	
				</div>
			</form>
		</div>
</body>
</html>