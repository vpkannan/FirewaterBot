/**
 * 
 */
package com.firewaterbot.server.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.firewaterbot.server.entity.BaseDrink;
import com.firewaterbot.server.entity.Drink;
import com.firewaterbot.server.manager.DrinkManager;

/**
 * @author Vignesh
 *
 */
public class DrinkManagerImpl implements DrinkManager {

	private ApplicationContext ctx;
	private MongoOperations mongoOperation;

	public DrinkManagerImpl() {
		ctx = new GenericXmlApplicationContext("springBeans.xml");
		mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.firewaterbot.server.manager.DrinkManager#addNewDrinkRecipe(com.
	 * firewaterbot.server.entity.Drink)
	 */
	public boolean addNewDrinkRecipe(Drink drink) {

		mongoOperation.save(drink);
		return true;
	}

	public Drink getDrinkRecipe(String drinkName) {

		Query searchDrinkRecipeQuery = new Query(Criteria.where("name").is(drinkName));
		Drink savedDrinkRecipe = mongoOperation.findOne(searchDrinkRecipeQuery, Drink.class);

		return savedDrinkRecipe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.firewaterbot.server.manager.DrinkManager#getDrinksByBaseDrinks(com.
	 * firewaterbot.server.entity.BaseDrink)
	 */
	public List<Drink> getDrinksByBaseDrink(BaseDrink baseDrink) {

		List<Drink> drinkRecipes = new ArrayList<Drink>();

		Query searchDrinksQuery = new Query(Criteria.where("ingredients.baseDrink").all(baseDrink.toString()));
		drinkRecipes = mongoOperation.find(searchDrinksQuery, Drink.class);

		return drinkRecipes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.firewaterbot.server.manager.DrinkManager#getDrinksByAvailableTaps()
	 */
	public List<Drink> getDrinksByAvailableTaps() {

		return null;
	}

}
