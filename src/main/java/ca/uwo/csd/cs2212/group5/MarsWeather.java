package ca.uwo.csd.cs2212.group5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONObject;

public class MarsWeather {

	private final String url = "http://marsweather.ingenology.com/v1/latest/?format=json";

	public MarsCurrent mr;
	private Calendar refreshTime;
	private boolean speedZero = false;
	private String jsonString;

	public MarsWeather() {
		refreshTime = Calendar.getInstance();
		jsonString = MiscOperations.readFromURL(url);
		mr = new MarsCurrent();
	}

	private void parseJson() {
		JSONObject main = new JSONObject(jsonString);
		main = main.getJSONObject("report");
		int tempInt;
		String tempString;

		// Minimum Temperature
		tempInt = (int) Double.parseDouble(main.getString("min_temp"));
		mr.setMinTemp(tempInt);

		// Maximum Temperature
		tempInt = (int) Double.parseDouble(main.getString("max_temp"));
		mr.setMaxTemp(tempInt);

		// Wind Speed
		try {
			tempInt = (int) Double.parseDouble(main.getString("wind_speed"));
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
				mr.setWindDir("--");
			}
		} else
			mr.setWindDir("--");

		// Condition Description
		tempString = main.getString("atmo_opacity");
		System.out.println("String" + tempString);
		mr.setCondition(tempString);

		// Humidity
		try {
			tempInt = (int) Double.parseDouble(main.getString("abs_humidity"));
			mr.setHumidity(tempInt);
		} catch (Exception e) {
			mr.setHumidity(0);
		}

	}

	public String getRefreshTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM hh:mm a");
		sdf.setTimeZone(refreshTime.getTimeZone());
		return sdf.format(refreshTime.getTime());
	}
}