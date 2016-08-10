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

	public String getDrinkByDrinkName(String drinkName) {

		String restUrl = this.constructUrl(GET_DRINK_BY_NAME_PROPERTY, drinkName);
		String response = "";
		try {

			URL url = new URL(restUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			InputStreamReader inputStreamReader = new InputStreamReader((connection.getInputStream()));

			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			System.out.println("Output from Server .... \n");
			String currentLine = "";
			while ((currentLine = bufferedReader.readLine()) != null) {
				response = response + currentLine;
				System.out.println(currentLine);
			}

			connection.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		System.out.println("Resp - " + response);
		return response;
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
