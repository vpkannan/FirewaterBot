package com.firewaterbot.server.test.functional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.firewaterbot.server.entity.BaseDrink;
import com.firewaterbot.server.entity.Drink;
import com.firewaterbot.server.entity.Ingredient;

@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class FunctionalTests extends AbstractTestNGSpringContextTests {

	private Drink drink;

	private String timeStamp;
	private ApplicationContext ctx;
	private MongoOperations mongoOperation;

	public FunctionalTests() {

		ctx = new GenericXmlApplicationContext("springBeans.xml");
		mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		String drinkName = "Martini" + "@" + this.timeStamp;

		String pictureFile = "martini.jpeg";

		List<Ingredient> ingredients = new ArrayList<Ingredient>();

		ingredients.add(new Ingredient(BaseDrink.GIN, 40.0));
		ingredients.add(new Ingredient(BaseDrink.VERMOUTH, 20.0));

		this.drink = new Drink(drinkName, pictureFile, ingredients);

	}

	/**
	 * Testcase to create a new drink recipe in the test DB and verify if it is
	 * created in the database
	 */
	@Test()
	void testCreateAndReadForNewDrinkRecipe() {

		mongoOperation.save(this.drink);

		Query searchUserQuery = new Query(Criteria.where("name").is("Martini" + "@" + this.timeStamp));
		Drink savedRecipe = mongoOperation.findOne(searchUserQuery, Drink.class);

		Assert.assertNotNull(savedRecipe);
		Assert.assertEquals(savedRecipe.getName(), "Martini" + "@" + this.timeStamp);

	}

}