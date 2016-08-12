/**
 * 
 */
package com.firewaterbot.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.firewaterbot.client.rest.DrinkMaster;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

/**
 * @author Vignesh
 *
 */
public class DrinkDetailView extends Application {

	private Label titleLabel;
	private Label ingredientsLabel;
	private Image picture;
	private Label baseDrink;
	private String drinkDetail = "";
	private Button dispense;
	private String drinkName = "Martini";
	private List<String> ingredients;

	private static final Font ITALIC_FONT = Font.font("Verdana", FontPosture.ITALIC, 15);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle(drinkName);

		getData(this.drinkName);
		assignValues();
		setStyle();

		GridPane grid = new GridPane();

		grid.add(this.titleLabel, 1, 0, 2, 1);

		// grid.add(this.baseDrink, 0, 1, 2, 1);

		grid.add(ingredientsLabel, 0, 1, 2, 1);

		primaryStage.setScene(new Scene(grid, 800, 500));
		primaryStage.show();

	}

	private void getData(String drinkName) {

		DrinkMaster drinkMaster = new DrinkMaster();
		drinkDetail = drinkMaster.getDrinkByDrinkName(drinkName);

		System.out.println("Response JSON - " + drinkDetail);

		JSONObject jsonObject = new JSONObject(drinkDetail);

		JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");

		this.ingredients = new ArrayList<String>();
		for (int i = 0; i < ingredientsArray.length(); i++) {
			this.ingredients.add(ingredientsArray.getJSONObject(i).getString("baseDrink"));
		}

	}

	private void assignValues() {

		this.titleLabel = new Label(drinkName);

		this.baseDrink = new Label(this.ingredients.get(0));

		String ingredientsString = "";
		for (String ingredient : ingredients) {
			if (ingredientsString != "") {
				ingredientsString = ingredientsString + ", " + ingredient.toLowerCase();
			} else {
				ingredientsString = "   (" + ingredient.toLowerCase();
			}
		}
		ingredientsString = ingredientsString + ") ";
		ingredientsLabel = new Label(ingredientsString);

		// this.dispense.setText("Dispense");
		// this.dispense.setOnAction(new EventHandler<ActionEvent>() {
		//
		// public void handle(ActionEvent event) {
		// System.out.println("Hello World!");
		// }
		// });

	}

	private void setStyle() {
		this.titleLabel.setFont(new Font(STYLESHEET_MODENA, 50.0));
		this.ingredientsLabel.setFont(ITALIC_FONT);

	}

}
