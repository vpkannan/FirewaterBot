/**
 * 
 */
package com.firewaterbot.client.launcher;

import com.firewaterbot.client.rest.DrinkMaster;
import com.firewaterbot.client.ui.DrinkDetailView;

import javafx.application.Application;

/**
 * @author Vignesh
 *
 */
public class BotClient {

	public static void main(String[] args) {
		System.out.println("Hello World!");

		DrinkMaster drinkMaster = new DrinkMaster();

		drinkMaster.getDrinkByDrinkName("Martini");

		new Thread() {
			@Override
			public void run() {
				Application.launch(DrinkDetailView.class);
			}
		}.start();

	}

}
