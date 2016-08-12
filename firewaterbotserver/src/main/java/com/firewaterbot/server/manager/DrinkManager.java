/**
 * 
 */
package com.firewaterbot.server.manager;

import java.util.List;

import com.firewaterbot.server.entity.BaseDrink;
import com.firewaterbot.server.entity.Drink;

/**
 * @author Vignesh
 *
 */
public interface DrinkManager {

	boolean addNewDrinkRecipe(Drink drink);

	Drink getDrinkRecipe(String drinkName);

	List<Drink> getDrinksByBaseDrink(BaseDrink baseDrink);

	List<Drink> getDrinksByAvailableTaps();

}
