package ca.uwo.csd.cs2212.group5;

import java.net.*;
import java.io.*;
import org.json.JSONObject;

public class URLReader {

	private String jsString;
	private String city;
	private String country;

	private static final String urlPrefix = "http://api.openweathermap.org/data/2.5/weather?q=";
	private CurrentWeather cw;

	public URLReader(String city, String country) throws WeatherException {

		this.city = city;
		this.country = country;

		fetchJson();
		parseJson();
	}

	private void fetchJson() throws WeatherException {

		String url = urlPrefix + city + "," + country;
		System.out.println(url);
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

	private void parseJson() throws WeatherException

	{
		try {

			cw = new CurrentWeather();

			JSONObject mainObject = new JSONObject(jsString);
			JSONObject main = mainObject.getJSONObject("main");
			JSONObject sys = mainObject.getJSONObject("sys");

			cw.setTemperature(Integer.parseInt((main.getString(new String(
					"temp")))));
			cw.setHumidity(Integer.parseInt((main.getString(new String(
					"humidity")))));
			cw.setMinTemp(Integer.parseInt((main.getString(new String(
					"temp_min")))));
			cw.setMaxTemp(Integer.parseInt((main.getString(new String(
					"temp_max")))));
			cw.setPressure(Integer.parseInt((main.getString(new String(
					"pressure")))));

			JSONObject wind = mainObject.getJSONObject("wind");
			cw.setWindSpeed(Integer.parseInt((wind
					.getString(new String("speed")))));

		} catch (Exception e) {
			throw new WeatherException("Error parsing JSON.");
		}

	}

	public String getCurrent() {
		
		return cw.toString();

	}

}
