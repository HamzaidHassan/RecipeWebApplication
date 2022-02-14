package com.recipe.dao;

import java.util.List;

import com.recipe.entity.Recipe;

public interface RecipeDAO {
	List<Recipe>get();
	
	boolean save(Recipe e);
	
	Recipe get(int menuid);
	
	boolean update(Recipe e);
	
	boolean delete(int menuid);
}
