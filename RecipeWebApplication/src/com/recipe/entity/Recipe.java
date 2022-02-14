package com.recipe.entity;

import java.util.Arrays;

public class Recipe {
	//define the fields
	private Integer menuID;
	private String name;
	private String step;
	private String base64Image;
	//private byte[] image;
	private Integer ingredientID;
	private String ingredient;
	//generate setter and getters
	public Integer getMenuID() {
		return menuID;
	}
	public void setMenuID(Integer menuID) {
		this.menuID = menuID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	//public byte[] getImage() {
		//return image;
	//}
	//public void setImage(byte[] image) {
		//this.image = image;
	//}
	public Integer getIngredientID() {
		return ingredientID;
	}
	public void setIngredientID(Integer ingredientID) {
		this.ingredientID = ingredientID;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
	//generate toString
	@Override
	public String toString() {
		return "Recipe [menuID=" + menuID + ", name=" + name + ", step=" + step + ", ingredientID=" + ingredientID + ", ingredient=" + ingredient + "]";
	}
	
	
}
