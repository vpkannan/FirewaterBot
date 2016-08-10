/**
 * 
 */
package com.firewaterbot.server.entity;

import java.io.File;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Vignesh
 *
 */
@Document(collection = "drink")
public class Drink {

	private String name;
	private String pictureFileName;
	private List<Ingredient> ingredients;

	public Drink() {

	}

	public Drink(String name, String pictureFileName, List<Ingredient> ingredients) {
		this.name = name;
		this.pictureFileName = pictureFileName;
		this.ingredients = ingredients;
	}

	public Drink(String name, List<Ingredient> ingredients) {
		this.name = name;
		this.ingredients = ingredients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
