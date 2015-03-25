package ca.uwo.csd.cs2212.group5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONObject;

public class MarsWeather {

	private static final String url = "http://marsweather.ingenology.com/v1/latest/?format=json";
	private static MarsCurrent mars;

	public static String getIt() {
		String jsonString;
		String displayString;
		StringBuilder jsonDerulo = new StringBuilder();

		try {
			URL source = new URL(url);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					source.openStream()));
			String temp = "";

			while ((temp = in.readLine()) != null) {
				jsonDerulo.append(temp);
			}

			in.close();

		} catch (Exception e) {
		}

		jsonString = jsonDerulo.toString();
		displayString = parseJson(jsonString);
		return displayString;

	}

	private static String parseJson(String jsonString){
		JSONObject main = new JSONObject(jsonString);
		main = main.getJSONObject("report");
		int tempInt;
		String tempString;
		
		// Minimum Temperature
		tempInt = (int) Double.parseDouble(main.getString("min_temp"));
		mars.setMinTemp(tempInt);
		
		// Maximum Temperature
		tempInt = (int) Double.parseDouble(main.getString("max_temp"));
		mars.setMaxTemp(tempInt);

		// Wind Speed
		try{
		tempInt = (int) Double.parseDouble(main.getString("wind_speed"));
		mars.setWindSpeed(tempInt);
		}
		catch (Exception e){
		mars.setWindSpeed(0);
		}
		
		// Condition Description
		tempString = main.getString("atmo_opacity");
		
		// Humidity
		try{
		tempInt = (int) Double.parseDouble(main.getString("abs_humidity"));
		mars.setHumidity(tempInt);
		}
		catch (Exception e){
		mars.setHumidity(0);
		}

		return mars.toString();
	}
}