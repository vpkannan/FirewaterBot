/**
 * 
 */
package com.firewaterbot.client.rest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 * @author Vignesh
 *
 */
public class DrinkMaster {

	// REST clients for the server APIs

	private String host;
	private int port;
	private String uri;
	private String protocol;

	private static final String GET_DRINK_BY_NAME_PROPERTY = "get-drink-by-name-uri";

	public DrinkMaster() {

	}
	// Get drink recipe by drink name

	public void getDrinkByDrinkName(String drinkName) {

		String restUrl = this.constructUrl(GET_DRINK_BY_NAME_PROPERTY, drinkName);

		try {

			URL url = new URL(restUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	// Get drinks by base drink

	private String constructUrl(String api, String parameter) {

		String url = "";

		Properties properties = new Properties();
		InputStream propertiesFile = null;

		try {

			propertiesFile = new FileInputStream("server.properties");

			properties.load(propertiesFile);

			this.protocol = properties.getProperty("protocol", "http");
			this.host = properties.getProperty("server-host");
			this.port = Integer.parseInt(properties.getProperty("http-port", "8080"));
			this.uri = properties.getProperty("get-drink-by-name-uri");

			System.out.println(this.getHost());
			System.out.println(this.getPort());
			System.out.println(this.getUri());
			System.out.println(this.getProtocol());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (propertiesFile != null) {
				try {
					propertiesFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		url = this.protocol + "://" + host + ":" + port + uri + parameter;

		System.out.println("URL is " + url);

		return url;

	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUri() {
		return uri;
	}

	public String getProtocol() {
		return protocol;
	}

}
