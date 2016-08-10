/**
 * 
 */
package com.firewaterbot.server.entity;

import java.io.File;

/**
 * @author Vignesh
 *
 */
public class Ingredient {

	private BaseDrink baseDrink;
	private String pictureFileName;
	private Double Quantity;

	public Ingredient() {

	}

	public Ingredient(BaseDrink baseDrink, String pictureFileName, Double quantity) {
		this.baseDrink = baseDrink;
		this.pictureFileName = pictureFileName;
		Quantity = quantity;
	}

	public Ingredient(BaseDrink baseDrink, Double quantity) {
		this.baseDrink = baseDrink;
		Quantity = quantity;
	}

	public BaseDrink getBaseDrink() {
		return baseDrink;
	}

	public void setBaseDrink(BaseDrink baseDrink) {
		this.baseDrink = baseDrink;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public Double getQuantity() {
		return Quantity;
	}

	public void setQuantity(Double quantity) {
		Quantity = quantity;
	}

}
