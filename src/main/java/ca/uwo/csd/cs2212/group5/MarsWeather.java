package ca.uwo.csd.cs2212.group5;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;

/**
 * Fetches the weather on Mars in JSON format from Mars Atmospheric
 * Aggregation System API. The JSON string is then parsed and the pertinent data
 * is stored in one object MarsCurrent
 * 
 * @author CS2212 Team 5
 *
 */
public class MarsWeather {

	private final String url = "http://marsweather.ingenology.com/v1/latest/?format=json";

	public MarsCurrent mr;
	private Calendar refreshTime;
	private boolean speedZero = false;
	private String jsonString;

	/**
	 * Constructor that stores the current (refresh) time, gets JSON string from
	 * the api server and parses it by calling parseJson()
	 */
	public MarsWeather() {
		refreshTime = Calendar.getInstance();
		jsonString = MiscOperations.readFromURL(url);
		mr = new MarsCurrent();
		parseJson();
	}

	/**
	 * Parses through JSON and stores data in an instance of the object
	 * MarsCurrent.
	 */
	private void parseJson() {
		JSONObject main = new JSONObject(jsonString);
		main = main.getJSONObject("report");
		int tempInt;
		String tempString;

		// Minimum Temperature
		tempInt = (int) Double.parseDouble(main.get("min_temp").toString());
		mr.setMinTemp(tempInt);

		// Maximum Temperature
		tempInt = (int) Double.parseDouble(main.get("max_temp").toString());
		mr.setMaxTemp(tempInt);

		// Wind Speed
		try {
			tempInt = (int) Double.parseDouble(main.get("wind_speed")
					.toString());
			mr.setWindSpeed(tempInt);
		} catch (Exception e) {
			mr.setWindSpeed(0);
			speedZero = true;
		}

		// Wind direction
		if (!speedZero) {
			try {
				mr.setWindDir(main.getString("wind_direction"));

			} catch (Exception e) {
				mr.setWindDir("no wind");
			}
		} else
			mr.setWindDir("no wind");

		// Condition Description
		tempString = main.getString("atmo_opacity");
		mr.setCondition(tempString);

		// Condition Icon
		if (mr.getCondition().equalsIgnoreCase("Sunny")) {
			mr.setIcon("01d");

		}

		// Humidity
		try {
			tempInt = (int) Double.parseDouble(main.getString("abs_humidity"));
			mr.setHumidity(tempInt);
		} catch (Exception e) {
			mr.setHumidity(0);
		}

	}

	/**
	 * Returns a string containing the refresh time (ie when the contructor was
	 * called).
	 * 
	 * @return a String showing time formatted as "dd MMM hh:MM a"
	 */
	public String getRefreshTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM hh:mm a");
		sdf.setTimeZone(refreshTime.getTimeZone());
		return sdf.format(refreshTime.getTime());
	}
}