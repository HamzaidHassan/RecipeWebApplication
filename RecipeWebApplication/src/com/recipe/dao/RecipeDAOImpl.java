package com.recipe.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.connectionmanager.util.DBConnectionUtil;
import com.recipe.entity.Recipe;

public class RecipeDAOImpl implements RecipeDAO {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	
	@Override
	public List<Recipe> get() {
		
		// Create reference variable
		List<Recipe> list = null;
		Recipe recipe = null;
		
		try {
			list = new ArrayList<Recipe>();
			// Create a sql query
			String sql = "SELECT * FROM tbl_menu";
			//String sql = "SELECT * FROM tbl_menu left JOIN tbl_ingredients ON tbl_ingredients.menuID = tbl_menu.menuID";
			
			// Get the database Connection
			connection = DBConnectionUtil.openConnnection();
			
			// create a statement
			statement = connection.createStatement();
			
			// execute the sql query
			resultSet = statement.executeQuery(sql);
			
			// process the resultset
			while(resultSet.next()) {
				recipe = new Recipe();
				recipe.setMenuID(resultSet.getInt("menuID"));
				//recipe.setImage(resultSet.getBytes("image"));
				recipe.setIngredient(resultSet.getString("ingredient"));
				//recipe.setIngredientID(resultSet.getInt("ingredientID"));
				recipe.setName(resultSet.getString("name"));
				recipe.setStep(resultSet.getString("Step"));
				
				Blob blob = resultSet.getBlob("image");
				
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
				while((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				byte[] imageBytes = outputStream.toByteArray();
				
				String base64Image = Base64.getEncoder().encodeToString(imageBytes);
				
				inputStream.close();
				outputStream.close();
				recipe.setBase64Image(base64Image);
				
				// add recipe to list
				list.add(recipe);
			}
			
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		// return the list
		return list;
	}

	@Override
	public boolean save(Recipe e) {
		boolean flag = false;
		try {
			String sql = "Insert into tbl_menu(name,ingredient,step,image) values('"+e.getName()+"','"+e.getIngredient()+"','"+e.getStep()+"','"+e.getBase64Image()+"')";
			connection = DBConnectionUtil.openConnnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public Recipe get(int menuid) {
		Recipe recipe = null;
		try {
			recipe = new Recipe();
			String sql = "SELECT * FROM tbl_menu where menuid="+menuid;
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				recipe.setMenuID(resultSet.getInt("menuID"));
				recipe.setName(resultSet.getString("name"));
				recipe.setIngredient(resultSet.getString("ingredient"));
				recipe.setStep(resultSet.getString("step"));
				recipe.setBase64Image(resultSet.getString("setbase64Image"));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("Recipe name: "+recipe.getName()+"Recipe ingredient: "+recipe.getIngredient()+"Recipe Step: "+recipe.getStep()+"Image: "+recipe.getBase64Image());
		return recipe;
		
		
		
	}

	@Override
	public boolean update(Recipe e) {
		boolean flag = false;
		try {
			String sql = "UPDATE tbl_menu set name='"+e.getName()+"', ingredient='"+e.getIngredient()+"', step='"+e.getStep()+"', image='"+e.getBase64Image()+"' where menuid='"+e.getMenuID()+"'";
			connection = DBConnectionUtil.openConnnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
			flag=true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(int menuid) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM tbl_menu where menuid="+menuid;
			connection = DBConnectionUtil.openConnnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeLargeUpdate();
			flag = true;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
	}
	
	

}
