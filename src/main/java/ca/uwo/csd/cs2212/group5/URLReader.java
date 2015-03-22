package ca.uwo.csd.cs2212.group5;

import java.net.*;
import java.io.*;
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

	private String jsString;
	private String city;
	private String country;

	private static final String urlPrefix = "http://api.openweathermap.org/data/2.5/weather?q=";
	private CurrentWeather cw;

	// public static void main (String[] args) throws WeatherException{
	// URLReader urly = new URLReader("London", "CA");
	// System.out.println(urly.getCurrent());
	// System.out.println(System.getProperty("java.runtime.version"));
	// }

	public URLReader(String city, String country) throws WeatherException {

		this.city = city;
		this.country = country;

		fetchJson();
		parseJson();
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

		String url = urlPrefix + city + "," + country;
		// System.out.println(url);
		String jsonDerulo = "";

		try {
			URL oracle = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					oracle.openStream()));

			jsonDerulo = in.readLine();
			in.close();

		} catch (Exception e) {
			throw new WeatherException("Error fetching json.");
		}

		if (jsonDerulo.isEmpty())
			throw new WeatherException("Invalid city or country code.");

		jsString = jsonDerulo;
	}

	/**
	 * The JSON string retrieved from OWM is parsed here. Additionally, this
	 * method stores the data in an object of the CurrentWeather class
	 * 
	 * @throws WeatherException
	 *             thrown if there is a problem parsing JSON, usually if the
	 *             format is incorrect or unexpected.
	 */
	private void parseJson() throws WeatherException

	{
		// try {

		cw = new CurrentWeather();

		int tempInt; // intermediate variable for casting double (given by api)
						// to int (for display)

		JSONObject mainObject = new JSONObject(jsString);
		JSONObject main = mainObject.getJSONObject("main");
		JSONObject sys = mainObject.getJSONObject("sys");

		// System.out.println(main.get("temp").toString());

		tempInt = (int) Double.parseDouble((main.get("temp").toString()));
		cw.setTemperature(tempInt);

		tempInt = (int) Double.parseDouble((main.get("temp_min").toString()));
		cw.setMinTemp(tempInt);

		tempInt = (int) Double.parseDouble((main.get("temp_max").toString()));
		cw.setMaxTemp(tempInt);

		cw.setHumidity(Integer.parseInt((main.get("humidity").toString())));
		cw.setPressure(Integer.parseInt((main.get("pressure").toString())));

		JSONObject wind = mainObject.getJSONObject("wind");

		tempInt = (int) Double.parseDouble((wind.get("speed").toString()));
		cw.setWindSpeed(tempInt);

		cw.setWindDir(Integer.parseInt((wind.get("deg").toString())));

		// } catch (Exception e) {
		// throw new WeatherException("Error parsing JSON.");
		// }

	}

	/**
	 * Returns the data stored by cw as a single string.
	 * 
	 * @return a string that contains all data, formatted.
	 */
	public String getCurrent() {
		return cw.toString();
	}
	
	

}
