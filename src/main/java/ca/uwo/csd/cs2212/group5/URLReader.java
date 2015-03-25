package ca.uwo.csd.cs2212.group5;

import java.net.*;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class creates an object that retrieves JSON data from OpenWeatherMap.
 * This data is parsed and stored in an object of type CurrentWeather.
 * Additionally, the class offers a method to return all the data currently
 * stored as a single string for display in the GUI.
 * 
 * @author Team 5
 * 
 */

public class URLReader {

	private String currentJson;
	private String shortTermJson;
	private String longTermJson;

	private String city;
	private String country;
	private double latti;
	private double longi;

	private static final String urlPrefix = "http://api.openweathermap.org/data/2.5";
	private static final String currentPrefix = "/weather?q=";
	private static final String stermPrefix = "/forecast?q=";
	private static final String ltermPrefix = "/forecast/daily?q=";

	private CurrentWeather cw;
	private ShortTerm[] st = new ShortTerm[8];
	private LongTerm[] lt = new LongTerm[5];

	// public static void main (String[] args) throws WeatherException{
	// URLReader urly = new URLReader("London", "CA");
	// System.out.println(urly.getCurrent());
	// System.out.println(System.getProperty("java.runtime.version"));
	// }

	public URLReader(String city, String country) throws NumberFormatException,
			JSONException, Exception {

		this.city = city;
		this.country = country;

		fetchJson();
		parseCW();
		parseST();
	}

	/**
	 * Gets JSON data from the OWM website which is stored in a single private
	 * string.
	 * 
	 * @throws WeatherException
	 *             error thrown if there is a problem fetching from API, usually
	 *             because the city name/country code is invalid or if the
	 *             program cannot connect to the API
	 */
	private void fetchJson() throws WeatherException {

		String url = urlPrefix + currentPrefix + city + "," + country;
		currentJson = readFromURL(url);
		// System.out.println(url);

		url = urlPrefix + stermPrefix + city + "," + country;
		shortTermJson = readFromURL(url);

		url = urlPrefix + ltermPrefix + city + "," + country;
		longTermJson = readFromURL(url);

	}

	/**
	 * Returns all the String at the given URL. This method is used to fetch the
	 * raw JSON String from the supplied URL
	 * 
	 * @param url
	 *            is the url from which JSON is to be fetched
	 * @return returns all the text on the supplied url webpage
	 */
	private String readFromURL(String url) {
		StringBuilder jsonDerulo = new StringBuilder();

		try {
			URL oracle = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					oracle.openStream()));
			String temp = "";

			while ((temp = in.readLine()) != null) {
				jsonDerulo.append(temp);
			}

			in.close();

		} catch (Exception e) {
		}

		return jsonDerulo.toString();
	}

	/**
	 * The JSON string retrieved from OWM is parsed here. Additionally, this
	 * method stores the data in an object of the CurrentWeather class
	 * 
	 * @throws Exception
	 * @throws JSONException
	 * @throws NumberFormatException
	 */
	private void parseCW() throws NumberFormatException, JSONException,
			Exception

	{
		// try {

		cw = new CurrentWeather();

		int tempInt; // intermediate variable for casting double (given by api)

		JSONObject mainObject = new JSONObject(currentJson);
		JSONObject sys = mainObject.getJSONObject("sys");
		JSONArray weatherArray = mainObject.getJSONArray("weather");
		JSONObject weather = weatherArray.getJSONObject(0);
		JSONObject main = mainObject.getJSONObject("main");
		JSONObject wind = mainObject.getJSONObject("wind");
		JSONObject coord = mainObject.getJSONObject("coord");

		// Coordinates (to set timezone)
		
		longi = Double.parseDouble(coord.get("lon").toString());
		latti = Double.parseDouble(coord.get("lat").toString());

		cw.setCoord(Double.parseDouble(coord.get("lon").toString()),
				Double.parseDouble(coord.get("lat").toString()));

		// Temperature
		tempInt = (int) Double.parseDouble(main.get("temp").toString());
		cw.setTemperature(tempInt);

		// Minimum Temperature
		tempInt = (int) Double.parseDouble(main.get("temp_min").toString());
		cw.setMinTemp(tempInt);

		// Maximum Temperature
		tempInt = (int) Double.parseDouble(main.get("temp_max").toString());
		cw.setMaxTemp(tempInt);

		// Humidity
		tempInt = (int) Double.parseDouble(main.get("humidity").toString());
		cw.setHumidity(tempInt);

		// Air Pressure
		tempInt = (int) Double.parseDouble(main.get("pressure").toString());
		cw.setPressure(tempInt);

		// Wind Speed
		tempInt = (int) Double.parseDouble(wind.get("speed").toString());
		cw.setWindSpeed(tempInt);

		// Wind Direction
		tempInt = (int) Double.parseDouble(wind.get("deg").toString());
		cw.setWindDir(tempInt);

		// Condition Description
		cw.setDescription(weather.get("description").toString());

		// Sunset Time
		cw.setSunset(Long.parseLong(sys.get("sunset").toString()));

		// Sunrise Time
		cw.setSunrise(Long.parseLong(sys.get("sunrise").toString()));
		// System.out.println("temp");

	}

	public void parseST() // throws NumberFormatException,Exception
	{

		JSONObject mainObject = new JSONObject(shortTermJson);
		JSONArray listArray = mainObject.getJSONArray("list");

		for (int i = 0; i < 8; i++) {
			JSONObject thisTime = listArray.getJSONObject(i);
			JSONObject main = thisTime.getJSONObject("main");
			JSONArray weatherArray = thisTime.getJSONArray("weather");
			JSONObject weather = weatherArray.getJSONObject(0);

			st[i] = new ShortTerm();

			// Temperature
			int tempInt = (int) Double.parseDouble(main.get("temp").toString());
			st[i].setTemp(tempInt);

			// Condition Description
			st[i].setDescription(weather.get("description").toString());

		}

	}

	public void parseLT() {
		JSONObject mainObject = new JSONObject(shortTermJson);
		JSONArray listArray = mainObject.getJSONArray("list");

		for (int i = 0; i < 5; i++) {
			JSONObject thisInstance = listArray.getJSONObject(i);
			JSONObject main = thisInstance.getJSONObject("main");
			JSONArray weatherArray = thisInstance.getJSONArray("weather");
			JSONObject weather = weatherArray.getJSONObject(0);

			// Coordinates and UTC time

			lt[i] = new LongTerm(true, longi, latti);

			// Temperature
			int tempInt = (int) Double.parseDouble(main.get("temp").toString());
			lt[i].setTemp(tempInt);

			// Condition Description
			lt[i].setDescription(weather.get("description").toString());

			// Minimum Temperature
			tempInt = (int) Double.parseDouble(main.get("temp_min").toString());
			lt[i].setMinTemp(tempInt);

			// Maximum Temperature
			tempInt = (int) Double.parseDouble(main.get("temp_max").toString());
			lt[i].setMaxTemp(tempInt);
		}
	}

	public void parseMars() {

	}

	// public void parseLT() throws NumberFormatException, JSONException,
	// Exception {
	// JSONObject mainObject = new JSONObject(longTermJson);
	// JSONArray listArray = mainObject.getJSONArray("list");
	//
	// for(int i = 0; i < 5)
	// }

	/**
	 * Returns the data stored by cw as a single string.
	 * 
	 * @return a string that contains all data, formatted.
	 */
	public String getCurrent() {
		return cw.toString();
	}

}
