package com.recipe.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.recipe.dao.RecipeDAO;
import com.recipe.dao.RecipeDAOImpl;
import com.recipe.entity.Recipe;

//@WebServlet("/RecipeController")
@MultipartConfig(maxFileSize = 16177215)
public class RecipeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	RequestDispatcher dispatcher = null;
	//create a references variable for recipe DAO
	RecipeDAO recipeDAO = null;
	
	//create constructor and initialize recipe DAO
	public RecipeController() {
		recipeDAO = new RecipeDAOImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action == null) {
			action = "LIST";
		}
		
		switch(action) {
			case "LIST":
				listRecipe(request, response);
				break;
			
			case "EDIT":
				getSingleRecipe(request, response);
				break;
			
			case "DELETE":
				deleteRecipe(request, response);
				break;
			
			default:
				listRecipe(request, response);
				break;
				
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String menuid = request.getParameter("menuid");
		String name = request.getParameter("name");
		String step = request.getParameter("step");
		String ingredient = request.getParameter("ingredient");
		String base64Image = request.getParameter("image");
		Recipe e = new Recipe();
		e.setName(name);
		e.setStep(step);
		e.setIngredient(ingredient);
		e.setBase64Image(base64Image);
		
		if(menuid.isEmpty() || menuid == null) {
			//save operation
			if(recipeDAO.save(e)) {
				request.setAttribute("message", "Save Successfully!!");
			}
		}else {
			//update operation
			e.setMenuID(Integer.parseInt(menuid));

			if(recipeDAO.update(e)) {
				request.setAttribute("message", "Update Successfully!!");
			}
		}
		listRecipe(request,response);
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/views/recipe-add.jsp");
		//dispatcher.forward(request, response);
	
	
	}
	
	public void listRecipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//call dao method to get list od recipe
		List<Recipe> list = recipeDAO.get();
				
		//add the recipe to request object
		request.setAttribute("list", list);
				
		//get the request dispatcher
		dispatcher = request.getRequestDispatcher("/views/recipe-list.jsp");
				
		//forward the request and response objects
		dispatcher.forward(request, response);
	}
	
	public void getSingleRecipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String menuid = request.getParameter("menuID");
		Recipe recipe = recipeDAO.get(Integer.parseInt(menuid));
		request.setAttribute("recipe", recipe);
		
		dispatcher = request.getRequestDispatcher("/views/recipe-add.jsp");
		
		dispatcher.forward(request, response);
	}
	
	public void deleteRecipe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String menuid = request.getParameter("menuID");
		if(recipeDAO.delete(Integer.parseInt(menuid))) {
			request.setAttribute("message", "Record has been deleted");
		}
		listRecipe(request,response);
	
	}
	

}
