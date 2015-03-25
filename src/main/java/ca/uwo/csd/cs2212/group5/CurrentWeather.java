package ca.uwo.csd.cs2212.group5;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

import org.json.JSONObject;

/**
 * This class constructs an object storing various weather data. The data is
 * stored in objects of the Parameter class. Time is stored in objects of the
 * Time class.
 * 
 * @author Team 5
 *
 */

public class CurrentWeather {

	private Parameter temperature;
	private Parameter humidity;
	private Parameter windSpeed;
	private Parameter windDirection;
	private Parameter airPressure;
	private Parameter minTemp;
	private Parameter maxTemp;
	private Calendar sunrise = Calendar.getInstance(TimeZone
			.getTimeZone("America/Toronto"));
	private Calendar sunset = Calendar.getInstance(TimeZone
			.getTimeZone("America/Toronto"));
	
	// private Calendar time =
	// Calendar.getInstance(TimeZone.getTimeZone("America/Toronto"));
	private Image condition;
	private String description;
	
	private static final Parameter nullParam = new Parameter("Error", "Error", 0);


	private double longi;
	private double latti;

	private JSONObject tzJson;
	private static final String timezoneUrl1 = "https://maps.googleapis.com/maps/api/timezone/json?location=";
	private static final String timezoneUrl2 = "&timestamp=0";
	private String timezone;

	/**
	 * Initializes everything to null.
	 */
	public CurrentWeather() {
		
		temperature = humidity = windSpeed = windDirection = airPressure = minTemp = maxTemp = nullParam;
		description = "";

	}

	/**
	 * Sets temperature
	 * 
	 * @param value
	 *            is the temperature
	 */
	public void setTemperature(int value) {
		value = MiscOperations.tempToCelsius(value);
		temperature = new Parameter("Temperature", "Celsius", value);
	}

	/**
	 * Sets humidity
	 * 
	 * @param value
	 *            is the humidity
	 */
	public void setHumidity(int value) {
		humidity = new Parameter("Humidity", "%", value);
	}

	/**
	 * Sets wind speed after converting from m/s to km/h
	 * 
	 * @param value
	 *            is the wind speed in m/s
	 */
	public void setWindSpeed(int value) {
		value = MiscOperations.windConvert(value);
		windSpeed = new Parameter("Wind Speed", "km/h", value);
	}

	/**
	 * Sets pressure
	 * 
	 * @param value
	 *            is the pressure
	 */
	public void setPressure(int value) {
		airPressure = new Parameter("Air Pressure", "hPa", value);
	}

	/**
	 * Sets minimum temperature
	 * 
	 * @param value
	 *            is the temperature
	 */
	public void setMinTemp(int value) {
		value = MiscOperations.tempToCelsius(value);
		minTemp = new Parameter("Min Temp", "Celsius", value);
	}

	/**
	 * Sets maximum temperature
	 * 
	 * @param value
	 *            is the temperature
	 */
	public void setMaxTemp(int value) {
		value = MiscOperations.tempToCelsius(value);
		maxTemp = new Parameter("Max Temp", "Celsius", value);
	}

	/**
	 * Sets wind direction after converting it from degree to string such as "N"
	 * or "SW"
	 * 
	 * @param value
	 *            is the wind direction in degrees
	 */
	public void setWindDir(int value) {
		String dir = MiscOperations.convertWindDir(value);
		windDirection = new Parameter("Wind Direction", dir);
	}

	/**
	 * Outputs data from all the variables as a single string.
	 */

	public void setDescription(String description) {
		this.description = description;

	}

	public void setSunset(long sunset) {
		this.sunset.setTimeInMillis(sunset * 1000);
	}

	public void setSunrise(long sunrise) {
		this.sunrise.setTimeInMillis(sunrise * 1000);
	}

	public void setCoord(double longitude, double lattitude) throws Exception {
		this.longi = longitude;
		this.latti = lattitude;

		System.out.println(longi + "," + latti);

		determineTimezone();

	}

	/**
	 * Determines the timezone of this city based on longitude and lattitude.
	 * Uses Google's Timezone API. The local timezone is needed in order to
	 * properly display (local) sunset/sunrise time.
	 * 
	 * @throws WeatherException
	 */
	public void determineTimezone() throws Exception {
		String url = timezoneUrl1 + latti + "," + longi + timezoneUrl2;

//		System.out.println(url);

		StringBuilder jsonText = new StringBuilder();

		// try {
		URL tzone = new URL(url);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				tzone.openStream()));

		String temp = "";
		while ((temp = in.readLine()) != null) {
			jsonText.append(temp);
		}
		in.close();

		// } catch (Exception e) {
		// throw new WeatherException("Error fetching timezone.");
		// }

//		System.out.println(jsonText.toString());
		
		tzJson = new JSONObject(jsonText.toString());
		timezone = tzJson.getString("timeZoneId");

//		System.out.println(timezone);

		// initializes both Calendar objects according to local timezone
		sunrise = Calendar.getInstance(TimeZone.getTimeZone(timezone));
		sunset = Calendar.getInstance(TimeZone.getTimeZone(timezone));

	}
	
	public String getTimezone(){
		return timezone;
	}

	public String toString() {
		return (temperature.toString() + "\n" + humidity.toString() + "\n"
				+ windSpeed.toString() + "\n" + windDirection.toString() + "\n"
				+ airPressure.toString() + "\n" + minTemp.toString() + "\n"
				+ maxTemp.toString() + "\n" + "Conditions: \t\t" + description
				+ "\n" + "Sunrise: \t\t" + MiscOperations.displayTime(sunrise)
				+ "\n" + "Sunset: \t\t" + MiscOperations.displayTime(sunset));
	}
}
