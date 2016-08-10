/**
 * 
 */
package com.firewaterbot.server.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Vignesh
 *
 */
@Document(collection = "bar")
public class Bar {

	private List<Tap> taps;

	private Bar() {

	}

	public static Bar getBarInstance() {
		// Code to retrieve bar from DB. IF not present, create a new Bar.

		Bar bar = new Bar();
		Tap tap = new Tap();
		tap.setBaseDrink(BaseDrink.RUM);
		List<Tap> taps = new ArrayList<Tap>();

		bar.setTaps(taps);

		return bar;
	}

	public List<Tap> getTaps() {
		return taps;
	}

	public void setTaps(List<Tap> taps) {
		this.taps = taps;
	}

}
