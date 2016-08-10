/**
 * 
 */
package com.firewaterbot.server.rest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.firewaterbot.server.entity.BaseDrink;
import com.firewaterbot.server.entity.Drink;
import com.firewaterbot.server.manager.DrinkManager;
import com.firewaterbot.server.manager.impl.DrinkManagerImpl;

/**
 * @author Vignesh
 *
 */

@Path("/drink")
public class DrinkController {

	DrinkManager drinkManager;

	public DrinkController() {
		drinkManager = new DrinkManagerImpl();
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Drink getDrinkRecipeByName(@QueryParam("drinkname") String drinkName) {

		Drink drink = drinkManager.getDrinkRecipe(drinkName);
		return drink;

	}

	@GET
	@Path("/basedrink")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Drink> getDrinkRecipeByBaseDrink(@QueryParam("basedrink") String baseDrinkName) {

		List<Drink> drinks = drinkManager.getDrinksByBaseDrink(BaseDrink.valueOf(baseDrinkName));
		return drinks;

	}

}
