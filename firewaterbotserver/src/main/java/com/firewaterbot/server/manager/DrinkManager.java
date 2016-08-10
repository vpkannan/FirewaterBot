/**
 * 
 */
package com.firewaterbot.server.manager;

import java.util.List;
import java.util.Map;

import com.firewaterbot.server.entity.BaseDrink;
import com.firewaterbot.server.entity.Drink;
import com.firewaterbot.server.entity.Tap;

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
